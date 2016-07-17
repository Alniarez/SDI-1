--	Este script prepara la base de datos para probar todos los casos de uso.
--	Es necesario el esquema base de datos original; este Script limpia y crea datos unicamente.

--	El usuario Pedro (login: user1, pass: user1) tiene un viaje en el que tiene varias peticiones.
--	Para ese viaje hay tanto un pasajero aceptado como un pasajero rechazado.

--	Existen más viajes disponibles en la base de datos para realizar peticiones con otros usuarios.
--	para todos los usuarios su clave es su login.

DELETE FROM TRATINGS;
DELETE FROM TSEATS;
DELETE FROM TAPPLICATIONS;
DELETE FROM TTRIPS;
DELETE FROM TUSERS;

--Usuarios
INSERT INTO TUSERS VALUES(101,'user1@mail.com','user1','Pedro','user1',0,'Alvarez');
INSERT INTO TUSERS VALUES(102,'user2@mail.com','user2','Luisa','user2',0,'Perez');
INSERT INTO TUSERS VALUES(103,'user3@mail.com','user3','Juan','user3',0,'Garcia');
INSERT INTO TUSERS VALUES(104,'user4@mail.com','user4','Alfonso','user4',0,'Gonzalez');
INSERT INTO TUSERS VALUES(105,'user5@mail.com','user5','Fernando','user5',0,'Herrero');
INSERT INTO TUSERS VALUES(106,'user6@mail.com','user6','Sixto','user6',0,'Miranda');
INSERT INTO TUSERS VALUES(107,'user7@mail.com','user7','Paula','user7',1,'Lucrecio');

--Viajes
INSERT INTO TTRIPS VALUES(201,'2047-07-18 20:00:00.000000',4,'2030-02-20 00:00:00.000000','Comentario','DireccionOrigen','CiudadOrigen1','PaisOrigen','EstadoOrigen',45.0E0,-45.0E0,'CodigoPostalOrigen','2047-07-18 08:00:00.000000','DireccionDestino','CiudadDestino6','PaisDestino','EstadoDestino',-1.5E0,1.5E0,'CodigoPostalDestino',45.6E0,5,0,101);
INSERT INTO TTRIPS VALUES(203,'2047-07-18 20:00:00.000000',5,'2030-02-20 00:00:00.000000','Comentario','DireccionOrigen','CiudadOrigen3','PaisOrigen','EstadoOrigen',45.0E0,-45.0E0,'CodigoPostalOrigen','2047-07-18 08:00:00.000000','DireccionDestino','CiudadDestino4','PaisDestino','EstadoDestino',-1.5E0,1.5E0,'CodigoPostalDestino',45.6E0,5,0,102);
INSERT INTO TTRIPS VALUES(204,'2047-07-18 20:00:00.000000',5,'2030-02-20 00:00:00.000000','Comentario','DireccionOrigen','CiudadOrigen4','PaisOrigen','EstadoOrigen',45.0E0,-45.0E0,'CodigoPostalOrigen','2047-07-18 08:00:00.000000','DireccionDestino','CiudadDestino3','PaisDestino','EstadoDestino',-1.5E0,1.5E0,'CodigoPostalDestino',45.6E0,5,0,103);
INSERT INTO TTRIPS VALUES(205,'2047-07-18 20:00:00.000000',5,'2030-02-20 00:00:00.000000','Comentario','DireccionOrigen','CiudadOrigen5','PaisOrigen','EstadoOrigen',45.0E0,-45.0E0,'CodigoPostalOrigen','2047-07-18 08:00:00.000000','DireccionDestino','CiudadDestino2','PaisDestino','EstadoDestino',-1.5E0,1.5E0,'CodigoPostalDestino',45.6E0,5,0,104);
INSERT INTO TTRIPS VALUES(206,'2047-07-18 20:00:00.000000',5,'2030-02-20 00:00:00.000000','Comentario','DireccionOrigen','CiudadOrigen6','PaisOrigen','EstadoOrigen',45.0E0,-45.0E0,'CodigoPostalOrigen','2047-07-18 08:00:00.000000','DireccionDestino','CiudadDestino1','PaisDestino','EstadoDestino',-1.5E0,1.5E0,'CodigoPostalDestino',45.6E0,5,0,105);

--Peticiones
INSERT INTO TAPPLICATIONS VALUES(201,102)
INSERT INTO TAPPLICATIONS VALUES(201,103)
INSERT INTO TAPPLICATIONS VALUES(201,104)

--Asientos
INSERT INTO TSEATS VALUES('',0,201,105)
INSERT INTO TSEATS VALUES('',1,201,106)