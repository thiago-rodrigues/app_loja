# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table produto (
  id                            bigserial not null,
  nome                          varchar(255),
  valor                         decimal(38),
  constraint pk_produto primary key (id)
);


# --- !Downs

drop table if exists produto cascade;

