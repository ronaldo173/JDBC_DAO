drop database if exists `daotalk` ;

create database  if not exists `daotalk` default charset `utf8`;
use `daotalk`;

create table if not exists `Group`(
`id` int not null auto_increment,
`number` int not null,
`department` varchar(50) null,
primary key(`id`));

create table if not exists `Student`(
`id` int not null auto_increment,
 `name` varchar(50) null,
 `surname` varchar(50) null,
 `enrolment_date` date null,
 `group_id` int,
 primary key(`id`));