create sequence hibernate_sequence start 1 increment 1;

create table department (
    id int8 not null,
    name varchar(255) not null,
    author_id int8,
    primary key (id)
);

create table employee (
    id int8 not null,
    name varchar(255) not null,
    salary float8 not null,
    author_id int8,
    department_id int8 not null,
    primary key (id)
);

create table logins (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    access_level varchar(255)
);

alter table if exists department add constraint dep_author_fk foreign key (author_id) references logins;
alter table if exists employee add constraint employee_author_fk foreign key (author_id) references logins;
alter table if exists employee add constraint employee_dep_fk foreign key (department_id) references department;
alter table if exists user_role add constraint user_role_user_fk foreign key (user_id) references logins;