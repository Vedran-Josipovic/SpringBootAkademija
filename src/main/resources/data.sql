CREATE TABLE test(
  id int primary key auto_increment,
  name varchar(100),
  description varchar(255)
);
insert into test(name,description) values ('Pero', 'Gamer 2');
insert into test(name,description) values ('Marko', 'Opisček');
insert into test(name,description) values ('Ivo', 'Predsjednik');


CREATE TABLE pretrage_letova (
    id int PRIMARY KEY auto_increment,
    sifra_polazista CHAR(3),
    sifra_odredista CHAR(3),
    datum_odlaska DATE,
    datum_povratka DATE,
    broj_odraslih INTEGER,

    datum_kreiranja DATE,
    korisnik_kreiranja varchar(50),
    datum_azuriranja DATE,
    korisnik_azuriranja varchar(50)
);


