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

	doSubmit(e) {
		e.preventDefault()
		let email = document.querySelector("#email").value
		let password = document.querySelector("#password").value

		let re = new RegExp("^[a-zA-Z0-9.?/-]{8,24}$")
		if (re.test(password)) {
			this.setState({
				html: (
					<Typography color="primary">
						ユーザー登録が完了しました． <br />
						メールを確認してください．
					</Typography>
				),
			})
		} else {
			this.setState({
				html: (
					<Typography color="error">
						パスワードは8文字以上入力してください
					</Typography>
				),
			})
		}

		password = createHash().update(password).digest("hex")
	}

	render() {
		return (
			<div>
				<Grid container alignItems="center" justify="center">
					<Grid item xs={12}>
						<Paper variant="outlined" elevation={3}>
							<form onSubmit={this.doSubmit}>
								<Grid container item xs={10}>
									<TextField
										type="email"
										placeholder="email"
										variant="outlined"
										id="email"
										required
									></TextField>
								</Grid>
								<Grid container item xs={10}>
									<TextField
										type="password"
										placeholder="password"
										variant="outlined"
										id="password"
										required
										minLength="8"
									></TextField>
								</Grid>
								<Grid container item xs={10}>
									<TextField
										type="password"
										placeholder="password"
										variant="outlined"
										id="password"
										required
										minLength="8"
									></TextField>
								</Grid>
								<Grid container item xs={10}>
									<Button type="submit" variant="contained">
										SignUp
									</Button>
								</Grid>
							</form>
							<Grid container>
								<Grid item xs={8}>
									{this.state.html}
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
