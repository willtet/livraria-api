create table autores(
	id bigint not null auto_increment,
	nome varchar(255) not null,
	email varchar(100) not null,
	nascimento date not null,
	curriculo varchar(255),
	primary key(id)
)