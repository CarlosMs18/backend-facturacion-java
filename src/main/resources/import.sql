//REGIONES
INSERT INTO regiones(id, nombre) VALUES (1,'Sudamerica');
INSERT INTO regiones(id, nombre) VALUES (2,'Asia');
INSERT INTO regiones(id, nombre) VALUES (3,'Europa');

//CLIENTE

INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos","melgarejo","a@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos","melgarejo","a2@gmail.com","2022-06-09",1)

//FACTURAS
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion1","obserevacion1","2022-06-08",1)
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion2","obserevacion2","2022-06-08",1)
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion3","obserevacion3","2022-06-08",2)