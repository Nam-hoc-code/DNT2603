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
    email varchar(100) unique, -- unique : dữ liệu ko đc trùng  
    useName varchar(100) unique key,
    fullName varchar(100),
	departmentId bigint,
    sum_member int unsigned, -- unsigned : giá trị ko đc âm
    positionId bigint, 
    createDate datetime,
	CONSTRAINT fk_Account_Department FOREIGN KEY (departmentId) REFERENCES Department(departmentId),
	constraint fk_Account_Position foreign key (positionId) references `Position`(positionId),
    check (sum_member > 4) -- kiểm tra giá trị đầu vào đúng với 
    );    
    -- test insert	( số lần check ) 
     -- insert into 
create table `Group` ( 
	groupId bigint primary key auto_increment,
    groupName varchar(100) ,
    creatorId bigint,
    createDate varchar(100),
    
    constraint fk_Group_Account foreign key (creatorId) references `Account`( accountId )
    );       
    
    
create table GroupAccount (
	groupId bigint primary key auto_increment,
    accountId bigint,
	joinDate Datetime,
    
    constraint fk_groupAccount_Group foreign key (groupId) references `Group`(groupId),
    constraint fk_groupAccount_Account foreign key (accountId) references `Account`(accountId)
    
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
    constraint fk_Question_CategoryQuestion foreign key (categoryId) references CategoryQuestion(categoryId),--
    constraint fk_Question_TypeQuestion foreign key (typeId) references TypeQuestion(typeId) ,
    constraint fk_Question_Account foreign key (creatorId) references `Account`(accountId)
    
    );
    
create table Answer (
	answerId bigint primary key auto_increment,
    content varchar(1000),
    questionId bigint,
    isCorrect boolean,
    
    constraint fk_Answer_Question foreign key (questionId) references Question(questionId)
    
    );
    
create table Exam ( 
	examId int primary key auto_increment,
    `Code` int,
    title varchar (200),
    categoryId bigint, -- định danh chủ đề  câu hỏi
    duration datetime,
    creatorId bigint,
    createDate datetime default   current_timestamp  ,
    
    constraint fk_Exam_CategoryQuestion foreign key (categoryId ) references CategoryQuestion ( categoryId),
    constraint fk_Exam_Account foreign key ( creatorId) references `Account`(accountId)
    );
    
create table ExamQuestion (
	examId int primary key ,
    questionId int,
    
    constraint fk_Exam_ExamQuestion foreign key (examId) references Exam(examId)
		
    );
    


--     SHOW TABLES;
--     drop table answer;--     constraint fk_creatorId_from_tableQuestion foreign key (creatorId) references `Account`(accountId)
		-- drop database baitapVTI;
    
--     alter table Question
--     drop CONSTRAINT  fk_creatorId;
--     
--      alter table Question 
--      add   constraint fk_creatorId_from_tableQuestion foreign key (creatorId) references `Account`(accountId)-- quy tắc đặt tên foreign key : ?
-- khóa ngoại cách đặt tên : fk_table1_table2. 

-- Query data statement : xem dữ liệu và không sửa.
-- select * from account; : see all
-- select ...,...,... <- field muốn xem from account : see field you choose
-- select dữ liệu theo điều kiện 
-- select * from account where department_id = 1;
-- AND
-- OR
-- BETWEEN…AND

-- tìm các tài khoản có exp_years là 1,2,6,7,...: -> quá nhiều case để dùng or -> dùng in / not in
-- IN / NOT IN


-- LIKE / NOT LIKE  :
--  where field (có thể là name,...) like %text : chuỗi có chuỗi text ở cuối và ở chuỗi text bắt đầu : text% hoặc nằm ở bất chứ đầu  : %text% 
--  hoặc tìm kiếm gần đúng : bằng _ : một _ đại điện cho một ký tự vd : __ _____ Nam -> nhưng ko phổ biến vì quá khó người cần tìm có bao nhiêu ký tự ở trước
-- not like  
-- Bài toán tìm các ô dữ liệu null.
-- Tại sao dùng is null ko phải = null : bản chất null : NULL là một trạng thái đại diện cho sự "vô định", "chưa biết" hoặc "không tồn tại".
						-- Phép = chỉ dùng để so sánh 2 giá trị cụ thể (Ví dụ: Anh = Anh)
                        -- Khi bạn viết = NULL, máy tính hiểu là bạn đang so sánh: "Giá trị này có bằng một thứ 'chưa biết' hay không?" 
                        -- Câu trả lời của máy tính luôn là "Không xác định" (Unknown). Do kết quả không phải là Đúng (True), SQL sẽ bỏ qua tất cả các dòng dữ liệu đó.
-- Tại sao IS NULL lại đúng?
--        IS NULL là một phép kiểm tra trạng thái (giống như một câu hỏi Có/Không).
--        Nó kiểm tra: "Ô dữ liệu này có đang bị bỏ trống không?". 
--        Nếu đúng là trống, máy tính trả về True và lấy dòng đó ra cho bạn.

