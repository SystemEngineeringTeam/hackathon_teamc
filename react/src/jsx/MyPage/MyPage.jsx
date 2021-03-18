import React from "react"
import {
	Grid,
	Paper,
	TableCell,
	TableHead,
	Typography,
	Table,
	TableBody,
	TableRow,
} from "@material-ui/core"
import axios from "axios"
import Host from "../Host"
import Header from "../Header"
import TableItem from "./TableItem"

const host = new Host()

class MyPage extends React.Component {
	constructor(props) {
		super(props)
		this.name = ""
		this.state = { list: [] }
	}

	async componentDidMount() {
		let c = document.cookie.split("=")
		let data = []
		if (c.length < 2) {
			location.href = "/login"
		} else {
			await axios
				.get(host.user, { params: { mailaddress: c[1] } })
				.then((res) => {
					res.data.list.map((e) => {
						data.push(e)
					})
				})
		}

		this.setState({
			list: data,
		})
	}

	async showList() {
		await axios.get(host.book).then((res) => {
			res.data.map((e) => {
				console.log(e)
				if (this.state.list.indexOf(e.id) != -1) {
					return (
						<TableItem
							title={e.title}
							author={e.author}
							publisher={e.publisher}
							pyear={e.pyear}
							key={e.id}
						/>
					)
				}
			})
		})
	}

	render() {
		return (
			<div>
				<Grid container>
					<Grid item xs={12}>
						<Paper variant="outlined">
							<Typography variant="h5">{this.name}</Typography>
							<Table>
								<TableHead>
									<TableRow>
										<TableCell>タイトル</TableCell>
										<TableCell>著者名</TableCell>
										<TableCell>出版社</TableCell>
										<TableCell>出版年</TableCell>
									</TableRow>
								</TableHead>
								<TableBody>{this.showList}</TableBody>
							</Table>
						</Paper>
					</Grid>
				</Grid>
			</div>
		)
	}
}

export default MyPage
