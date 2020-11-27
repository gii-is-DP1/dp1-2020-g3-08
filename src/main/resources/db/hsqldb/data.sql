INSERT INTO genres VALUES (1, 'hombre');
INSERT INTO genres VALUES (2, 'mujer');

INSERT INTO users VALUES ('admin1', '2010-09-07', 'trainer@hotmail.com', 'true', 'Ricardo', 'Pomelo', 'admin1', '628718902', 1);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO noticias VALUES (1, '2010-09-07','titulo', 'texto');

INSERT INTO equipos VALUES (1, 'Benito Villamarín', 'Betis');
INSERT INTO equipos VALUES (2, 'Pocilga Stadium', 'Sevilla');

INSERT INTO jugador VALUES (1, 'Rodriguez', '93725381K', '1996-09-07', 'España', 'Daniel', FALSE, 0, 0, 1);
INSERT INTO jugador VALUES (2, 'López', '13625381K', '1993-09-07', 'España', 'Jose', TRUE, 0, 1, 1);