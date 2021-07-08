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

CREATE TABLE items_categories(
                                     item_id int NOT NULL,
                                     categories_id int NOT NULL,
                                     FOREIGN KEY (item_id) REFERENCES items(id),
                                     FOREIGN KEY (categories_id) REFERENCES categories(id),
                                     UNIQUE (item_id, categories_id)
);

insert into categories values (1, 'WORK');
insert into categories values (2, 'HOME');
insert into categories values (3, 'BISINESS');
