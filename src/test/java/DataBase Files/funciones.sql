USE `SISDI`;
DROP procedure IF EXISTS insertOffice;
DELIMITER $$
USE `SISDI`$$
CREATE PROCEDURE insertOffice (IN offNumber VARCHAR(45),IN reason VARCHAR(45),IN name VARCHAR(45),
 IN incorDate DATE, IN incorTime TIME, 
IN deadLine DATE, IN sessionDate DATE,  IN observations longtext, IN publics TINYINT(4),
IN notified TINYINT(4), IN states INT,
IN type_id int,IN version_id int,
 IN user_id VARCHAR(45),IN receiver_id VARCHAR(45),timeouts_id INT
)

BEGIN
INSERT INTO T_OFFICE (OFFNUMBER,REASON,NAME, INCORDATE,INCORTIME, 
DEADLINE, SESSIONDATE, OBSERVATIONS, PUBLIC, NOTIFIED,  STATE, TYPE_ID,VERSION_ID, USER_ID,RECEIVER_ID,TIMEOUTS_ID) 
VALUES (offNumber, reason, name, incorDate, incorTime, deadLine,
 sessionDate, observations, publics, notified, states, type_id, version_id, user_id ,receiver_id,timeouts_id);
commit; 
end$$ 
DELIMITER ;

USE `SISDI`;
DROP procedure IF EXISTS insertVersion;
DELIMITER $$
USE `SISDI`$$
CREATE PROCEDURE insertVersion (
IN version_id VARCHAR(45),
IN version_number int,
IN version_date DATE,
IN version_time TIME, 
IN version_description VARCHAR(100)
)

BEGIN
INSERT INTO T_VERSION (VERSION_ID,VERSION_NUMBER,VERSION_DATE,VERSION_TIME,VERSION_DESCRIPTION) 
VALUES (version_id, version_number, version_date, version_time, version_description);
commit; 
end$$ 
DELIMITER ;

USE `SISDI`;
DROP procedure IF EXISTS updateOffice;
DELIMITER $$
USE `SISDI`$$
create procedure updateOffice( IN _offNumber VARCHAR(45),IN _reason VARCHAR(45),IN _name VARCHAR(45),
 IN _incorDate DATE, IN _incorTime TIME, 
IN _deadLine DATE, IN _sessionDate DATE,  IN _observations longtext, IN _publics TINYINT(4),
IN _notified TINYINT(4), IN _state INT,
IN _type int,IN _version int,
 IN _user VARCHAR(45),IN _receiver VARCHAR(45)) 
begin
UPDATE T_OFFICE SET REASON= _reason, NAME= _name, DEADLINE=_deadline, SESSIONDATE=_sessionDate, OBSERVATIONS=_observations,PUBLIC=_publics , NOTIFIED=_notified, STATE=_state, 
TYPE_ID=_type, USER_ID=_user, RECEIVER_ID=_receiver WHERE OFFNUMBER=_offNumber;
end$$
DELIMITER ; 



USE `SISDI`;
DROP procedure IF EXISTS updateCodOffice;
DELIMITER $$
USE `SISDI`$$
create procedure updateCodOffice( IN _tempuser VARCHAR(45),IN _indx int) 
begin
DECLARE var varchar(100);
set var=  CONCAT('OFICIO MSPH-','AM-', _indx,'-',YEAR(NOW()));
UPDATE T_OFFICE SET OFFNUMBER=var where USER_ID=_tempuser AND INDX=_indx;
end$$
DELIMITER ; 





alter table T_ROLE auto_increment = 1;
alter table T_DEPARTMENT auto_increment = 1;

insert into T_TYPE (DESCRIPTION) values ('interno');
insert into T_TYPE (DESCRIPTION) values ('externo');



insert into T_STATE (ID, DESCRIPTION)values (0, 'Esperando Respuesta');
insert into T_STATE (ID, DESCRIPTION)values (1, 'Respondido');


insert into T_TEMPUSER(NAME,EMAIL) values ('SUPERUSER','superuser@superuser.com');
insert into T_TEMPUSER(NAME,EMAIL) values ('Concejo Municipal','concejomunicipal@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Secretaria de Alcaldia','alcaldia@sanpablo.go.cr');
insert into T_DEPARTMENT (NAME) values ('SUPERUSER'); # 1
insert into T_DEPARTMENT (NAME) values ('Concejo Municipal'); #2
insert into T_DEPARTMENT (NAME) values ('Alcaldia'); #3
insert into T_DEPARTMENT (NAME) values ('Desarrollo Urbano');#4
insert into T_DEPARTMENT (NAME) values ('Hacienda Municipal'); #5
insert into T_DEPARTMENT (NAME) values ('Servicios Públicos'); #6
insert into T_DEPARTMENT (NAME) values ('Prooveduría'); #7
insert into T_DEPARTMENT (NAME) values ('Recursos Humanos'); #8


insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('superuser@superuser.com','$2y$12$N6OQ0DsyRhYOq/m9AK7GzePyjLGsmiUM3ax0z3xIAFF40vjTgL73q',1,1);

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('concejomunicipal@sanpablo.go.cr','$2a$10$iCDiliiLJjGNB93sNBc.be6suYV/B.2KeklGnEnuRsDzKC2l79bV2',2,1);
insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('alcaldia@sanpablo.go.cr','$2a$10$VJnqKpTeW7FLaSf6/eI6..4w6IAUOVUoVSicZbkGDgpHV2ajFaAny',3,1);
insert into T_ROLE (NAME) values ('superuser');
insert into T_ROLE (NAME) values ('Concejo Municipal');
insert into T_ROLE (NAME) values ('Secretaria de Alcaldia');
insert into T_ROLE (NAME) values('Infraestructura Privada');
insert into T_ROLE (NAME) values('Infraestructura Pública');
insert into T_ROLE (NAME) values('Dirección de Desarrollo humano');
insert into T_ROLE (NAME) values('Dirección de hacienda Muninicipal');
insert into T_ROLE (NAME) values('Dirección de Servicios Públicos');
insert into T_ROLE (NAME) values('Inspector');
insert into T_ROLE (NAME) values('Planificación y Ordenamiento territorial');
insert into T_ROLE (NAME) values('Contabilidad');
insert into T_ROLE (NAME) values('Auxiliar de Contabilidad');
insert into T_ROLE (NAME) values('Gestión de Cobro');
insert into T_ROLE (NAME) values('Tesorería');
insert into T_ROLE (NAME) values('Cajas');
insert into T_ROLE (NAME) values('Valoración de Bienes Inmuebles');
insert into T_ROLE (NAME) values('Perito');
insert into T_ROLE (NAME) values('Archivo Central');
insert into T_ROLE (NAME) values('Centro de Atención Adulto Mayor Miraflores');
insert into T_ROLE (NAME) values('Centro de Atención Adulto Mayor R_d_Ricardo');
insert into T_ROLE (NAME) values('CECUDI');
insert into T_ROLE (NAME) values('Cementerio');
insert into T_ROLE (NAME) values('Centro de Conocimiento (Biblioteca)');
insert into T_ROLE (NAME) values('Centro Cultural');
insert into T_ROLE (NAME) values('Desarrollo social inclusivo');
insert into T_ROLE (NAME) values('Gestión Ambiental');
insert into T_ROLE (NAME) values('Plataforma de Servicios');
insert into T_ROLE (NAME) values('Plataformista');
insert into T_ROLE (NAME) values('Policía Municipal');
insert into T_ROLE (NAME) values('Proveeduría');
insert into T_ROLE (NAME) values('Subproveedora');
insert into T_ROLE (NAME) values('Bodega');
insert into T_ROLE (NAME) values('Planillas');
insert into T_ROLE (NAME) values('Salud Ocupacional');
insert into T_ROLE (NAME) values('Alcalde');
insert into T_ROLE (NAME) values('Vice Alcalde 1');
insert into T_ROLE (NAME) values('Director de asesoria Legal interna');
insert into T_ROLE (NAME) values('Asesoria Legal interna');
insert into T_ROLE (NAME) values('Tecnologías de Información');
insert into T_ROLE (NAME) values('Planificación, Presupuesto y Control');
insert into T_ROLE (NAME) values('Control Interno');
insert into T_ROLE (NAME) values('Recursos Humanos');

insert into T_USER_ROLE (USER_ID,ROLE_NAME) values ('concejomunicipal@sanpablo.go.cr','Concejo Municipal');
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values ('alcaldia@sanpablo.go.cr','Secretaria de Alcaldia');
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values ('superuser@superuser.com','superuser');

#Direccion de desarrorllo urbano
insert into T_TEMPUSER(NAME,EMAIL) values ('Santiago Baizán Hidalgo','desarrollourbano@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Allan Alfaro Arias','infraestructuraprivada@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Oscar Campos Garita','infraestructurapublica@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Miguel Cortés Sánchez','planot@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Jorge Duarte Ramírez','visados@sanpablo.go.cr');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS,ISBOSS) values ('desarrollourbano@sanpablo.go.cr','$2y$12$GXf2OJ06OcHRFoujC7DscewFa8AYqrhlnSd6xWsyb0Z/m5YoNKKDu',4,1,1); # pass:desarrollourbano
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('desarrollourbano@sanpablo.go.cr','Dirección de Desarrollo humano');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('infraestructuraprivada@sanpablo.go.cr','$2y$12$RYOpOCvwQrJV65JXK5qrD.X/ZYVsm8sVO5nNzBx7dB7QDwlMyhhPe',4,1); # pass:infraestructuraprivada
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('infraestructuraprivada@sanpablo.go.cr','Infraestructura Privada');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('infraestructurapublica@sanpablo.go.cr','$2y$12$T3Vb33ERjSk6bdczS7M0i.72Mve0z544LAs1Lo3Hj4da8pAIgKdqO',4,1); # pass:infraestructurapublica
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('infraestructurapublica@sanpablo.go.cr','Infraestructura Pública');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('planot@sanpablo.go.cr','$2y$12$toG1PoV6sDm8PSvXBA6fk.FgFNNbZb19OvSP2nLRWeRBGgjSmTFC.',4,1); # pass:planot
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('planot@sanpablo.go.cr','Planificación y Ordenamiento territorial');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('visados@sanpablo.go.cr','$2y$12$0DALv6Kh4s5teatCpOPgmeAA3EbEbm3RYbdtPfn4dHvkmoypMRewK',4,1); # pass:visados
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('visados@sanpablo.go.cr','Planificación y Ordenamiento territorial');


# Dirección de Hacienda Municipal

insert into T_TEMPUSER(NAME,EMAIL) values ('Marjorie Montoya Gamboa','haciendamunicipal@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Gilberth Chávez','contabilidad@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Alonso Valerio','auxiliarcontabilidad@go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Carolina González Valerio','gestiondecobros@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Johnny Cabalceta Ramírez','asistente.licencias@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Yael Solano Méndez','tesoreria@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Juan Carlos Zúñiga Jimenez','valoracionbienesinmuebles@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Marcial Alpízar Gutiérrez','perito.a@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values ('Carlo Arias Villalobos','perito.b@sanpablo.go.cr');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS,ISBOSS) values ('haciendamunicipal@sanpablo.go.cr','$2y$12$xKiBSkIQYGrIcwZbWKquPesSlc88TQFMdv8f.x2.L5psgGW7T7Hr6',5,1,1); # pass:haciendamunicipal
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('haciendamunicipal@sanpablo.go.cr','Dirección de hacienda Muninicipal');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('contabilidad@sanpablo.go.cr','$2y$12$cV2PKROtqybto5TUqJizbOXOF7I5UX04g8n6ByCPRrRbBBZQYQajW',5,1); # pass:contabilidad
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('contabilidad@sanpablo.go.cr','Contabilidad');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('auxiliarcontabilidad@go.cr','$2y$12$BX.plgUXeQwNWHYPTOp6lexiUOkAMFvhmdRJtPxDS8ftKTZ5kDCdO',5,1); # pass:auxiliarcontabilidad
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('auxiliarcontabilidad@go.cr','Auxiliar de Contabilidad');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('gestiondecobros@sanpablo.go.cr','$2y$12$uKQG2afsHC0YzG31cQ6WWuDCsbrwBCKINOeeAaN6QYiBiHJFEz2cS',5,1); # pass:gestiondecobros
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('gestiondecobros@sanpablo.go.cr','Gestión de Cobro');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values ('asistente.licencias@sanpablo.go.cr','$2y$12$3Xo2qB3jzTnOIEzISWy6fedQviOzz06Fvw39HV0hr1OfnW5WidQ3K',5,1); # pass:asistentelicencias
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('asistente.licencias@sanpablo.go.cr','Gestión de Cobro');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values 
('tesoreria@sanpablo.go.cr','$2y$12$ouEFyuz/OSgnXwGJvUJefOVWD0vMZZUNlKDhXodUvi.Lp/X2cD0eG',5,1); # pass:tesoreria
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('tesoreria@sanpablo.go.cr','Tesorería');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values 
('valoracionbienesinmuebles@sanpablo.go.cr','$2y$12$ppPziOR/CmJMYR2lPRPwGOlTLK.mqNLX/Quzmp0dVz40QcDOhXl5u',5,1); # pass:valoracionbienesinmuebles
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('valoracionbienesinmuebles@sanpablo.go.cr','Valoración de Bienes Inmuebles');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('perito.a@sanpablo.go.cr','$2y$12$YADFgLFA.Vv6yqunsaondeCqUKx7wtb9hmcA5MKl//92464/6VTRu',5,1); # pass:peritoa
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('perito.a@sanpablo.go.cr','Perito');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('perito.b@sanpablo.go.cr','$2y$12$Kb5P5AMYk/ojENbjI9BXX.z1IzEbwaMcUSF0HIghxy6QIdRY4hQIa',5,1); # pass:peritob
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('perito.b@sanpablo.go.cr','Perito');


#####- Servicios Publicos #####-

insert into T_TEMPUSER(NAME,EMAIL) values('Mauricio González González','serviciospublicos@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('José Silva Castillo','archivocentral@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Lizbeth Rodríguez Calderón','centroatencionadultomayor@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Karla Araya Núñez','cecudi@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Vanessa Valverde','cementerio@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Jennifer Conejo Vásquez','centrodeconocimiento@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Sergio Salazar Rivera','administradorcentrocultural@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Ileana Rojas Hernández','centrocultural@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Yamileth Monterey López','desarrollosocialinclusivo@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('David González Ovares','gestionambiental@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Marcela Sancho Villalobos','plataforma@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Maureen Gutiérrez','plataforma.a@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Carlos García Arguedas','plataforma.b@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Patricia Zúñiga','plataforma.c@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Luis Moncada Espinoza','policiamunicipal@sanpablo.go.cr');



insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS,ISBOSS) values
 ('serviciospublicos@sanpablo.go.cr','$2y$12$sv9mXszFFms8Vsp2gtJ21eJcJKq/dd7aTdB35Q6QHmqtynRdAsruW',6,1,1); # pass:serviciospublicos
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('serviciospublicos@sanpablo.go.cr','Dirección de Servicios Públicos');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('archivocentral@sanpablo.go.cr','$2y$12$9knVYUk7M6gUmt9XIdHO/OMWZURJOmlAp8hcX/pNCtoRhiz6xOSqm',6,1); # pass:archivocentral
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('archivocentral@sanpablo.go.cr','Archivo Central');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('centroatencionadultomayor@sanpablo.go.cr','$2y$12$qPjPC8ijCMmOezlddMxA5e5tSiQ/ekgB7DMBiLj.L9xr6/kOv2MvC',6,1); # pass:centroatencionadultomayor
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('centroatencionadultomayor@sanpablo.go.cr','Centro de Atención Adulto Mayor Miraflores');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('cecudi@sanpablo.go.cr','$2y$12$I9z7f6EL6mv6HYp37TEGOOetQLGKo9ld8VGvFAt4JxkCStmb6yhii',6,1); # pass:cecudi
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('cecudi@sanpablo.go.cr','CECUDI');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('cementerio@sanpablo.go.cr','$2y$12$6cQe24xuooaWHhDAI27ouu67ZKWcSwYizRsflPK34/oBSlvKoZRCy',6,1); # pass:cementerio
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('cementerio@sanpablo.go.cr','Cementerio');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('centrodeconocimiento@sanpablo.go.cr','$2y$12$XUhvsKDnttd8plXdBtHSY./YTy/GcOdZ16upoEj/EUAxdb6Tg.Qje',6,1); # pass:centrodeconocimiento
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('centrodeconocimiento@sanpablo.go.cr','Centro de Conocimiento (Biblioteca)');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('administradorcentrocultural@sanpablo.go.cr','$2y$12$H0OPp7/F4r3fzwYdZg.feuCs4nqk8mBUeZUi.AkGEsBWgXWMD4Cqa',6,1); # pass:administradorcentrocultural
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('administradorcentrocultural@sanpablo.go.cr','Centro Cultural');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('desarrollosocialinclusivo@sanpablo.go.cr','$2y$12$j7Oe/V2VvZF1rFx5GMBwDesCdlGfFk4nMTA.dOv3Hzd6Yig4TfmF.',6,1); # pass:desarrollosocialinclusivo
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('desarrollosocialinclusivo@sanpablo.go.cr','Desarrollo social inclusivo');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('gestionambiental@sanpablo.go.cr','$2y$12$/aRrJ2B6P5U1Z7cf6R.a9OMoZXuD/Jj8rezPd.2VYgkbQaQy4NcTC',6,1); # pass:gestionambiental
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('gestionambiental@sanpablo.go.cr','Gestión Ambiental');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('plataforma@sanpablo.go.cr','$2y$12$/zUT.lk5a4HgdlnsdZteL.of/cq0vcrZ2FKBp87vCrLH7NO6K67ei',6,1); # pass:plataforma
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('plataforma@sanpablo.go.cr','Plataforma de Servicios');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('plataforma.a@sanpablo.go.cr','$2y$12$vbOYKP4afxEYdzZvDGgyuegCt3iwXTDEAHrFGjqW6Udn9A3ojTlfq',6,1); # pass:plataformaa
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('plataforma.a@sanpablo.go.cr','Plataformista');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('plataforma.b@sanpablo.go.cr','$2y$12$3pm1JS0KZ9Fq6p3td1c0PONKBlIIzq832CbOIobqa4GXYftMT3zY2',6,1); # pass:plataformab
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('plataforma.b@sanpablo.go.cr','Plataformista');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('plataforma.c@sanpablo.go.cr','$2y$12$3yoRAm7cqUjuunPtngwQQuBavPzCPi08eabm6VlKCZSeXO.rOdhr6',6,1); # pass:plataformac
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('plataforma.c@sanpablo.go.cr','Plataformista');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('policiamunicipal@sanpablo.go.cr','$2y$12$C1F.BejEy.AJjkK6JU3mSuJ2HPFyTaaYgIvA1uRs/kmIxnAMoiXhS',6,1); # pass:policiamunicipal
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('policiamunicipal@sanpablo.go.cr','Policía Municipal');










#### Proveduria #####
insert into T_TEMPUSER(NAME,EMAIL) values('Oscar Hidalgo Mena','proveeduria1@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Adriana Benavides Vargas','proveeduria2@sanpablo.go.cr');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS,ISBOSS) values
 ('proveeduria1@sanpablo.go.cr','$2y$12$glSbeA/7oQ4ppjMjQH7I1.LmnqL7GJMMhJWXpBPAr90LaWvjQW3Hy',7,1,1); #pass:proveeduria1
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('proveeduria1@sanpablo.go.cr','Proveeduría');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('proveeduria2@sanpablo.go.cr','$2y$12$Kx0iSLCqJXyIzVqDub0x0.bu89dBP1l8bD4/uVWPtWw4Mgs0YRrla',7,1); #pass:proveeduria2
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('proveeduria2@sanpablo.go.cr','Subproveedora');




##Recursos Humanos####

insert into T_TEMPUSER(NAME,EMAIL) values('Diana Arias','recursoshumanos@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Planillas','nomina@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Jorge Slon Jaikel','saludocupacional@sanpablo.go.cr');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS,ISBOSS) values
 ('recursoshumanos@sanpablo.go.cr','$2y$12$erDRhHdAorRpNTfzRddr6uX1D.BBhBbbhyyY6g0C.lQMCWwYRy8G6',8,1,1); #pass:recursoshumanos
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('recursoshumanos@sanpablo.go.cr','Recursos Humanos');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('nomina@sanpablo.go.cr','$2y$12$J3UeKhGvzHuskW9b0Gq3LObvXcaB85XHLZvIvOv.vgCgHHPEXa/ke',8,1); #pass:nomina
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('nomina@sanpablo.go.cr','Planillas');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('saludocupacional@sanpablo.go.cr','$2y$12$BE3FgDmRKoMT2i2xuY2ZiODIxcvu7kj8Kq5zAjmZwufmEfDRX7m.W',8,1); #pass:saludocupacional
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('saludocupacional@sanpablo.go.cr','Salud Ocupacional');




insert into T_TEMPUSER(NAME,EMAIL) values('Luis Fernando Vargas Mora','asesorialegal@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Joseph Granda Vargas','informatica@sanpablo.go.cr');
# insert into T_TEMPUSER(NAME,EMAIL) values('Gilberth Acuña Cerdas','planificacionpresupuestocontrol@sanpablo.go.cr');
insert into T_TEMPUSER(NAME,EMAIL) values('Ismael Salazar Oviedo','controlinterno@sanpablo.go.cr');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('asesorialegal@sanpablo.go.cr','$2y$12$w7UGsHJM0NfW/xQROeg8x.xjX.S1XYs7F1gFccHcvqy.b6k7NjOxK',3,1); #pass:asesorialegal
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('asesorialegal@sanpablo.go.cr','Director de asesoria Legal interna');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('informatica@sanpablo.go.cr','$2y$12$B3VstPqR8DzS4xcVMv.2ZukKtskcNW5cDxL4vYN0XqHi4MgSOq0uC',3,1); #pass:informatica
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('informatica@sanpablo.go.cr','Tecnologías de Información');

#insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
# ('planificacionpresupuestocontrol@sanpablo.go.cr','$2y$12$ywJ1xt5cpjgt3.JBKHr9teDSyq8xAlK4oi2uyTxnJmyBe.hd9IWeq',3,1); 
#  pass:planificacionpresupuestocontrol
#insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('planificacionpresupuestocontrol@sanpablo.go.cr','Planificación, Presupuesto y Control');

insert into T_USER (TEMPUSER,PASSWORD,DEPARTMENT,STATUS) values
 ('controlinterno@sanpablo.go.cr','$2y$12$PP.ROST1M6niteGtzRK/f.qpbOOAkLk8eZXonQgJC1uKE9QdIlpsu',3,1); #pass:controlinterno
insert into T_USER_ROLE (USER_ID,ROLE_NAME) values('controlinterno@sanpablo.go.cr','Control Interno');
INSERT INTO `sisdi`.`t_timeouts` (`ID`, `COD`, `LIMITDATE`, `LIMITTIME`, `DEPARTMENT`, `TYPE`) VALUES ('1', 'SOLICITUD VACACIONES', '2020/10/10', '11:00', '1', 'Oficio');

INSERT INTO `sisdi`.`t_version` (`ID`, `VERSION_ID`, `VERSION_NUMBER`, `VERSION_DATE`, `VERSION_TIME`, `VERSION_DESCRIPTION`) VALUES ('1', '\"Version id\"', '1', '2020-10-01', '11:00', '\"Primera version descripcion\"');
call sisdi.insertOffice("OFICIO MSPH-AM-CCPJ-NE-001-2020", "Plan covid", "PLAN COVID", "2020/10/1", "11:00", "2020/10/3", "2020/10/1", "texto largo", 1, 1, 1, 1, 1, "superuser@superuser.com", "concejomunicipal@sanpablo.go.cr",1);

call sisdi.insertVersion('ver2', 1, '2020/10/1', '11:00', 'version nueva ');
call sisdi.updateOffice('OFICIO MSPH-AM-CCPJ-NE-001-2020', 'Asamblea', 'Oficio Asamblea', '2020/10/20', '12:00', '2020/10/30', '2020/10/20', 'texto largo', 1, 1, 0, 2, 1, 'asistente.licencias@sanpablo.go.cr', 'gestiondecobros@sanpablo.go.cr');