import React from "react"
import { Button, Grid, Paper, TextField, Typography } from "@material-ui/core"
import ReactDOM from "react-dom"
import axios from "axios"
import Host from "../Host"

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
		let name = document.querySelector("#name").value
		let email = document.querySelector("#email").value
		let password = document.querySelector("#password").value
		let passwordChecker = document.querySelector("#password-check").value

		let re = new RegExp("^[a-zA-Z0-9.?/-]{8,24}$")

		let flag = true
		if (re.test(password)) {
			this.setState({
				html: (
					<Typography color="primary" align="center">
						ユーザー登録が完了しました． <br />
						メールを確認してください．
					</Typography>
				),
			})

			if (password != passwordChecker) {
				flag = false
				this.setState({
					html: (
						<Typography color="error" align="center">
							パスワードが一致しません
						</Typography>
					),
				})
			}
		} else {
			flag = false
			this.setState({
				html: (
					<Typography color="error" align="center">
						パスワードは8文字以上入力してください
					</Typography>
				),
			})
		}

		password = createHash().update(password).digest("hex")
		let body = {
			name: name,
			mailaddress: email,
			pass: password,
		}

		if (flag) {
			await axios.post(host.user, body).then((res) => {
				// console.log(res)
				if (res.data.available) {
					alert("登録完了")
				}
			})
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
											type="text"
											placeholder="名前"
											variant="outlined"
											id="name"
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
										<TextField
											type="password"
											placeholder="パスワード(確認用)"
											variant="outlined"
											id="password-check"
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
											SignUp
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
