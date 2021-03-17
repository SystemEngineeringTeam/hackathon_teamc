import React from "react"
import { Button, Grid, Paper, TextField, Typography } from "@material-ui/core"
import ReactDOM from "react-dom"

const createHash = require("sha256-uint8array").createHash

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

		// wip post処理かく
		// await axios.post()

		if (password) {
			this.setState({
				html: <Typography color="primary" align="center"></Typography>,
			})

			// wip ログインできたらcookieに必要な情報を保存
		} else {
			this.setState({
				html: (
					<Typography color="error" align="center">
						パスワードは8文字以上入力してください
					</Typography>
				),
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