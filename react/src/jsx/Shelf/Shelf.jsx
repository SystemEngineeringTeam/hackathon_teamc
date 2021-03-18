import React from "react"
import { connect } from "react-redux"
import Book from "../Book"
import Store, { getShelf } from "../Store"
import ReactDOM from "react-dom"

class Shelf extends React.Component {
	constructor(props) {
		super(props)
	}

	getBooks() {
		let action = getShelf()
		this.props.dispatch(action)

		let shelf = document.querySelector("#shelf")
		Store.getState().then((res) => {
			ReactDOM.render(
				res.data.map((e) => {
					console.log(e)
					return (
						<Book
							key={e.id}
							bookID={e.id}
							title={e.title}
							author={e.author}
							publisher={e.publisher}
							publishYear={e.pyear}
							src={e.cover_url}
							registered={true}
							lend_flag={e.lend_flag}
						/>
					)
				}),
				shelf
			)

			console.log(res)
		})
	}

	componentDidMount() {
		this.getBooks()
	}

	render() {
		return (
			<div>
				<div id="shelf" />
			</div>
		)
	}
}

export default connect()(Shelf)
