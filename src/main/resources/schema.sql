create table if not exists ACCOUNT (
  id integer primary key identity ,
  name varchar (255),
  surname varchar (255),
  address varchar (255),
  iban integer,
  balance numeric(12,2),
  currency varchar (3)
);

create table if not exists TRANSACTION (
  id integer primary key identity ,
  sender integer,
  recipient integer,
  amount numeric(12,2)
);