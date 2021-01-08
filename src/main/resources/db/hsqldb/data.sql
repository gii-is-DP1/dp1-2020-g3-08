INSERT INTO genres VALUES (1, 'hombre');
INSERT INTO genres VALUES (2, 'mujer');

INSERT INTO users VALUES ('admin1', '2010-09-07', 'trainer@hotmail.com', 'true', 'Ricardo', 'Pomelo', 'admin1', '628718902', 1);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO noticias VALUES (1, '2010-09-07','titulo', 'texto');

INSERT INTO equipos VALUES (1, 'Benito Villamarín', 'Betis');
INSERT INTO equipos VALUES (2, 'Pocilga Stadium', 'Sevilla');

INSERT INTO jugador VALUES (1, 'Rodriguez', '93725381K', '1996-09-07', 'España', 'Daniel', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (2, 'López', '13625381K', '1993-09-07', 'España', 'Jose', TRUE, 0, 1, 1);

INSERT INTO arbitro VALUES(1,'Ilaoz', '93316451K', '1976-09-07', 'España', 'Matheu');

INSERT INTO partidos VALUES (1,'2012-12-12', 'lugar',1,1,2);
INSERT INTO partidos VALUES (2,'2012-12-14', 'lugar2',1,1,2);

INSERT INTO competiciones VALUES(1,'Competicion prueba');
INSERT INTO competiciones VALUES(2,'Competicion prueba 2');




