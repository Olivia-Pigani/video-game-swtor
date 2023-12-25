create database swtor_jdbc;

create table characters (
	id serial primary key not null,
	name varchar(50),
	light_saber boolean not null,
	health int not null,
	hasForce boolean not null,
	team varchar(50) not null
);

create table powers (
	id serial primary key not null,
	name varchar(50) not null,
	attack_points int,
	health_restorer int,
	team varchar(50)not null
);

create table equipments (
	id serial not null primary key,
	name varchar(50) not null,
	attack_points int,
	health_restorer int,
	stuff_type varchar(50)
);

create table characters_powers (
	characters_id int,
	powers_id int,
	primary key (characters_id,powers_id),
	foreign key (characters_id) references characters(id),
	foreign key (powers_id) references powers(id)
);

create table characters_equipments (
	characters_id int,
	equipments_id int,
	primary key (characters_id,equipments_id),
	foreign key (characters_id) references characters(id),
	foreign key (equipments_id) references equipments(id)
);


insert into characters (name,light_saber,health,hasforce,team)
values 
('Atton Rand',false,50,true,'REPUBLICTEAM'),
('Goto',false,50,false,'SITHTEAM');


insert into powers (name,attack_points,health_restorer,team)
values
('Force Lightning', 30, 0, 'SITH'),
('Force Choke', 25, 0, 'SITH'),
('Force Rage', 20, 0, 'SITH'),
('Life Drain', 15, 5, 'SITH'),
('Force Heal', 0, 20, 'JEDI'),
('Force Push', 15, 0, 'JEDI'),
('Force Meditation', 0, 10, 'JEDI'),
('Blaster Deflection', 10, 0, 'JEDI');

INSERT INTO equipments (name, attack_points, health_restorer, stuff_type) VALUES
('Blaster Rifle', 20, 0, 'WEAPON'),
('Ion Blaster', 15, 0, 'WEAPON'),
('Heavy Repeater', 25, 0, 'WEAPON'),
('Disruptor Rifle', 30, 0, 'WEAPON');

INSERT INTO equipments (name, attack_points, health_restorer, stuff_type) VALUES
('Thermal Detonator', 40, 0, 'GRENADE'),
('EMP Grenade', 0, 0, 'GRENADE'), 
('Smoke Grenade', 0, 0, 'GRENADE'), 
('Stun Grenade', 10, 0, 'GRENADE'),
('Fragmentation Grenade', 35, 0, 'GRENADE');

INSERT INTO equipments (name, attack_points, health_restorer, stuff_type) VALUES
('Basic Medkit', 0, 20, 'MEDKIT'),
('Advanced Medkit', 0, 50, 'MEDKIT'),
('Field Medkit', 0, 30, 'MEDKIT'),
('Combat Medkit', 0, 40, 'MEDKIT'),
('Bacta Infusion', 0, 60, 'MEDKIT');  



