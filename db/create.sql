CREATE TABLE IF NOT EXISTS items (
    id serial PRIMARY KEY,
    description text,
    created timestamp,
    is_done bool
);