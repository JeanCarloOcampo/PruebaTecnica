create table if not exists productos (
    id bigint auto_increment primary key,
    nombre varchar(255) not null,
    descripcion varchar(255),
    precio double not null,
    stock int not null,
    categoria varchar(255)
);

create table if not exists clientes (
    id bigint auto_increment primary key,
    nombre varchar(255) not null,
    email varchar(255) unique
);

create table if not exists lista_deseos (
    id bigint auto_increment primary key,
    cliente_id bigint not null,
    producto_id bigint not null,
    cantidad int not null,
    fecha_agregado datetime,
    foreign key (cliente_id) references clientes(id),
    foreign key (producto_id) references productos(id)
);

create table if not exists historico_lista_deseos (
    id bigint auto_increment primary key,
    cliente_id bigint,
    producto_id bigint,
    accion varchar(50),
    fecha_accion datetime,
    foreign key (cliente_id) references clientes(id),
    foreign key (producto_id) references productos(id)
);