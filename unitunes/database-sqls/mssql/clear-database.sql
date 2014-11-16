delete from ALBUMS_MEDIAS;
delete from ALBUMS;
delete from USER_FAVORITE_MEDIAS;
delete from USER_PUBLISHED_MEDIAS;
delete from USER_PURCHASED_MEDIAS;
delete from MEDIAS_CONTENTS;
delete from MOVEMENTS;
delete from MEDIAS;
delete from MEDIAS_CATEGORIES;
delete from USERS;

DBCC CHECKIDENT (USERS, RESEED, 0);
insert into USERS values ('admin@unitunes.com', 'Admin', '123456', 0, 0);


insert into medias_categories values ('Blues', 0);
insert into medias_categories values ('Clássica', 0);
insert into medias_categories values ('Dance', 0);
insert into medias_categories values ('Hip-Hop', 0);
insert into medias_categories values ('Jazz', 0);
insert into medias_categories values ('Metal', 0);
insert into medias_categories values ('Natal', 0);
insert into medias_categories values ('Rock', 0);

insert into medias_categories values ('Artes', 1);
insert into medias_categories values ('Biografias', 1);
insert into medias_categories values ('Esportes', 1);
insert into medias_categories values ('Ficção', 1);
insert into medias_categories values ('Humor', 1);
insert into medias_categories values ('Informática', 1);
insert into medias_categories values ('Negócios', 1);
insert into medias_categories values ('Política', 1);
insert into medias_categories values ('Romance', 1);
insert into medias_categories values ('Saúde', 1);

insert into medias_categories values ('Ação', 2);
insert into medias_categories values ('Comédia', 2);
insert into medias_categories values ('Documentário', 2);
insert into medias_categories values ('Drama', 2);
insert into medias_categories values ('Esportes', 2);
insert into medias_categories values ('Infantil', 2);
insert into medias_categories values ('Musical', 2);
insert into medias_categories values ('Romance', 2);
insert into medias_categories values ('Terror', 2);

insert into medias_categories values ('Artes', 3)
insert into medias_categories values ('Esportes', 3);
insert into medias_categories values ('Games', 3);
insert into medias_categories values ('Infantil', 3);
insert into medias_categories values ('História', 3);
insert into medias_categories values ('Humor', 3);
insert into medias_categories values ('Música', 3);
insert into medias_categories values ('Notícias', 3);
insert into medias_categories values ('Saúde', 3);
insert into medias_categories values ('Tecnologia', 3);
