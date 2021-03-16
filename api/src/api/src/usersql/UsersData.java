package api.src.usersql;

public class UsersData {
    public String name;
    public String mailaddress;
    public String pass;
    public void AddData(String name, String mailaddress, String pass){
        this.name = name;
        this.mailaddress = mailaddress;
        this.pass = pass;
    }
}
