--QUERIES USADOS POR EL AMBIENTE "local".
--AL USAR H2, LAS TABLAS SE CREAN AUTOMÁTICAMENTE. SI EXISTEN, LAS BORRA CADA QUE EL SERVIDOR SE INICIA. ADEMAS, ESTAMOS USANDO EL ATRIBUTO spring.jpa.hibernate.ddl-auto=create-drop EN EL application.properties.
--POR DFECTO, H2 CREA UNA BASE DE DATOS LLAMADA testdb. AHI SE VAN GUARDANDO LAS TABLAS

--SI USAMOS AUTORIZACION BASADA EN COINCICENCIAS DE URL O BASADA EN METODOS, NO DEBEMOS GUARDAR EN BASE DE DATOS MODULOS, OPERACIONES, ROLES Y PERMISOS.
--LAS TABLAS ANTES MENCIONADAS SON PARA LA "AUTORIZACIÓN PERSONALIZADA".

--CREACION DE MODULOS
--Es solo para modularizar las tablas(normalizacion). Podría estar dentro de la tabla operation
INSERT INTO module (name, base_path) VALUES ('PRODUCT', '/products');
INSERT INTO module (name, base_path) VALUES ('CATEGORY', '/categories');
INSERT INTO module (name, base_path) VALUES ('CUSTOMER', '/customers');
INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');

-- CREACIÓN DE OPERACIONES
-- Contempla la misma lógica que el metodo buildRequestMatcher(autorizacion basada en coincidencias de urls). --> Si tienes determinado permiso tendrás acceso a determinado endpoint.
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_PRODUCTS','', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_PRODUCT','/[0-9]*', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_PRODUCT','', 'POST', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_PRODUCT','/[0-9]*', 'PUT', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_PRODUCT','/[0-9]*/disabled', 'PUT', false, 1);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CATEGORIES','', 'GET', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ONE_CATEGORY','/[0-9]*', 'GET', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('CREATE_ONE_CATEGORY','', 'POST', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ONE_CATEGORY','/[0-9]*', 'PUT', false, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DISABLE_ONE_CATEGORY','/[0-9]*/disabled', 'PUT', false, 2);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CUSTOMERS','', 'GET', false, 3);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','', 'POST', true, 3);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, 4);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', false, 4);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('LOGOUT','/logout','POST', true, 4);

-- CREACIÓN DE ROLES
INSERT INTO role (name) VALUES ('CUSTOMER');
INSERT INTO role (name) VALUES ('ASSISTANT_ADMINISTRATOR');
INSERT INTO role (name) VALUES ('ADMINISTRATOR');

-- CREACIÓN DE PERMISOS
-- Es como una tabla intermedia. Relaciona operaciones(authorities) con roles.
INSERT INTO granted_permissions (role_id, operation_id) VALUES (1, 15);

INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 1);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 2);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 4);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 6);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 7);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 9);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (2, 15);

INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 1);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 2);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 3);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 4);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 5);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 6);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 7);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 8);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 9);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 10);
INSERT INTO granted_permissions (role_id, operation_id) VALUES (3, 15);

--CREACION DE USUARIOS
--INSERT INTO "user" (username, name, password, role) VALUES ('lmarquez', 'luis márquez', '$2a$10$IjorW0xEHmFM07qD7.bwXui4hePvBzJ2tz59ev8K.fFUO5CesYF1K', 'CUSTOMER');
--INSERT INTO "user" (username, name, password, role) VALUES ('fperez', 'fulano pérez', '$2a$10$5t5RI58gERsc7aS032FeAeVGpDDgT3n85k83Qv3OV/Dq9TTgCSg0i', 'ASSISTANT_ADMINISTRATOR');
--INSERT INTO "user" (username, name, password, role) VALUES ('mhernandez', 'mengano hernández', '$2a$10$X12EIqSKrf7voOGc9qJGGOQeYB.YsZak7WaIIIHqkKugaTBvi87mu', 'ADMINISTRATOR');
INSERT INTO "user" (username, name, password, role_id) VALUES ('lmarquez', 'luis márquez', '$2a$10$ywh1O2EwghHmFIMGeHgsx.9lMw5IXpg4jafeFS.Oi6nFv0181gHli', 1);
INSERT INTO "user" (username, name, password, role_id) VALUES ('fperez', 'fulano pérez', '$2a$10$V29z7/qC9wpHfzRMxGOHye5RMAxCid2/MzJalk0dsiA3zZ9CJfub.', 2);
INSERT INTO "user" (username, name, password, role_id) VALUES ('mhernandez', 'mengano hernández', '$2a$10$TMbMuEZ8utU5iq8MOoxpmOc6QWQuYuwgx1xJF8lSMNkKP3hIrwYFG', 3);

-- CREACIÓN DE CATEGORIAS
INSERT INTO category (name, status) VALUES ('Electrónica', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Ropa', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Deportes', 'ENABLED');
INSERT INTO category (name, status) VALUES ('Hogar', 'ENABLED');

-- CREACIÓN DE PRODUCTOS
INSERT INTO product (name, price, status, category_id) VALUES ('Smartphone', 500.00, 'ENABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('Auriculares Bluetooth', 50.00, 'DISABLED', 1);
INSERT INTO product (name, price, status, category_id) VALUES ('Tablet', 300.00, 'ENABLED', 1);

INSERT INTO product (name, price, status, category_id) VALUES ('Camiseta', 25.00, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Pantalones', 35.00, 'ENABLED', 2);
INSERT INTO product (name, price, status, category_id) VALUES ('Zapatos', 45.00, 'ENABLED', 2);

INSERT INTO product (name, price, status, category_id) VALUES ('Balón de Fútbol', 20.00, 'ENABLED', 3);
INSERT INTO product (name, price, status, category_id) VALUES ('Raqueta de Tenis', 80.00, 'DISABLED', 3);

INSERT INTO product (name, price, status, category_id) VALUES ('Aspiradora', 120.00, 'ENABLED', 4);
INSERT INTO product (name, price, status, category_id) VALUES ('Licuadora', 50.00, 'ENABLED', 4);
