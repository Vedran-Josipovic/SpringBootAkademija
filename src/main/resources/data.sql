CREATE TABLE test
(
    id          int primary key auto_increment,
    name        varchar(100),
    description varchar(255)
);
insert into test(name, description)
values ('Pero', 'Gamer 2');
insert into test(name, description)
values ('Marko', 'Opisček');
insert into test(name, description)
values ('Ivo', 'Predsjednik');

CREATE TABLE pretrage_letova
(
    id                  int PRIMARY KEY auto_increment,
    sifra_polazista     CHAR(3),
    sifra_odredista     CHAR(3),
    datum_odlaska       DATE,
    datum_povratka      DATE,
    broj_odraslih       INTEGER,

    datum_kreiranja     DATE,
    korisnik_kreiranja  varchar(50),
    datum_azuriranja    DATE,
    korisnik_azuriranja varchar(50)
);

CREATE TABLE rezultati_pretrage
(
    id                                 INT PRIMARY KEY auto_increment,

    polazak_sifra_aerodroma_polazak    varchar(3),
    polazak_sifra_aerodroma_odrediste  varchar(3),
    polazak_datum_polaska              varchar(25),
    polazak_datum_dolaska              varchar(25),

    povratak_sifra_aerodroma_polazak   varchar(3),
    povratak_sifra_aerodroma_odrediste varchar(3),
    povratak_datum_polaska             varchar(25),
    povratak_datum_dolaska             varchar(25),

    prijevoznik                        varchar(50),
    cijena                             varchar(10),

    id_pretrage int,

    datum_kreiranja                    DATE,
    korisnik_kreiranja                 varchar(50),
    datum_azuriranja                   DATE,
    korisnik_azuriranja                varchar(50)
);

alter table rezultati_pretrage add foreign key (id_pretrage) references pretrage_letova(id);


