create table if not exists personnage
(
    id serial not null
        constraint personnage_pk
            primary key,
    prenom varchar,
    nom varchar
);

alter table personnage owner to postgres;

