import React from "react"
import ReactDOM from "react-dom"
import { Provider } from "react-redux"
import App from "./App"
import Store from "./Store"
import "regenerator-runtime"
import SignUp from "./SignUp/SignUp"

let root = document.querySelector("#root")
ReactDOM.render(
	<Provider store={Store}>
		<App />
		<SignUp></SignUp>
	</Provider>,
	root
)
