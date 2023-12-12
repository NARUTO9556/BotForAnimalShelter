--liquibase formatter sql

--changeset vaverin:1
create table  notification
(
    id                  bigserial   primary key,
    chat_id             bigint,
    notification_text   text,
    date_time           timestamp
);

create table pet
(
    id                  bigserial   primary key,
    pet_name            text,
    age                 integer,
    photo               bytea,
    kind_of_animal      text,
    animal_breed        text,
    shelter_id          bigint,
    customer_id         bigint
);
create table connection
(
    id                  bigserial   primary key,
    chat_id             bigint,
    pet_id              bigint,
    date_time           timestamp
);
create table volunteer
(
    id                  bigint      primary key,
    name_volunteer      text,
    chat_id             text,
    phone_number        text
);
create table shelter
(
    id                  bigserial   primary key,
    name_shelter        text,
    address             text,
    info                text,
    map                 bytea
);
create table customer
(
    id                  bigserial   primary key,
    name_customer       text,
    chat_id             text,
    phone_number        text
);
create table report
(
    id                  bigserial   primary key,
    chat_id             bigint,
    text_report         text,
    date_report         timestamp,
    file_path           text,
    file_size           bigint,
    media_type          text,
    photo               bytea,
    pet_id              bigint
);