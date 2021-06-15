CREATE TABLE IF NOT EXISTS items (
    id serial,
    description varchar(500),
    created timestamp,
    is_done bool
);