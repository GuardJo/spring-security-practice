insert into dummy_user(username, password)
VALUES ('test1', '{noop}1234');
insert into dummy_user(username, password)
values ('test2', '{bcrypt}1234');
insert into dummy_user(username, password)
values ('test3', '{noop}1234');
insert into dummy_authority(username, authority)
VALUES ('test1', 'READ');
insert into dummy_authority(username, authority)
VALUES ('test2', 'WRITE');
insert into dummy_authority(username, authority)
VALUES ('test3', 'WRITE');