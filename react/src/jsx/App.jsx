import React from "react"
import { connect } from "react-redux"
import { Container } from "@material-ui/core"
import SearchForm from "./Search/SearchForm"
import Header from "./Header"

class App extends React.Component {
	constructor(props) {
		super(props)
	}

	render() {
		return (
			<div>
				<Header />
				<Container>
					<SearchForm />
					<div id="book-list" />
				</Container>
			</div>
		)
	}
}

export default connect()(App)
