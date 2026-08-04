drop database if exists baitap_vti;
drop table `group`;


create database if not exists baitap_vti;
use baitap_vti;

create table department (
    department_id bigint primary key auto_increment,
    department_name varchar(100) not null unique
);

create table `position` (
    position_id bigint primary key auto_increment,
    position_name enum('DEV','TEST','SCRUM MASTER','PM') not null 
);

create table `account` (
    account_id bigint primary key auto_increment,
    email varchar(100) not null unique,
    username varchar(100) not null unique,
    full_name varchar(100) not null,
    department_id bigint not null,
    position_id bigint not null,
    create_date datetime not null default current_timestamp,
    constraint fk_account_department foreign key (department_id) references department(department_id),
    constraint fk_account_position foreign key (position_id) references `position`(position_id) 
);

create table `group` (
    group_id bigint primary key auto_increment,
    group_name varchar(100) not null,
    creator_id bigint not null,
    create_date datetime not null default current_timestamp,
    constraint fk_group_account foreign key (creator_id) references `account`(account_id)
);

create table group_account (
    group_id bigint  not null,
    account_id bigint  not null,
    join_date datetime not null default current_timestamp,
    primary key(group_id, account_id),
    constraint fk_group_account_group foreign key (group_id) references `group`(group_id),
    constraint fk_group_account_account foreign key (account_id) references `account`(account_id)
);

create table type_question (
    type_id bigint primary key auto_increment,
    type_name enum('Essay','Multiple-Choice') not null 
);

create table category_question (
    category_id bigint primary key auto_increment,
    category_name enum('Java','.NET','SQL','Postman','Ruby') not null 
);

create table question (
    question_id bigint primary key auto_increment,
    content varchar(1000) not null,
    category_id bigint not null,
    type_id bigint not null,
    creator_id bigint not null,
    create_date date not null default (current_date),
    constraint fk_question_category foreign key (category_id) references category_question(category_id),
    constraint fk_question_type foreign key (type_id) references type_question(type_id),
    constraint fk_question_account foreign key (creator_id) references `account`(account_id)
);

create table answer (
    answer_id bigint primary key auto_increment,
    content varchar(1000) not null,
    question_id bigint not null,
    is_correct boolean not null,
    constraint fk_answer_question foreign key (question_id) references question(question_id)
);

create table exam (
    exam_id int primary key auto_increment,
    `code` int unsigned not null unique,
    title varchar(200) not null,
    category_id bigint not null,
    duration int unsigned not null,
    creator_id bigint not null,
    create_date datetime not null default current_timestamp,
    constraint fk_exam_category foreign key (category_id) references category_question(category_id),
    constraint fk_exam_account foreign key (creator_id) references `account`(account_id),
    check (duration > 0)
);

create table exam_question (
    exam_id int not null,
    question_id bigint not null,
    primary key(exam_id, question_id),
    constraint fk_exam_question_exam foreign key (exam_id) references exam(exam_id),
    constraint fk_exam_question_question foreign key (question_id) references question(question_id)
);
-- department
insert into department (department_name)
values
('IT'),
('HR'),
('Marketing'),
('Sales'),
('Finance'),
('Security'),
('Support'),
('QA'),
('Business'),
('Training');

-- position
insert into `position` (position_name)
values
('DEV'),
('TEST'),
('SCRUM MASTER'),
('PM'),
('DEV'),
('TEST'),
('DEV'),
('PM'),
('SCRUM MASTER'),
('DEV');

-- account
insert into `account`
(email, username, full_name, department_id, position_id)
values
('nam@gmail.com','nam','Le Thanh Nam',1,1),
('an@gmail.com','an','Nguyen Van An',2,2),
('binh@gmail.com','binh','Tran Van Binh',3,3),
('hoa@gmail.com','hoa','Le Thi Hoa',4,4),
('linh@gmail.com','linh','Pham Thi Linh',5,1),
('tuan@gmail.com','tuan','Nguyen Van Tuan',6,2),
('mai@gmail.com','mai','Tran Thi Mai',7,1),
('hung@gmail.com','hung','Le Van Hung',8,4),
('yen@gmail.com','yen','Pham Thi Yen',9,3),
('long@gmail.com','long','Vo Van Long',10,1);

-- group
insert into `group`
(group_name, creator_id)
values
('Java Team',1),
('Backend Team',2),
('Frontend Team',3),
('Tester Team',4),
('Intern Team',5),
('Mobile Team',6),
('AI Team',7),
('DevOps Team',8),
('Cloud Team',9),
('Research Team',10);

-- group_account
insert into group_account
(group_id, account_id)
values
(1,1),
(1,2),
(2,3),
(3,4),
(4,5),
(5,6),
(6,7),
(7,8),
(8,9),
(9,10);

-- type_question
insert into type_question (type_name)
values
('Essay'),
('Multiple-Choice'),
('Essay'),
('Multiple-Choice'),
('Essay'),
('Multiple-Choice'),
('Essay'),
('Multiple-Choice'),
('Essay'),
('Multiple-Choice');

-- category_question
insert into category_question (category_name)
values
('Java'),
('.NET'),
('SQL'),
('Postman'),
('Ruby'),
('Java'),
('.NET'),
('SQL'),
('Postman'),
('Ruby');

-- question
insert into question
(content, category_id, type_id, creator_id)
values
('What is Java?',1,1,1),
('What is SQL?',3,2,2),
('Explain OOP.',1,1,3),
('What is Postman?',4,2,4),
('What is Ruby?',5,1,5),
('What is Spring Boot?',1,2,6),
('Explain REST API.',2,1,7),
('What is Docker?',3,2,8),
('Explain Git.',4,1,9),
('What is MySQL?',5,2,10);

-- answer
insert into answer
(content, question_id, is_correct)
values
('Java is a programming language.',1,true),
('SQL is used to query databases.',2,true),
('OOP stands for Object-Oriented Programming.',3,true),
('Postman is an API testing tool.',4,true),
('Ruby is a programming language.',5,true),
('Spring Boot is a Java framework.',6,true),
('REST is an architectural style.',7,true),
('Docker uses containers.',8,true),
('Git is a version control system.',9,true),
('MySQL is a relational database.',10,true);

-- exam
insert into exam
(code, title, category_id, duration, creator_id)
values
(1001,'Java Basic Test',1,60,1),
(1002,'SQL Basic Test',3,45,2),
(1003,'Backend Test',1,90,3),
(1004,'Postman Test',4,30,4),
(1005,'Ruby Test',5,60,5),
(1006,'Spring Boot Test',1,60,6),
(1007,'REST API Test',2,45,7),
(1008,'Docker Test',3,90,8),
(1009,'Git Test',4,30,9),
(1010,'MySQL Test',5,60,10);

-- exam_question
insert into exam_question
(exam_id, question_id)
values
(1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6),
(7,7),
(8,8),
(9,9),
(10,10);