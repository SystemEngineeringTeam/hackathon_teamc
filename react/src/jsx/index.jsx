import React from "react"
import ReactDOM from "react-dom"
import { Provider } from "react-redux"
import App from "./App"
import Store from "./Store"
import "regenerator-runtime"
import SignUp from "./SignUp/SignUp"
import SignIn from "./SignIn/SignIn"
import { BrowserRouter, Route } from "react-router-dom"
import Header from "./Header"
import Shelf from "./Shelf/Shelf"

let root = document.querySelector("#root")
ReactDOM.render(
	<Provider store={Store}>
		<BrowserRouter>
			<Header />
			<Route exact path="/search" component={App} />
			<Route exact path="/signup" component={SignUp} />
			<Route exact path="/signin" component={SignIn} />
			<Route exact path="/shelf" component={Shelf} />
		</BrowserRouter>
	</Provider>,
	root
)
