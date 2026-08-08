use baitap_vti;

-- Bài tập 1: JOIN

-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ
select * 
from account acc
left join  department dep on acc.department_id = dep.department_id;
-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010
select * 
from account acc
where create_date > '2026-08-02'; -- 2026-08-02 00:00:00
select * from position;
-- Question 3: Viết lệnh để lấy ra tất cả các developer
select * 
from account
where position_id = (select position_id from position where position_name = 'DEV'); 

-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên

select department_name, count(acc.department_id)
from account acc
right join department dep on dep.department_id = acc.department_id
group by dep.department_name
having count(acc.department_id) > 3;

select * from question;
-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất -- để sau 
-- sub query
select count(question_id) from exam_question 
group by question_id
order by count(question_id) desc
limit 1;


-- Query chính
select qt.content as 'Tên câu hỏi', count(eq.question_id) 
from exam_question eq
right join question qt on qt.question_id = eq.question_id
group by qt.question_id
having count( eq.question_id) = (
select count(question_id) from exam_question 
group by question_id
order by count(question_id) desc
limit 1);



-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question
select cq.category_name as 'Tên danh mục', count(qt.question_id) as 'Tổng số'
from category_question cq
left join question qt on cq.category_id = qt.category_id
group by cq.category_id;


-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam
select * from exam_question;

select qt.content as 'Tên câu hỏi', count(eq.exam_id) 
from exam_question eq
right join question qt on qt.question_id = eq.question_id
group by qt.question_id;

-- Question 8: Lấy ra Question có nhiều câu trả lời nhất
select count(question_id) 
from answer
group by question_id
order by count(question_id) desc limit 1;
-- 
select * from question;

select qt.content 'Câu hỏi', count(aw.question_id) 'Tổng số'
from answer aw
right join question qt on aw.question_id = qt.question_id
group by qt.question_id
having count(aw.question_id) = (
select count(question_id) 
from answer
group by question_id
order by count(question_id) desc limit 1)
;
-- Question 9: Thống kê số lượng account trong mỗi group
select * from group_account;
select * from `group`;

select group_name 'Tên nhóm', count(ga.account_id) 'Số thành viên'
from group_account ga
right join `group`  gr on gr.group_id = ga.group_id
group by gr.group_id;

-- Question 10: Tìm chức vụ có ít người nhất


select count(position_id) 
from account
group by  position_id
order by count(position_id) asc 
limit 1;

select pst.position_name 'Tên Phòng', count(acc.position_id) 'Số thành viên'
from position pst
left join account acc on pst.position_id = acc. position_id
group by pst.position_id
having count(acc.position_id) = ( 
select count(position_id)
from account
group by  position_id
order by count(position_id) asc 
limit 1); 

-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM
select
    d.department_name as 'Department',
    sum(case when p.position_name = 'DEV' then 1 else 0 end) as 'DEV',
    sum(case when p.position_name = 'TEST' then 1 else 0 end) as 'TEST',
    sum(case when p.position_name = 'SCRUM MASTER' then 1 else 0 end) as 'SCRUM MASTER',
    sum(case when p.position_name = 'PM' then 1 else 0 end) as 'PM'
from department d -- cả hai bảng
left join account a
    on d.department_id = a.department_id
left join `position` p
    on a.position_id = p.position_id
group by d.department_id, d.department_name;

select * from account;
-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, ...

select qs.content as 'câu hỏi', cq.category_name 'loại danh mục', acc.full_name 'tên người tạo câu hỏi', tq.type_name 'kiểu câu hỏi' , aw.content 'kết quả'
from question qs
left join category_question cq 
		on qs.category_id = cq.category_id
left join account acc 
		on acc.account_id = qs.creator_id
left join type_question tq 
		on tq.type_id = qs.type_id
left join answer aw
		on aw.question_id = qs.question_id;
-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm
select * from question;
select * from department;

select type_name 'tên loại' , count(qt.type_id) 
from type_question tq
left join question qt on tq.type_id = qt.type_id
group by tq.type_id;

-- Question 14: Lấy ra group không có account nào -- Question 15: Lây ra group không có account nào

select gr.group_name,
       count(ga.account_id)
from `group` gr
left join group_account ga
    on gr.group_id = ga.group_id
group by gr.group_id, gr.group_name
having count(ga.account_id) = 0;
	
-- Question 16: Lấy ra question không có answer nào

select * from question;
select * from answer;

select qs.content 'Tên câu hỏi', count(aw.question_id) 'Tổng số'
from answer aw
right join question qs on qs.question_id = aw.question_id
group by qs.question_id
having count(aw.question_id) = 0;

-- 2. Union.
-- Question 17:
-- a) Lấy các account thuộc nhóm thứ 1
-- b) Lấy các account thuộc nhóm thứ 2
-- c) Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau

select * 
from account acc
join group_account ga on ga.account_id = acc.account_id
where group_id = 1 
union 
select * 
from account acc
join group_account ga on ga.account_id = acc.account_id
where group_id = 2 ;
select * from `group_account` ;

-- Question 18:
-- a) Lấy các group có lớn hơn 5 thành viên
-- b) Lấy các group có nhỏ hơn 7 thành viên
-- c) Ghép 2 kết quả từ câu a) và câu b).

select gr.group_name, count(ga.account_id)
from `group` gr
join group_account ga on gr.group_id =  ga.group_id
group by gr.group_id
having count(ga.account_id) > 5
union all
select gr.group_name, count(ga.account_id)
from `group` gr
join group_account ga on gr.group_id =  ga.group_id
group by gr.group_id
having count(ga.account_id) < 7;
