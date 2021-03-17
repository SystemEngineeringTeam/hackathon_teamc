package database.src.booksql;

public class BooksData {

    public int id;
    public String title;
    public String author;
    public String publisher;
    public String pyear;
    public String cover_url;
    public String[] tags;

    public void setBooksData(int id,String title,String author,String publisher,String pyear,String cover_url){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pyear = pyear;
        this.cover_url = cover_url;
    }
    public void settags(String tags){
        this.tags = tags.split(",",0);
    }
}
