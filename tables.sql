create user notebook identified by notebook;
grant connect, resource to notebook;

connect  notebook/notebook;

create table users
( uname    varchar2(10) primary key,
  password varchar2(10) not null,
  fullname varchar2(50) not null,
  email    varchar2(50) unique not null,
  phoneno   varchar2(10),
  joinedon date
);


create sequence entryid_seq nocache;

create table RecordEntries
( recordid  number(5)  primary key,
  uname    varchar2(10) references users(uname) not null,
  recorddate varchar2(10)  not null, 
  recordtime varchar2(5),  
  recordnote varchar2(4000) not null  
);



