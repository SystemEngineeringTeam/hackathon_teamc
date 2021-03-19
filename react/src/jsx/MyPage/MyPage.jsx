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
import ReactDOM from "react-dom"

const host = new Host()

class MyPage extends React.Component {
	constructor(props) {
		super(props)
		this.name = ""
		this.state = { list: [] }
	}

	async componentDidMount() {
		let c = document.cookie.split("=")
		this.state.list = []
		if (c.length < 2) {
			location.href = "/login"
		} else {
			await axios.get(host.user, { params: { email: c[1] } }).then((res) => {
				console.log(res)
				if (res.data.list) {
					res.data.list.map((e) => {
						this.state.list.push(e)
					})
				}
			})
		}

		this.setState({
			list: this.state.list,
		})

		await this.showList()
	}

	async showList() {
		let items = document.querySelector("#items")
		let list = []
		await axios.get(host.book).then((res) => {
			res.data.map((e) => {
				if (this.state.list.indexOf(e.id) != -1) {
					list.push(e)
				}
			})
		})

		ReactDOM.render(
			list.map((e) => {
				return (
					<TableItem
						title={e.title}
						author={e.author}
						publisher={e.publisher}
						pyear={e.pyear}
						bookID={e.id}
						key={e.id}
					/>
				)
			}),
			items
		)
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
								<TableBody id="items">
									{/* {this.showList().then((res) => {
										console.log(res)
									})} */}
								</TableBody>
							</Table>
						</Paper>
					</Grid>
				</Grid>
			</div>
		)
	}
}

export default MyPage
