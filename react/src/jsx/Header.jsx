import React from "react"
import {
	AppBar,
	IconButton,
	Toolbar,
	Typography,
	Drawer,
	List,
	ListItemText,
	ListItem,
} from "@material-ui/core"
import Menu from "@material-ui/icons/Menu"
import { Link } from "react-router-dom"

class Header extends React.Component {
	constructor(props) {
		super(props)
		this.state = {
			isOpen: false,
		}
		this.toggle = this.toggle.bind(this)
	}

	toggle(e) {
		e.preventDefault()
		this.setState({
			isOpen: !this.state.isOpen,
		})
	}

	render() {
		const menuItems = [
			{ text: "本棚", path: "/shelf" },
			{ text: "本の登録", path: "/search" },
			{ text: "ユーザー登録", path: "/signup" },
			{ text: "ログイン", path: "/signin" },
			{ text: "マイページ", path: "/mypage" },
		]
		return (
			<div>
				<AppBar position="static">
					<Toolbar>
						<IconButton color="inherit" onClick={this.toggle}>
							<Menu></Menu>
						</IconButton>
						<Typography variant="h6">App</Typography>
					</Toolbar>
				</AppBar>

				<Drawer anchor="left" open={this.state.isOpen} onClose={this.toggle}>
					<List>
						<React.Fragment>
							{menuItems.map((e) => {
								return (
									<Link to={e.path} key={e.text}>
										<ListItem button>
											<ListItemText primary={e.text} />
										</ListItem>
									</Link>
								)
							})}
						</React.Fragment>
					</List>
				</Drawer>
			</div>
		)
	}
}

export default Header
