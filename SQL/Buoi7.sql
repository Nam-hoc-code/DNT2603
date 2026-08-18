use baitap_vti;
-- Funtional : khá giống với procedure nhưng lại có thể tái sử dụng dữ liệu trả về -> chọn funtional	
DELIMITER $$

CREATE FUNCTION test(dep_name_in VARCHAR(100))
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE so_luong INT;
    SELECT COUNT(1)
    INTO so_luong
    FROM account acc
    JOIN department dep
        ON acc.department_id = dep.department_id
    WHERE dep.department_name LIKE CONCAT('%', dep_name_in, '%');
    RETURN so_luong;
END $$
DELIMITER ;

select test ( 'a');

-- ---------------------------------------------------------------------
-- TRIGGER -- 
-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo trước 1 năm trước
-- lấy id phòng -> truy vấn tên phòng -> kiểm tra thời gian chỉ trong 1 năm trước đổ lại -> sai basao Lỗi
	
delimiter $$ 
create trigger trg_entry_name 
before insert ON `group` 
for each row 
begin 

    if new.create_date < date_sub(now(), interval 1 year) then 
        signal sqlstate '45000'
        set message_text = 'Nhóm được insert phải từ 1 năm trước trở lại';
    end if;

end$$

delimiter ;
	
-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào
-- department "Sale" nữa, khi thêm thì hiện ra thông báo "Department
-- "Sale" cannot add more user"
delimiter $$ 

create trigger trg_cancel_sale 
before insert on account 
for each row 
begin 

    declare new_department varchar(100);

    select department_name
    into new_department
    from department
    where department_id = new.department_id;

    if new_department like 'Sale' then
        signal sqlstate '45000'
        set message_text = 'Sale cannot add more user';
    end if;

end$$

delimiter ;

-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user
-- -> lấy group muốn user muốn insert ( before, insert )  -> kiểm tra số lượng thành viên (count) -> đưa ra thông báo lỗi nếu đủ 5 -- 
select * from `group_account`;

delimiter $$ 

create trigger trg_5_team_member 
before insert on group_account
for each row 
begin 

    declare number_of_member int;

    select count(1) 
    into number_of_member
    from group_account 
    where group_id = new.group_id;
        
    if number_of_member >= 5 then
        signal sqlstate '45000'
        set message_text = 'number of member must be less than or equal to five';
    end if;

end$$

delimiter ;

select * from exam_question;

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question
delimiter $$ 

create trigger trg_10_ques_1_exam 
before insert on exam_question
for each row 
begin 

    declare sum_of_questions int;

    select count(1)
    into sum_of_questions 
    from exam_question
    where exam_id = new.exam_id;
        
    if sum_of_questions >= 10 then 
        signal sqlstate '45000'
        set message_text = 'Max of question for each exam just 10';
    end if;

end$$

delimiter ;

-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là
-- admin@gmail.com (đây là tài khoản admin, không cho phép user xóa),
-- còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông
-- tin liên quan tới user đó
-- xác định tài khoản user đang muốn xóa -> nếu ko phải admin -> xóa các thông tin ở account, thông tin ở exam_question, group_account,...

delimiter $$ 

create trigger trg_delete_account
before delete on account
for each row 
begin 

    if old.email = 'admin@gmail.com' then
        signal sqlstate '45000'
        set message_text = 'Cannot remove this gmail';

    else

        delete from group_account
        where account_id = old.account_id;

    end if;

end$$

delimiter ;

-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của table
-- Account, hãy tạo trigger cho phép người dùng khi tạo account không điền
-- vào departmentID thì sẽ được phân vào phòng ban "waiting Department"
select * from account;

use baitap_vti;

delimiter $$ 

create trigger trig_no_default_department_id 
before insert on account
for each row 
begin 

    if new.department_id is null then

        set new.department_id = (
            select department_id
            from department 
            where department_name like 'Watting'
            limit 1
        );

    end if;

end$$

delimiter ;

--  
-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi
-- question, trong đó có tối đa 2 đáp án đúng.
-- đếm giá trị khi add vào answer cho câu hỏi before -> kiểm tra với đk tối đa 4 answer, 2 câu đúng tối thiểu 1 câu đúng ( ở bảng answer )
select * from question;
select * from answer;

delimiter $$ 

create trigger trg_question_answer 
before insert on answer 
for each row
begin 

    declare number_of_right_answer int;
    declare number_of_answer int; 

    -- đếm số câu hỏi
    select count(1)
    into number_of_answer
    from answer
    where question_id = new.question_id;

    -- đếm đáp án đúng 
    select count(1)
    into number_of_right_answer
    from answer
    where question_id = new.question_id
    and is_correct = true;

    -- kiểm tra đk : số câu trả lời tối đa là 4
    if number_of_answer >= 4 then 
        signal sqlstate '45000'
        set message_text = 'số câu trả lời nhỏ hơn bằng 4';
    end if;

    if new.is_correct = true and number_of_right_answer >= 2 then
        signal sqlstate '45000'
        set message_text = 'số đáp án đúng nhỏ hơn bằng 2';
    end if;

end$$

delimiter ;

-- Question 8: Viết trigger sửa lại dữ liệu cho đúng:
-- Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định
-- Thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database

-- mình kiểm tra trước ( before insert) -> đổi (dùng if ) để đổi cái gender tương dúng mà người dùng chọn rồi (into vào gender ) để insert vào db

alter table account
add column gender char(1);

delimiter $$

create trigger before_insert_account
before insert on account
for each row
begin

    if lower(new.gender) = 'nam' then
        set new.gender = 'm';

    elseif lower(new.gender) = 'nữ' then
        set new.gender = 'f';

    elseif lower(new.gender) = 'chưa xác định' then
        set new.gender = 'u';
    end if;

end$$

delimiter ;

-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày
-- kiểm tra trước ( before delete ) -> on exam table -> sau đó kiểm tra create_date có phù với là > 2 ngày trước ko -> rồi mới cho phép thực hiện xóa

delimiter $$

create trigger before_delete_exam
before delete on exam
for each row
begin

    if old.create_date > date_sub(now(), interval 2 day) then
        signal sqlstate '45000'
        set message_text = 'khong the xoa exam moi tao trong vong 2 ngay';
    end if;

end$$

delimiter ;

-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các
-- question khi question đó chưa nằm trong exam nào
-- -> before hành động update, delete on question , kiểm tra ở question_exam table đảm bảo nó ko tồn tại ở bằng này ( question_id) -> ròi với cho phép thực hiện xóa hay update

delimiter $$

create trigger before_delete_question
before delete on question
for each row
begin

    if exists (
        select 1
        from exam_question
        where question_id = old.question_id
    ) then

        signal sqlstate '45000'
        set message_text = 'khong the xoa question da nam trong exam';

    end if;

end$$

delimiter ;

delimiter $$

create trigger before_update_question
before update on question
for each row
begin

    if exists (
        select 1
        from exam_question
        where question_id = old.question_id
    ) then

        signal sqlstate '45000'
        set message_text = 'khong the update question da nam trong exam';

    end if;

end$$

delimiter ;