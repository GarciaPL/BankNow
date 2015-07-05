create table if not exists ACCOUNT (
  id integer primary key identity ,
  name varchar (255),
  surname varchar (255),
  address varchar (255),
  phone numeric(11),
  iban numeric(26),
  balance numeric(12,2),
  currency varchar (3)
);

create table if not exists TRANSACTION (
  id integer primary key identity ,
  sender numeric(26),
  recipient numeric(26),
  amount numeric(12,2)
);