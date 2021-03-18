export default class Host {
	constructor() {
		this.baseURL = "http://localhost:8000"
		this.book = this.baseURL + "/book"
		this.user = this.baseURL + "/user"
		this.lend = this.baseURL + "/lend"
		this.login = this.baseURL + "/login"
	}
}
