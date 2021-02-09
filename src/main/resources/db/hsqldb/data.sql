INSERT INTO genres VALUES (1, 'hombre');
INSERT INTO genres VALUES (2, 'mujer');

INSERT INTO users VALUES ('admin1', '2010-09-07', 'trainer@hotmail.com', 'true', 'Ricardo', 'Pomelo', 'admin1', '628718902', 1);
INSERT INTO users VALUES ('Pelegrini', '1967-09-07', 'pelegrini@hotmail.com', 'true', 'Ricardo', 'Pomelo', 'Pelegrini', '628518902', 1);
INSERT INTO users VALUES ('Lopetegui', '1968-09-07', 'lopetegui@hotmail.com', 'true', 'Ricardo', 'Pomelo', 'Lopetegui', '628618902', 1);
INSERT INTO users VALUES ('Usuario1', '2011-02-17', 'usuario1@gmail.com', 'true', 'Usuario', 'Uno', 'usuario', '628718902', 1);
INSERT INTO users VALUES ('Alexka', '1945-11-03', 'alexka@gmail.com', 'true', 'Alexka', 'Petrotsky', 'alex1945', '157256830', 2);
INSERT INTO users VALUES ('Entrenador1', '1975-11-03', 'entrenador@gmail.com', 'true', 'Entrenador', 'Uno', 'entrenador', '157252830', 2);
INSERT INTO users VALUES ('Mario', '1956-01-13', 'mario@gmail.com', 'true', 'Mario', 'Buichiti', 'mario', '157251830', 1);
INSERT INTO users VALUES ('Marta', '2000-06-24', 'marto@gmail.com', 'true', 'Marta', 'Ortiz', 'marta', '137251830', 1);

INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
INSERT INTO authorities(id,username,authority) VALUES (2,'Pelegrini','admin');
INSERT INTO authorities(id,username,authority) VALUES (3,'Lopetegui','admin');
INSERT INTO authorities(id,username,authority) VALUES (4,'Entrenador1','admin');
INSERT INTO authorities(id,username,authority) VALUES (5,'Usuario1','user');
INSERT INTO authorities(id,username,authority) VALUES (6,'Alexka','admin');
INSERT INTO authorities(id,username,authority) VALUES (7,'Mario','user');
INSERT INTO authorities(id,username,authority) VALUES (8,'Marta','user');

INSERT INTO noticias VALUES (1, '2010-09-07','texto','titulo' );
INSERT INTO noticias VALUES (2, '2010-10-13', 'Se suspende la liga por causas externas al evento','La liga se suspende');
INSERT INTO noticias VALUES (3, '2010-10-14', 'Debido a la insistencia de los aficionados, al final se retomaran los partidos de la Liga','La liga VUELVE');
INSERT INTO noticias VALUES (4, '2015-03-13', 'SUCESO IMPACTANTE para los aficionados, Messi anuncio a traves de sus redes sociales que abandonaba el barcelona para competir en el mundial.','EXCLUSIVA: Messi se va del Barcelona');
INSERT INTO noticias VALUES (5, '2020-01-19', 'Gracias a la cuantiosa inversion de un magnate chino, el Betis por fin podra retomar la reforma de su estadio','El Betis anuncia reforma de su estadio');
INSERT INTO noticias VALUES (6, '2020-10-13', 'El gran magnate chino que iba a realizar la inversion al final retiro la oferta, prefirio invertir el dinero en comprar una empresa de tecnologia: Nokia','Se suspende la reforma del estadio del Betis');
INSERT INTO noticias VALUES (7, '2018-03-05', 'Paliza de los Verdiblancos, un partido que sera memorable, con un resultado final de 11-0 a favor del Betis','Resultados Derbi Sevilla-Betis');
INSERT INTO noticias VALUES (8, '2019-09-02','Fernando Alonso cambia su carrera al Futbol','El conocido piloto de carreras ha decidido dar un vuelco a su vida y desde a partir de hoy competira como jugador en el Real Betis Balonpie' );

INSERT INTO competiciones VALUES(1,'La Liga');
INSERT INTO competiciones VALUES(2,'Premier League');
INSERT INTO competiciones VALUES(3,'Bundesliga');

INSERT INTO entrenador VALUES(1,'Pelegrini');
INSERT INTO entrenador VALUES(2,'Lopetegui');
INSERT INTO entrenador VALUES(3,'Entrenador1');
INSERT INTO entrenador VALUES(4,'Alexka');

INSERT INTO equipos VALUES (1, 'Benito Villamarin', 'Betis',1,1);
INSERT INTO equipos VALUES (2, 'Sanchez Pizjuan', 'Sevilla',1,2);
INSERT INTO equipos VALUES (3, 'Barcelona', 'Barcelona',1,3);
INSERT INTO equipos VALUES (4, 'Milan Stadium', 'Milan FC',1,4);

INSERT INTO jugador VALUES (1, 'Rodriguez', '93725381K', '1996-09-07', 'España', 'Daniel', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (3, 'Perez', '93725382K', '1986-09-17', 'España', 'Paco', FALSE, 1, 0, 1);
INSERT INTO jugador VALUES (4, 'Gutierrez', '93425381K', '19960-03-12', 'España', 'Jose', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (5, 'Gunch', '93725681K', '1993-02-27', 'Filipinas', 'Krong', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (6, 'Alonso', '93727381K', '1999-09-09', 'España', 'Fernando', FALSE, 2, 0, 1);
INSERT INTO jugador VALUES (7, 'Quijano', '95725381K', '1996-09-17', 'Mexico', 'Beltro', TRUE, 0, 0, 1);
INSERT INTO jugador VALUES (8, 'Julien', '93725381S', '1996-09-07', 'Paises Bajos', 'Maluel', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (9, 'Miramocho', '93725381E', '1996-09-07', 'Peru', 'Andres', FALSE, 5, 0, 1);
INSERT INTO jugador VALUES (10, 'Sagazi', '93725381W', '1996-09-07', 'Italia', 'Mario', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (11, 'Libre', '93725381H', '1996-09-07', 'España', 'Rodolfo', FALSE, 4, 0, 1);
INSERT INTO jugador VALUES (12, 'Martin', '93725381J', '1996-09-07', 'España', 'Ernesto', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (13, 'Villazuelo', '93725381B', '1996-09-07', 'España', 'Edmundo', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (14, 'Verne', '93725381Y', '1996-09-07', 'Francia', 'Julio', FALSE, 0, 1, 1);
INSERT INTO jugador VALUES (58, 'Rojo', '16790381W', '1997-09-03', 'Italia', 'JugadorTarjRoja', FALSE, 3, 1, 4);
INSERT INTO jugador VALUES (59, 'Amarillo', '13241381Q', '1997-09-03', 'Italia', 'JugadorTarjAm', FALSE, 5, 0, 4);
INSERT INTO jugador VALUES (60, 'Lesion', '13625388A', '1997-09-03', 'Italia', 'JugadorLesionado', TRUE, 2, 0, 4);

INSERT INTO jugador VALUES (15, 'Lopez', '13625381K', '1993-09-07', 'España', 'Jose', TRUE, 0, 1, 2);
INSERT INTO jugador VALUES (16, 'Martinez', '14625381K', '1993-09-07', 'España', 'Francisco', FALSE, 3, 0, 2);
INSERT INTO jugador VALUES (17, 'Hidalgo', '13626581K', '1993-09-07', 'España', 'Manuel', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (18, 'Garcia', '13625341K', '1993-09-07', 'Inglaterra', 'Kirby', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (19, 'Lopez', '13665381K', '1993-09-07', 'Japon', 'Shigushi', FALSE, 2, 0, 2);
INSERT INTO jugador VALUES (20, 'Parker', '13628981K', '1993-09-07', 'EEUU', 'Peter', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (21, 'Kithduitnze', '13583751K', '1960-09-17', 'Polonia', 'Krit', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (22, 'Bautitsther', '13625698K', '1973-09-07', 'Canada', 'Joahn', FALSE, 5, 0, 2);
INSERT INTO jugador VALUES (23, 'Merez', '13625481K', '2000-09-17', 'Brasil', 'Jonny', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (24, 'Linista', '13215381K', '1999-09-04', 'Argentina', 'Elvio', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (25, 'Lopez', '13625338K', '1987-09-07', 'España', 'Luisma', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (26, 'Kameni', '13625781K', '1998-09-06', 'Chad', 'Susano', FALSE, 4, 0, 2);
INSERT INTO jugador VALUES (27, 'Giorgoni', '13625212K', '1956-09-09', 'Grecia', 'Teocracio', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (28, 'Hufjgefkoojf', '94525381K', '1993-09-27', 'Alemania', 'Gut', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (29, 'Lopez', '13626381K', '1996-09-04', 'España', 'Bambolero', FALSE, 0, 1, 2);
INSERT INTO jugador VALUES (30, 'Hacha', '13625381J', '1997-09-03', 'España', 'Hector', FALSE, 0, 0, 2);
INSERT INTO jugador VALUES (55, 'Rojo', '16790377W', '1997-09-03', 'Italia', 'JugadorTarjRoja', FALSE, 3, 1, 4);
INSERT INTO jugador VALUES (56, 'Amarillo', '13241111Q', '1997-09-03', 'Italia', 'JugadorTarjAm', FALSE, 5, 0, 4);
INSERT INTO jugador VALUES (57, 'Lesion', '13625123A', '1997-09-03', 'Italia', 'JugadorLesionado', TRUE, 2, 0, 4);

INSERT INTO jugador VALUES (43, 'Messi', '13625381M', '1997-09-03', 'Argentina', 'Lionel', FALSE, 2, 0, 3);
INSERT INTO jugador VALUES (31, 'Martin', '13625465J', '1997-09-03', 'España', 'Julio', TRUE, 0, 0, 3);
INSERT INTO jugador VALUES (32, 'Soto', '13625383S', '1997-09-03', 'España', 'Marcos', FALSE, 0, 1, 3);
INSERT INTO jugador VALUES (33, 'Sanchez', '13625385X', '1997-09-03', 'España', 'Javier', FALSE, 0, 0, 3);
INSERT INTO jugador VALUES (34, 'Marquez', '13625381A', '1997-09-03', 'España', 'Fernando', FALSE, 0, 0, 3);
INSERT INTO jugador VALUES (35, 'Fernandez', '13625367U', '1997-09-03', 'España', 'Enrique', FALSE, 0, 0, 3);
INSERT INTO jugador VALUES (36, 'Santo', '13625386H', '1997-09-03', 'España', 'Pablo', FALSE, 5, 0, 3);
INSERT INTO jugador VALUES (37, 'Jimenez', '13625385F', '1997-09-03', 'España', 'Gonzalo', FALSE, 0, 0, 3);
INSERT INTO jugador VALUES (38, 'Portillo', '13625385N', '1997-09-03', 'España', 'Rodrigo', FALSE, 0, 0, 3);
INSERT INTO jugador VALUES (39, 'Birley', '13625311S', '1997-09-03', 'Portugal', 'Justin', FALSE, 2, 0, 3);
INSERT INTO jugador VALUES (40, 'Burbone', '13625381B', '1997-09-03', 'Polonia', 'Hyldre', FALSE, 0, 0, 3);
INSERT INTO jugador VALUES (41, 'Sanatori', '13625789M', '1997-09-03', 'Japon', 'Kobayashi', FALSE, 0, 0, 3);
INSERT INTO jugador VALUES (42, 'Berlusconi', '13625381C', '1997-09-03', 'Italia', 'Hugo', FALSE, 3, 0, 3);
INSERT INTO jugador VALUES (52, 'Rojo', '16790788W', '1997-09-03', 'Italia', 'JugadorTarjRoja', FALSE, 3, 1, 4);
INSERT INTO jugador VALUES (53, 'Amarillo', '13241799Q', '1997-09-03', 'Italia', 'JugadorTarjAm', FALSE, 5, 0, 4);
INSERT INTO jugador VALUES (54, 'Lesion', '13624444A', '1997-09-03', 'Italia', 'JugadorLesionado', TRUE, 2, 0, 4);


INSERT INTO jugador VALUES (44, 'Birley', '13627777S', '1997-09-03', 'Portugal', 'Justin', FALSE, 2, 0, 4);
INSERT INTO jugador VALUES (45, 'Burbone', '13625888B', '1997-09-03', 'Polonia', 'Hyldre', FALSE, 0, 0, 4);
INSERT INTO jugador VALUES (46, 'Sanatori', '13625999M', '1997-09-03', 'Japon', 'Kobayashi', FALSE, 0, 0, 4);
INSERT INTO jugador VALUES (47, 'Berlusconi', '13625145C', '1997-09-03', 'Italia', 'Hugo', FALSE, 3, 0, 4);
INSERT INTO jugador VALUES (48, 'Portillo', '13625398N', '1997-09-03', 'España', 'Rodrigo', FALSE, 0, 0, 4);
INSERT INTO jugador VALUES (49, 'Rojo', '26790381W', '1997-09-03', 'Italia', 'JugadorTarjRoja', FALSE, 3, 1, 4);
INSERT INTO jugador VALUES (50, 'Amarillo', '23241381Q', '1997-09-03', 'Italia', 'JugadorTarjAm', FALSE, 5, 0, 4);
INSERT INTO jugador VALUES (51, 'Lesion', '23625388A', '1997-09-03', 'Italia', 'JugadorLesionado', TRUE, 2, 0, 4);


INSERT INTO arbitro VALUES(1,'Ilaoz', '93316451K', '1976-09-07', 'España', 'Matheu');
INSERT INTO arbitro VALUES(2,'Builder', '93316789K', '1956-03-17', 'Inglaterra', 'Jack');
INSERT INTO arbitro VALUES(3,'Rubio', '93316001O', '1945-02-27', 'España', 'Francisco');
INSERT INTO arbitro VALUES(4,'Garcia', '93316450K', '1966-11-09', 'España', 'Federico');
INSERT INTO arbitro VALUES(5,'Dante', '94516451K', '1976-09-07', 'Italia', 'Mateo');
INSERT INTO arbitro VALUES(6,'Ilaoz', '43316451K', '1976-09-07', 'España', 'Fernet');
INSERT INTO arbitro VALUES(7,'Ybarra', '53316451K', '1989-09-07', 'España', 'Paco');
INSERT INTO arbitro VALUES(8,'Hector', '93316411K', '1976-09-07', 'España', 'Vigor');
INSERT INTO arbitro VALUES(9,'Sanchez', '93516451K', '1976-09-07', 'España', 'Potele');
INSERT INTO arbitro VALUES(10,'Pedrosa', '98916451Q', '1976-09-07', 'España', 'Pedro');

INSERT INTO partidos VALUES (1,'2012-12-12', 'lugar',1,1,2,1);
INSERT INTO partidos VALUES (2,'2012-12-14', 'lugar2',5,2,2,1);
INSERT INTO partidos VALUES (3,'2012-12-14', 'lugar3',3,1,3,4);
INSERT INTO partidos VALUES (4,'2015-12-14', 'lugar1',8,1,1,4);
INSERT INTO partidos VALUES (5,'2015-12-14', 'lugar5',2,2,3,2);
INSERT INTO partidos VALUES (6,'2015-12-19', 'lugar5',1,2,3,2);
INSERT INTO partidos VALUES (7,'2015-11-19', 'lugar2',1,2,2,4);


