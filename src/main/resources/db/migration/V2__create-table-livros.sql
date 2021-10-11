create table livros(
	id bigint not null auto_increment,
	titulo varchar(255) not null,
	lancamento date not null,
	paginas bigint not null,
	primary key(id)
)