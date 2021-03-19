import React from "react"
import axios from "axios"
import { TableRow, TableCell, Button } from "@material-ui/core"
import Host from "../Host"

const host = new Host()

class TableItem extends React.Component {
	constructor(props) {
		super(props)
		this.doReturn = this.doReturn.bind(this)
	}

	async doReturn() {
		let body = {
			bookID: this.props.bookID,
		}
		await axios.put(host.lend, body).then((res) => {})

		location.href = "/mypage"
	}

	render() {
		return (
			<TableRow>
				<TableCell>{this.props.title}</TableCell>
				<TableCell>{this.props.author} </TableCell>
				<TableCell>{this.props.publisher} </TableCell>
				<TableCell>{this.props.pyear} </TableCell>
				<TableCell>
					<Button onClick={this.doReturn} variant="contained">
						返却
					</Button>
				</TableCell>
			</TableRow>
		)
	}
}

export default TableItem
