create table ACCOUNT(
                        id int not null auto_increment,
                        username varchar(45) not null unique,
                        password TEXT not null,
                        algorithm varchar(45) not null default 'bcrypt',
                        primary key (id)
);

create table AUTHORITY(
                          id int not null auto_increment,
                          name varchar(45) not null,
                          account_id int not null,
                          primary key (id),
                          foreign key (account_id) references ACCOUNT
);

create table PRODUCT(
                        id int not null auto_increment,
                        name varchar(45) not null,
                        price long not null default 0,
                        currency varchar(45) not null default 'USD',

                        primary key (id)
);