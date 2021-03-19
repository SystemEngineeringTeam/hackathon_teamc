import { createStore } from "redux"
import axios from "axios"
import Host from "./Host"

let initData = {
	data: [],
}

let baseURL = "https://www.googleapis.com/books/v1/volumes?q="
const host = new Host()

export function bookReducer(state = initData, action) {
	switch (action.type) {
		case "search":
			return searchReduce(state, action)
		case "shelf":
			return shelfReduce(state, action)
		default:
			return state
	}
}

async function searchReduce(state, action) {
	state.data = []
	if (action.word.length > 0) {
		await axios.get(baseURL + action.word).then((res) => {
			if (res.data.items) {
				res.data.items.map((e) => {
					state.data.push(e)
				})
			}
		})
	}

	return { data: state.data }
}

async function shelfReduce(state, action) {
	state.data = []
	await axios.get(host.book).then((res) => {
		res.data.map((e) => {
			state.data.push(e)
		})
	})
	return { data: state.data }
}

export function searchBook(word) {
	return {
		word: word,
		type: "search",
	}
}

export function getShelf() {
	return {
		type: "shelf",
	}
}

export default createStore(bookReducer)
