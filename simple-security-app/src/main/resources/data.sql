insert into ACCOUNT(USERNAME, PASSWORD, ALGORITHM)
VALUES ('Kyeongho', '$2y$04$dzgYJtybEkOUUv7NrUGfEemQmaziewTZq8b4dbBtxJuU4MW.i8vEW', 'bcrypt');
insert into ACCOUNT(username, password, algorithm)
VALUES ('Kyeongho2', '$2y$04$dzgYJtybEkOUUv7NrUGfEemQmaziewTZq8b4dbBtxJuU4MW.i8vEW', 'bcrypt');

insert into AUTHORITY(name, account_id)
VALUES ('READ', 1);
insert into AUTHORITY(name, account_id)
VALUES ('WRITE', 1);
insert into AUTHORITY(name, account_id)
VALUES ('READ', 2);

insert into PRODUCT(name, price, currency)
VALUES ('Chocolate', 10, 'USD');
insert into PRODUCT(name, price, currency)
VALUES ('Eclipse Candy', 25000, 'WON');