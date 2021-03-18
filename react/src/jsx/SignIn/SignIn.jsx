import React from "react"
import { Button, Grid, Paper, TextField, Typography } from "@material-ui/core"
import ReactDOM from "react-dom"
import Host from "../Host"
import axios from "axios"
import { Redirect } from "react-router"

const createHash = require("sha256-uint8array").createHash
const host = new Host()
class UserForm extends React.Component {
	constructor(props) {
		super(props)
		this.state = {
			html: <div />,
		}
		this.doSubmit = this.doSubmit.bind(this)
	}

	async doSubmit(e) {
		e.preventDefault()
		let email = document.querySelector("#email").value
		let password = document.querySelector("#password").value
		password = createHash().update(password).digest("hex")
		let flag = false

		let body = {
			mailaddress: email,
			pass: password,
		}

		// wip post処理かく
		await axios.post(host.login, body).then((res) => {
			console.log(res)
			flag = res.data.available
		})

		if (flag) {
			alert("ログイン成功")
			document.cookie = "email=" + email
			location.href = "/shelf"
		} else {
			alert("ログイン失敗")
		}
	}

	render() {
		return (
			<div>
				<Grid container alignItems="center" justify="center">
					<Grid
						container
						alignItems="center"
						justify="center"
						alignContent="center"
						item
						xs={12}
					>
						<Paper variant="outlined" elevation={3}>
							<Grid item xs={12}>
								<form onSubmit={this.doSubmit}>
									<Grid
										container
										item
										xs={12}
										alignContent="center"
										justify="center"
									>
										<TextField
											type="email"
											placeholder="メールアドレス"
											variant="outlined"
											id="email"
											required
										></TextField>
									</Grid>
									<Grid
										container
										item
										xs={12}
										alignContent="center"
										justify="center"
									>
										<TextField
											type="password"
											placeholder="パスワード"
											variant="outlined"
											id="password"
											required
											minLength="8"
										></TextField>
									</Grid>
									<Grid
										container
										item
										xs={12}
										alignContent="center"
										justify="center"
									>
										<Button type="submit" variant="contained">
											SignIn
										</Button>
									</Grid>
								</form>
								<Grid container>
									<Grid item xs={12}>
										{this.state.html}
									</Grid>
								</Grid>
							</Grid>
						</Paper>
					</Grid>
				</Grid>
			</div>
		)
	}
}

export default UserForm
