insert ignore into productos (id, nombre, descripcion, precio, stock, categoria) values
(1, 'Cuaderno Profesional 100 hojas', 'Cuaderno cuadriculado de pasta dura', 8500.0, 50, 'Papelería'),
(2, 'Resma de papel carta', 'Resma 500 hojas tamaño carta', 22000.0, 0, 'Papelería'),
(3, 'Caja de lápices x12', 'Lápices de grafito HB', 12000.0, 30, 'Útiles escolares'),
(4, 'Marcador permanente negro', 'Marcador de punta fina', 3500.0, 100, 'Útiles escolares'),
(5, 'Carpeta archivadora', 'Carpeta tamaño oficio', 15000.0, 0, 'Oficina');

insert ignore into clientes (id, nombre, email) values
(1, 'Juan Pérez', 'juan.perez@correo.com'),
(2, 'María López', 'maria.lopez@correo.com');