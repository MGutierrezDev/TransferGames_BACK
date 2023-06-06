--------------------------------- Import erole --------------------------------
INSERT INTO erole (id,name) VALUES (1,'ADMIN');
INSERT INTO erole (id,name) VALUES (2,'USER');

--------------------------------- Import juego --------------------------------
INSERT INTO juego (id,descripcion,imagen,nombre) VALUES (1,'Increible juego de plataformas en el que eres la rana más golfa! Disfruta de las animaciones de la RanaNinja!',1,'RanaMan');

--------------------------------- Import mensaje_foro --------------------------------
INSERT INTO mensaje_foro (id_mensaje,asunto,date,texto,user_id) VALUES (1,'Tony probando probando','2023-06-06 10:30:00','los golfo',1);


--------------------------------- Import respuesta_foro --------------------------------
INSERT INTO respuesta_foro (id_respuesta,date,respuesta,titulo,mensaje_foro_id_mensaje,user_id) VALUES (1,'2023-06-06 10:35:00','Si','Respuesta golfa',1,1);

--------------------------------- Import user_security --------------------------------
INSERT INTO user_juegos (user_id,juego_id) VALUES (1,1);

--------------------------------- Import user_security_lista_juegos --------------------------------
INSERT INTO user_security (id,email,name,password,role_id) VALUES (1, 'nachopsanchez@gmail.com','NachitoDeveloper','$2a$10$D3VgWbgNAiSVfeEtD4rNA.aiqw2kycdgcpl44ie5j8QsXaxcQLA2u',1);