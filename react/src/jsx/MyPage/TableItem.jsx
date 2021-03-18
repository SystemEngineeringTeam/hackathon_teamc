import React from "react"
import axios from "axios"

class TableItem extends React.Component {
	constructor(props) {
		super(props)
		this.doReturn = this.doReturn.bind(this)
	}

	async doReturn() {
		let body = {
			bookID: this.props.bookID,
		}
		await axios.put(host.lend, { data: body }).then((res) => {
			console.log(res)
		})
	}

	render() {
		return (
			<TableRow>
				<TableCell>{this.props.title}</TableCell>
				<TableCell>{this.props.author} </TableCell>
				<TableCell>{this.props.publisher} </TableCell>
				<TableCell>{this.props.pyear} </TableCell>
				<TableCell>
					<Button onClick={this.doReturn}>返却</Button>
				</TableCell>
			</TableRow>
		)
	}
}

export default TableItem
