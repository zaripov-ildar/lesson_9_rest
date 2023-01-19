create table if not exists users(id bigint auto_increment primary key, username varchar(30) unique, password varchar(50), email    varchar(30));
create table if not exists roles(id   bigint auto_increment primary key,role varchar(30));
create table if not exists users_roles(user_id bigint, role_id bigint);
insert into users(username, password, email) VALUES ( 'manager', '42', 'picard@mail.com' ), ('admin', '42', 'kirk@mail.com'), ('superadmin', '42', 'archer@mail.com');
insert into roles(role) values ( 'ROLE_MANAGER' ), ('ROLE_ADMIN'),('ROLE_SUPERADMIN');
insert into users_roles(user_id, role_id) VALUES ( 1,1 ),(2,2),(3,3);
