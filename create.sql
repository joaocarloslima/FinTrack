create sequence categoria_seq start with 1 increment by 50;
create sequence movimentacao_seq start with 1 increment by 50;
create table categoria (id number(19,0) not null, icone varchar2(255 char), nome varchar2(255 char), primary key (id));
create table movimentacao (data date, valor number(38,2), categoria_id number(19,0), id number(19,0) not null, descricao varchar2(255 char), tipo varchar2(255 char), primary key (id));
alter table movimentacao add constraint FK4xv8dlqbeqln91hiepgijsu1m foreign key (categoria_id) references categoria;
