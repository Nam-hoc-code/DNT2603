use baitap_vti;

-- Question 2: lấy ra tất cả các phòng ban
select department_name as 'Các phòng ban'  
from department;

-- Question 3: lấy ra id của phòng ban "Sales"
select department_id as 'id phòng Sales' 
from department
where department_name = 'Sales';

-- Question 4: lấy ra thông tin account có full name dài nhất
select length(full_name) from account;

select account_id as 'Id', email as 'Email', username as 'Username', full_name as 'Full name'
from account 
order by length(full_name) desc  -- asc : giảm dần
limit 1;

-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id= 3
select account_id as 'Id', email as 'Email', username as 'Username', full_name as 'Full name'
from account 
where department_id = 3
order by length(full_name) desc  -- asc : giảm dần
limit 1;

-- Question 6: Lấy ra tên group đã tham gia trước ngày 20/12/2019
select g.group_name
from `group` g, group_account ga
where g.group_id = ga.group_id 
and join_date < '2026-08-03';

-- Question 7: Lấy ra ID của question có >= 4 câu trả lời
select question_id 'Id question'
from answer 
group by question_id 
having count(*) >= 4;

	
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