use app_db;

create table books(
    id int not null auto_increment primary key,
    title varchar(128) not null,
    author varchar(16),
    publisher varchar(16),
    pyear int,
    cover_url varchar(256),
    foreign key (lend_user) references users(id) on delete cascade,
);

create table users(
    id int not null auto_increment primary key,
    name varchar(16) not null,
    mailaddress varchar(64) not null,
    pass varchar(16) not null
);

create table tags(
    id int not null auto_increment primary key,
    tags_detail varchar(8) not null
);

create table book_tags(
    book_id int not null,
    tags_id int not null,
    foreign key (book_id) references books(id) on delete cascade,
    foreign key (tags_id) references tags(id) on delete cascade
);

create table rental_list(
    book_id int not null,
    user_id int not null,
    lend_flag tinyint not null,
    foreign key (book_id) references books(id) on delete cascade,
    foreign key (user_id) references users(id) on delete cascade
);