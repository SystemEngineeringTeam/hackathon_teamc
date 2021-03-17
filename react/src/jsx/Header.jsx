import React from "react"
import { AppBar, IconButton, Toolbar, Typography } from "@material-ui/core"
import Menu from "@material-ui/icons/Menu"

class Header extends React.Component {
	constructor(props) {
		super(props)
	}

	render() {
		return (
			<AppBar position="static">
				<Toolbar>
					<IconButton color="inherit">
						<Menu></Menu>
					</IconButton>
					<Typography variant="h6">App</Typography>
				</Toolbar>
			</AppBar>
		)
	}
}

export default Header
