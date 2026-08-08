
use baitap_vti;
-- (Sử dụng subquery hoặc CTE)
-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
select * from account;
-- view
create view list_employe_sale1 as
select acc.*
from account acc
where department_id = (
	select department_id 
	from department 
	where department_name = 'Sales');

select * from list_employe_sale1;

-- CTE
create view list_employee_sale2 as
(with cte_department_sale as (
	select department_id
    from department
    where department_name = 'Sales'
)
select * 
from account
where department_id = ( 
	select department_id
    from cte_department_sale )
); 
select * from  list_employee_sale2;

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
-- sub query
select count(account_id) 
from group_account
group by account_id
order by count(account_id) desc limit 1;
 
create view list_account_most_group as
select acc.*, count( ga.account_id) tong_so
from account acc
join group_account ga on acc.account_id = ga.account_id
group by acc.account_id
having tong_so   = (
		select count(account_id) 
		from group_account
		group by account_id
		order by count(account_id) desc limit 1);
        
select * from list_account_most_group;
select * from group_account;
-- CTE
create view list_account_most_group2 as 
with cte_most_group as (
	select acc.account_id, count( ga.account_id ) 
    from account acc
    join group_account ga on ga.account_id = acc.account_id
    group by acc.account_id
    having count( ga.account_id ) = ( 
		select  count(account_id) 
		from group_account 
		group by account_id
		order by count(account_id) desc limit 1)
    )
select *
from account 
where account_id in ( select account_id from cte_most_group );

select  * from list_account_most_group2;



-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ
-- được coi là quá dài) và xóa nó đi
-- sub query

create view list_question_too_long_content as
select question_id 
from question 
where question_id in (
		select question_id 
		from question
		where length(content) > 20) ;  


delete from question --  thực hiện xóa 
where question_id in (
select question_id from ( 
	select question_id 
	from  list_question_too_long_content)as temp);

-- Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column.  To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.

-- Error Code: 1443. The definition of table 'list_question_too_long_content' prevents operation DELETE on table 'question'. 
-- nghĩa là : mysql không cho việc xóa dữ liệu từ một view dùng chính dữ liệu của bảng đó -> như tên view tạo từ qusetion rồi xóa ở qusetion : vì làm như thế có thể làm cho dữ liệu trong view thay đổi : rối loạn trong việc đọc dữ liệu  ( tìm hiểu thêm ) 

--  CTE 
create view list_question_too_long_content2 as
	(with cte_id_long_content as (
		select question_id 
		from question
		where length(content) > 20 )
	
select question_id 
from cte_id_long_content );

select * from list_question_too_long_content2;
        
        
-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
-- sub query
select count(department_id) 
from account 
group by department_id
order by count(department_id) desc limit 1;


create	view list_department_most_employee as
select department_name, count(acc.department_id) as tong_so
from account acc
join department dep on dep.department_id = acc.department_id
group by dep.department_id
having tong_so  = (
	select count(department_id) 
	from account 
	group by department_id
	order by count(department_id) desc limit 1);

select * from list_department_most_employee;
-- CTE
create	view list_department_most_employee2
as ( with cte_most_employee as (
	select count(department_id) 
	from account 
	group by department_id
	order by count(department_id) desc limit 1)
    
select department_name, count( acc.department_id) tong_so
from account acc 
join department dep on acc.department_id = dep.department_id
group by dep.department_id
having tong_so = (select * from cte_most_employee ));

select  * from list_department_most_employee2;
-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo.
-- sub query
select account_id 
from account
where full_name like 'Nguyen%';


create view list_question_by_Nguyen as
select content as noi_dung
from question
where creator_id in ( 
		select account_id 
		from account
		where full_name like 'Nguyen%');
	
select * from list_question_by_Nguyen;

-- CTE
 
create view list_question_by_Nguyen2 as 
( with cte_id_creator_Nguyen as (
		select account_id 
		from account
		where full_name like 'Nguyen%' )
        
select content 
from question 
where creator_id in (select * from  cte_id_creator_Nguyen));

select * from  list_question_by_Nguyen2;

