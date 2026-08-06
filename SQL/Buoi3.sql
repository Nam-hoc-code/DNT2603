use baitap_vti;

-- Question 2: lấy ra tất cả các phòng ban
select department_name as 'Các phòng ban'  
from department;

-- Question 3: lấy ra id của phòng ban "Sales"
select department_id as 'id phòng Sales' 
from department
where department_name = 'Sales';

-- Question 4: lấy ra thông tin account có full name dài nhất
select length(full_name) from account 
order by length(full_name) desc limit 1;

select account_id as 'Id', email as 'Email', username as 'Username', full_name as 'Full name'
from account 
where length(full_name) = (
	select length(full_name) from account 
	order by length(full_name) desc limit 1 
    );
-- select max(length(full_name)) from account;
-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id= 3
select account_id as 'Id', email as 'Email', username as 'Username', full_name as 'Full name'
from account 
where department_id = 3 and length(full_name) = (
select length(full_name) from account 
where department_id = 3
order by length(full_name) desc limit 1 );

-- Question 6: Lấy ra tên group đã tham gia trước ngày 20/12/2019
select g.group_name
from `group` as g, group_account as ga
where g.group_id = ga.group_id 
and join_date < '2026-08-03';

select * from `group_account`;
select * from `group`;
-- Question 7: Lấy ra ID của question có >= 4 câu trả lời
select question_id 'Id question'
from answer 
group by question_id 
having count(1) >= 4;

	
-- Question 8: Lấy ra các mã đề thi có thời gian thi >= 60 phút và được tạo trước ngày 20/12/2019
select code
from exam
where duration >= 60
and create_date < '2019-12-20';

-- Question 9: Lấy ra 5 group được tạo gần đây nhất

select *
from `group`
order by create_date desc
limit 5;

-- Question 10: Đếm số nhân viên thuộc department có id = 2 
select count(*)
from account
where department_id = 2;
 
-- Question 11: Lấy ra nhân viên có tên bắt đầu bằng chữ "D" và kết thúc bằng chữ "o"

select *
from account
where full_name like 'D%o';


select * -- False
from `account`
group by position_id
having count(position_id)> 2;

-- Error Code: 1055. Expression #1 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'baitap_vti.account.account_id' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by
-- -> Giải thích : khi đã group by thì dữ liệu trả về 
-- có thể select chỉ có thể  lấy các dữ liệu đã group by ko
--  thể lấy các field đã group by

select position_id -- True 
from `account`
group by position_id
having count(position_id)> 2;
 
-- alterError Code: 1054. Unknown column 'fullname' in 'field list'

select * from account;
insert into `account`
(email, username, full_name, department_id, position_id)
values
('nam@gmail.com','nam','Le Thanh Nam',1,1);


select department_name, count(acc.department_id)
from department dep
left join account acc on acc.department_id = dep.department_id
group by dep.department_id;

-- group thì nên group bên đầy đủ. 
-- đếm thì nên đếm bên thiếu. 
select * from account;


-- Question 12: Xóa tất cả các exam được tạo trước ngày 20/12/2019
delete eq
from exam_question eq
join exam e
    on eq.exam_id = e.exam_id
where e.create_date < '2019-12-20';

delete from exam
where create_date < '2019-12-20';

--  Question 13 : Xóa tất cả các question có nội dung bắt đầu bằng từ "câu hỏi"
delete from question
where content like 'câu hỏi%';


-- Question 14: Update thông tin của account có id = 5 thành tên "Nguyễn Bá Lộc" và email thành loc.nguyenba@vti.com.vn
update account
set
    full_name = 'Nguyễn Bá Lộc',
    email = 'loc.nguyenba@vti.com.vn'
where account_id = 5;

-- Question 15: update account có id = 5 sẽ thuộc group có id = 4 
update group_account
set group_id = 4
where account_id = 5;