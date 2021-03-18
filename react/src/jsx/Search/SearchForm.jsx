import React from "react"
import { TextField, Button, Grid } from "@material-ui/core"
import Store, { searchBook } from "../Store"
import { connect } from "react-redux"
import ReactDOM from "react-dom"
import Book from "../Book"

class SearchForm extends React.Component {
	constructor(props) {
		super(props)
		this.doSubmit = this.doSubmit.bind(this)
	}

	doSubmit(e) {
		e.preventDefault()
		let word = document.querySelector("#search-word").value
		let action = searchBook(word)
		this.props.dispatch(action)
		let bookList = document.querySelector("#book-list")
		Store.getState().then((state) => {
			let key = 0
			ReactDOM.render(
				state.data.map((e) => {
					return (
						<Book
							title={e.volumeInfo.title}
							author={
								e.volumeInfo.authors && e.volumeInfo.authors.length > 0
									? e.volumeInfo.authors[0]
									: "不明"
							}
							publisher={
								e.volumeInfo.publisher ? e.volumeInfo.publisher : "不明"
							}
							publishYear={
								e.volumeInfo.publishedDate
									? e.volumeInfo.publishedDate.split("-")[0]
									: ""
							}
							key={key++}
							src={
								e.volumeInfo.imageLinks && e.volumeInfo.imageLinks.thumbnail
									? e.volumeInfo.imageLinks.thumbnail
									: "http://via.placeholder.com/350x150"
							}
							registered={false}
						/>
					)
				}),
				bookList
			)
		})
	}

	render() {
		return (
			<form onSubmit={this.doSubmit}>
				<Grid container spacing={1} alignItems="center" justify="center">
					<Grid item xs={8}>
						<TextField
							fullWidth
							placeholder="Search"
							variant="outlined"
							size="small"
							id="search-word"
						></TextField>
					</Grid>
					<Grid item xs={3}>
						<Button type="submit" variant="contained">
							Search
						</Button>
					</Grid>
				</Grid>
			</form>
		)
	}
}

export default connect()(SearchForm)
