import React from "react"
import {
	Card,
	CardContent,
	Typography,
	Button,
	CardMedia,
	Grid,
	Paper,
} from "@material-ui/core"
import axios from "axios"
import Host from "./Host"
import "regenerator-runtime/runtime"

const host = new Host()
class Book extends React.Component {
	constructor(props) {
		super(props)

		this.doAdd = this.doAdd.bind(this)
		this.doBorrow = this.doBorrow.bind(this)
		this.doDelete = this.doDelete.bind(this)

		this.isLend = this.props.lend_flag
	}

	async doAdd(e) {
		e.preventDefault()
		// WIP
		// APIにPOSTする
		// await axios.post(host.book, {

		// })

		let body = {
			title: this.props.title,
			author: this.props.author,
			publisher: this.props.publisher,
			pyear: this.props.publishYear,
			cover_url: this.props.src,
		}

		await axios.post(host.book, body).then((res) => {
			// wip resのbodyに応じてalertする(登録できたよ)
			console.log(res)
			if (res.data.available) {
				alert("登録完了")
			}
		})
	}

	async doDelete() {
		let body = {
			bookID: this.props.bookID,
		}
		console.log(body)
		await axios.delete(host.book, { data: body }).then((res) => {
			console.log(res)
		})
	}

	async doBorrow() {
		let body = {
			bookID: this.props.bookID,
			email: document.cookie.split("=")[1],
		}
		console.log(body)

		await axios.post(host.lend, body).then((res) => {
			console.log(res)
			this.isLend = 1
		})

		location.href = "/shelf"
	}

	render() {
		return (
			<Paper elevation={3} variant="outlined">
				{/* <input type="hidden" value={this.props.bookID} id="bookID" /> */}
				<Grid container spacing={3}>
					<Grid item xs={3}>
						<img src={this.props.src} height="100px"></img>
					</Grid>
					<Grid item xs={9} sm container>
						<Grid xs={12} item>
							<Typography variant="h4" component="h4">
								{this.props.title}
							</Typography>
						</Grid>
						<Grid xs={12} item>
							<Typography variant="h5">{this.props.author}</Typography>
						</Grid>
						<Grid xs={12} item>
							<Typography variant="h5">{this.props.publisher}</Typography>
						</Grid>
						<Grid xs={12} item>
							<Typography variant="h5">{this.props.publishYear}</Typography>
						</Grid>
						<Grid container>
							<Grid item xs={this.props.registered ? 5 : 8}></Grid>
							{this.props.registered ? (
								this.isLend ? (
									<Grid xs={3} item>
										<Button disabled variant="contained" height="20px">
											貸出中
										</Button>
									</Grid>
								) : (
									<Grid xs={3} item>
										<Button
											color="primary"
											variant="contained"
											height="20px"
											onClick={this.doBorrow}
										>
											借りる
										</Button>
									</Grid>
								)
							) : (
								<div />
							)}

							{this.props.registered ? (
								this.isLend ? (
									<Grid xs={3} item>
										<Button
											color="secondary"
											variant="contained"
											height="20px"
											onClick={this.doDelete}
											disabled
										>
											削除
										</Button>
									</Grid>
								) : (
									<Grid xs={3} item>
										<Button
											color="secondary"
											variant="contained"
											height="20px"
											onClick={this.doDelete}
										>
											削除
										</Button>
									</Grid>
								)
							) : (
								<Grid xs={3} item>
									<Button
										color="primary"
										variant="contained"
										height="20px"
										onClick={this.doAdd}
									>
										Add
									</Button>
								</Grid>
							)}
						</Grid>
					</Grid>
				</Grid>
			</Paper>
		)
	}
}

export default Book
