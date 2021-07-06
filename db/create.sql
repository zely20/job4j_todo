CREATE TABLE IF NOT EXISTS items (
    id serial PRIMARY KEY,
    description text,
    created timestamp,
    is_done bool,
    user_id int not null references users(id)
);

CREATE TABLE IF NOT EXISTS users
(
    id       serial PRIMARY KEY,
    name     text,
    password text
);

create table if not exists categories (
                            id serial PRIMARY KEY,
                            name text)
;

insert into categories values (1, 'WORK');
insert into categories values (2, 'HOME');
insert into categories values (3, 'BISINESS');
