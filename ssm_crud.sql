create database if not exists ssm_crud;
use ssm_crud;

create table if not exists tbl_emp(
	emp_id int primary key auto_increment,
    emp_name varchar(255) not null,
    gander char(1),
    email varchar(255),
    d_id int
);

create table if not exists tbl_dept(
	dept_id int primary key auto_increment,
    dept_name varchar(255) not null
);

alter table tbl_emp add constraint fk_emp_dept foreign key (d_id) references tbl_dept(dept_id);


INSERT INTO `tbl_dept`
(`dept_name`)
VALUES
("");




