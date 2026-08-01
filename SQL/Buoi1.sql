-- select database() -- xem db đang dùng hiện tại 


create database baitapVTI;
use baitapVTI;

create table Department (
	departmentId bigint primary key auto_increment,
    departmentName varchar(100)
);

create table `Position` ( 
	positionId bigint primary key auto_increment,
    positionName enum('DEV','TEST', 'SCRUM MASTER', 'PM')
    );
    

    
create table `Account` ( 
	accountId bigint primary key auto_increment,
    email varchar(100) ,
    useName varchar(100) unique key,
    fullName varchar(100),
	departmentId bigint,
    positionId bigint, 
    createDate datetime,
	CONSTRAINT fk_departmentId FOREIGN KEY (departmentId) REFERENCES Department(departmentId),
	constraint fk_positionId foreign key (positionId) references `Position`(positionId)
    );    
    --
create table `Group` ( 
	groupId bigint primary key auto_increment,
    groupName varchar(100) ,
    creatorId bigint,
    createDate varchar(100)
    );       
    
    
create table GroupAccount (
	groupId bigint primary key auto_increment,
    accountId bigint,
	joinDate Datetime,
    
    constraint fk_groupId foreign key (groupId) references `Group`(groupId),
    constraint fk_accountId foreign key (accountId) references `Account`(accountId)
    
    );
    
create table TypeQuestion ( 
	typeId bigint primary key auto_increment,
    typeName Enum( 'Essay','Multiple-Choice')
    );
-- nên khai báo trường trước rồi mới xác định khai báo đâu là kháo chính bằng primary key ( ....,...)  ?
create table CategoryQuestion ( 
	categoryId bigint primary key auto_increment, --  định danh của chủ đề câu hỏi (auto increment)
    categoryName Enum('Java', '.NET', 'SQL', 'Postman','Ruby')
);

create table Question ( 
	questionId bigint primary key auto_increment,
    content varchar(1000),
    categoryId bigint, 
    typeId bigint,
    creatorId bigint,
    createDate date,
    constraint fk_categoryId_from_tableQuestion foreign key (categoryId) references CategoryQuestion(categoryId),--
    constraint fk_typeId foreign key (typeId) references TypeQuestion(typeId) ,
    constraint fk_creatorId_from_tableQuestion foreign key (creatorId) references `Account`(accountId)
    
    );
    
create table Answer (
	answerId bigint primary key auto_increment,
    content varchar(1000),
    questionId bigint,
    isCorrect boolean,
    
    constraint fk_questionId foreign key (questionId) references Question(questionId)
    
    );
    
create table Exam ( 
	examId int primary key auto_increment,
    `Code` int,
    title varchar (200),
    categoryId bigint, -- định danh chủ đề  câu hỏi
    duration datetime,
    creatorId bigint,
    createDate datetime,
    
    constraint fk_categoryId_from_tableExam foreign key (categoryId ) references CategoryQuestion ( categoryId),
    constraint fk_creatorId_from_tableExam foreign key ( creatorId) references `Account`(accountId)
    );
    
create table ExamQuestion (
	examId int primary key ,
    questionId int,
    
    constraint fk_examId foreign key (examId) references Exam(examId)
    
    );
    
  --  Error Code: 1826. Duplicate foreign key constraint name 'fk_categoryId'



--     SHOW TABLES;
--     drop table answer;--     constraint fk_creatorId_from_tableQuestion foreign key (creatorId) references `Account`(accountId)

    
    alter table Question
    drop CONSTRAINT  fk_creatorId;
    
     alter table Question 
     add   constraint fk_creatorId_from_tableQuestion foreign key (creatorId) references `Account`(accountId)-- quy tắc đặt tên foreign key : ?
