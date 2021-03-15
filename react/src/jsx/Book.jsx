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

class Book extends React.Component {
	constructor(props) {
		super(props)
	}

	doClick(e) {
		e.preventDefault()
		// WIP
		// APIにPOSTする
	}

	render() {
		return (
			<Paper elevation={3} variant="outlined">
				<Grid container spacing={3}>
					<Grid item xs={3}>
						<img src={this.props.src} height="100px"></img>
					</Grid>
					<Grid item xs={9} sm container>
						<Grid xs={12} item>
							<Typography variant="h5">{this.props.title}</Typography>
						</Grid>
						<Grid container>
							<Grid item xs={this.props.registered ? 5 : 8}></Grid>
							{this.props.registered ? (
								<Grid xs={3} item>
									<Button color="secondary" variant="contained" height="20px">
										Delete
									</Button>
								</Grid>
							) : (
								<Grid xs={3} item>
									<Button color="primary" variant="contained" height="20px">
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
