DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_seq;
DROP TABLE IF EXISTS cities;
DROP TYPE IF EXISTS user_flag;
DROP TYPE IF EXISTS group_type;

CREATE TYPE user_flag AS ENUM ('active', 'deleted', 'superuser');

CREATE SEQUENCE user_seq START 100000;

CREATE TABLE cities
(
    id   TEXT PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE users
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
    full_name TEXT      NOT NULL,
    email     TEXT      NOT NULL,
    flag      user_flag NOT NULL,
    city      TEXT REFERENCES cities
);
CREATE UNIQUE INDEX email_idx ON users (email);

CREATE TABLE projects
(
    name        TEXT PRIMARY KEY,
    description TEXT NOT NULL
);
CREATE UNIQUE INDEX project_name_idx ON projects (name);

CREATE TYPE group_type AS ENUM ('CURRENT', 'FINISHED', 'REGISTERING');

CREATE TABLE groups
(
    name    TEXT PRIMARY KEY,
    project TEXT       NOT NULL REFERENCES projects,
    type    group_type NOT NULL,
    UNIQUE (project, name)
);

CREATE TABLE user_group
(
    user_id  INTEGER REFERENCES users,
    group_id TEXT REFERENCES groups,
    UNIQUE (user_id, group_id)
);