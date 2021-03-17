import React from "react"
import { connect } from "react-redux"
import { Container } from "@material-ui/core"
import SearchForm from "./Search/SearchForm"
class App extends React.Component {
	constructor(props) {
		super(props)
	}

	render() {
		return (
			<div>
				<Container>
					<SearchForm />
					<div id="book-list" />
				</Container>
			</div>
		)
	}
}

export default connect()(App)
