import { createStore } from "redux"
import axios from "axios"

let initData = {
	data: [],
}

let baseURL = "https://www.googleapis.com/books/v1/volumes?q="

export function bookReducer(state = initData, action) {
	if (action.type == "search") {
		return searchReduce(state, action)
	} else {
		return state
	}
}

async function searchReduce(state, action) {
	state.data = []
	if (action.word.length > 0) {
		await axios.get(baseURL + action.word).then((res) => {
			res.data.items.map((e) => {
				state.data.push(e)
			})
		})
	}

	return { data: state.data }
}

export function searchBook(word) {
	return {
		word: word,
		type: "search",
	}
}

export default createStore(bookReducer)
