//REGIONES
INSERT INTO regiones(id, nombre) VALUES (1,'Sudamerica');
INSERT INTO regiones(id, nombre) VALUES (2,'Asia');
INSERT INTO regiones(id, nombre) VALUES (3,'Europa');

//CLIENTE

INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos","melgarejo","a@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos2","melgarejo2","a2@gmail.com","2022-06-09",2)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos3","melgarejo3","a3@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos4","melgarejo4","a4@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos5","melgarejo5","a5@gmail.com","2022-06-09",3)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos6","melgarejo6","a6@gmail.com","2022-06-09",1)

//FACTURAS
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion1","obserevacion1","2022-06-08",1)
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion2","obserevacion2","2022-06-08",1)
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion3","obserevacion3","2022-06-08",2)