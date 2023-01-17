CREATE TABLE IF NOT EXISTS users(    id       bigint auto_increment primary key,    username varchar(30) not null unique,    password varchar(80) not null,    email    varchar(50));
CREATE TABLE IF NOT EXISTS roles(    id   bigint auto_increment primary key,    name varchar(50));
CREATE TABLE IF NOT EXISTS users_roles(    user_id bigint not null,    role_id bigint not null,    foreign key (user_id) references users (id),    foreign key (role_id) references roles (id));
insert into roles(name) VALUES ('ROLE_MANAGER'),       ('ROLE_ADMIN'),       ('ROLE_SUPERADMIN');
insert into users(username, password, email) values ('manager', '$2a$12$umWFmDZ.25wkMM23sNUDdeGHWUo596Pfq6Jjyu9cfJIDh.SKcTO92', 'manager@mail.com'),       ('admin', '$2a$12$umWFmDZ.25wkMM23sNUDdeGHWUo596Pfq6Jjyu9cfJIDh.SKcTO92', 'admin@mail.com'),       ('superadmin', '$2a$12$umWFmDZ.25wkMM23sNUDdeGHWUo596Pfq6Jjyu9cfJIDh.SKcTO92', 'superadmin@email.com');
insert into users_roles(user_id, role_id)VALUES (1, 1),       (2, 2),       (3, 3);
