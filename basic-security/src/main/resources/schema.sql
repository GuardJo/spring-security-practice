create table dummy_user
(
    id        int         not null auto_increment,
    username  varchar(50) not null unique,
    password  varchar(50) not null,
    authority varchar(50) not null,
    primary key (id)
);