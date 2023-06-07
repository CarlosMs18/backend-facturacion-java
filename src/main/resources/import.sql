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
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos7","melgarejo","a7@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos8","melgarejo2","a8@gmail.com","2022-06-09",2)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos9","melgarejo3","a9@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos10","melgarejo4","a10@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos11","melgarejo5","a11@gmail.com","2022-06-09",3)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos12","melgarejo6","a12@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos13","melgarejo","a13@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos14","melgarejo2","a14@gmail.com","2022-06-09",2)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos15","melgarejo3","a15@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos16","melgarejo4","a16@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos17","melgarejo5","a17@gmail.com","2022-06-09",3)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos18","melgarejo6","a18@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos19","melgarejo","a19@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos20","melgarejo2","a20@gmail.com","2022-06-09",2)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos21","melgarejo3","a21@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos22","melgarejo4","a22@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos23","melgarejo5","a23@gmail.com","2022-06-09",3)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos24","melgarejo6","a24@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos25","melgarejo","a25@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos26","melgarejo2","a26@gmail.com","2022-06-09",2)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos27","melgarejo3","a27@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos28","melgarejo4","a28@gmail.com","2022-06-09",1)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos29","melgarejo5","a29@gmail.com","2022-06-09",3)
INSERT INTO clientes( nombre, apellido, email, create_at, region_id) VALUES ("carlos30","melgarejo6","a30@gmail.com","2022-06-09",1)

INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digl DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Mult2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());



//FACTURAS
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion1","obserevacion1","2022-06-08",1)
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion2","obserevacion2","2022-06-08",1)
INSERT INTO facturas(descripcion, observacion, create_at, cliente_id) VALUES("descripcion3","obserevacion3","2022-06-08",2)

//FACTURA ITEMS

INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);




INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 2, 1);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(2, 2, 4);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 2, 5);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 2, 7);

INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 3, 5);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 3, 7);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 3, 5);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 3, 7);

