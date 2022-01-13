create table article_types
(
    id   int primary key,
    name varchar
);

create table categories
(
    id   int primary key,
    name varchar
);

create table articles
(
    id               int primary key,
    headline         varchar,
    article_abstract varchar,
    main_text        text,
    date_created     date,
    date_amended     date,
    posted_by        varchar,
    make_public      boolean,
    views            int,

    article_type_id  int,
    foreign key (article_type_id) references types (id)
);

create table comments
(
    id          int primary key,
    name        varchar,
    email       varchar,
    text        text,
    date_posted date,
    is_reply    boolean,
    publish     boolean,

    article_id  int,
    foreign key (article_id) references articles (id)
);


--many to many
create table category_article
(
    id          int primary key,
    article_id  int,
    category_id int,
    foreign key (article_id) references articles (id),
    foreign key (category_id) references categories (id)
);





