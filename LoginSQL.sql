create database login;
use login;

create table user(
id int primary key auto_increment,
nom varchar(50),
email varchar (50),
mdps int,
telephone int,
specialite varchar(50)
);

create table medecin(
id_medecin int auto_increment primary key,
id_user int ,
nom varchar(50),
email varchar (50),
mdps int,
telephone int,
specialite varchar (50),
foreign key (id_user) references user(id)
);

create table patient(
id_parient int auto_increment primary key,
id_user int ,
nom varchar(50),
email varchar (50),
mdps int,
telephone int,
foreign key (id_user) references user(id)
);
