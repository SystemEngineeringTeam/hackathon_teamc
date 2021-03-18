INSERT INTO books(title,author,publisher,publishyear,cover_url) 
    VALUE ('test','kaityo','sisuken','2021',null);

INSERT INTO users(name ,mailaddress,pass)
    VALUE ('henoheno','hoge','hogehoge');

INSERT INTO tags(tags_detail)
    VALUE ('テスト');

INSERT INTO book_tags
    VALUE(1,1);

INSERT INTO rental_lists
    VALUE(1,1,2021-3-20,1);