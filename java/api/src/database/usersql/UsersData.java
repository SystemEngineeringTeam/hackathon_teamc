package database.usersql;

public class UsersData {
    public String name;
    public String mailaddress;
    public String pass;
    public void SetData(String name, String mailaddress, String pass){
        this.name = name;
        this.mailaddress = mailaddress;
        this.pass = pass;
    }
}
