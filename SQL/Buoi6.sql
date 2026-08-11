use baitap_vti;

-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó.

delimiter $$ 
create procedure print_account_name ( IN department_name varchar(100) ) 
	begin 
		select acc.* 
        from account acc
        join department dep on  dep.department_id = acc.department_id
        where dep.department_name like concat( '%', department_name,'%' ) ; 
	end $$
delimiter ;

drop procedure print_account_name ;

select *  from `group`;
select * from group_account;

call print_account_name ('sale');
        

-- Question 2: Tạo store để in ra số lượng account trong mỗi group.
delimiter $$
create procedure print_account_each_group (IN group_name_in varchar(100))
	begin 
		select gr.group_name as 'Tên group' , count(acc.account_id) 'Số lượng'
        from `group` gr
        left join group_account ga on ga.group_id = gr.group_id
        left join account acc on acc.account_id = ga.account_id
        group by gr.group_id;
	end $$
delimiter ;

drop procedure print_account_each_group;


select  * from question;
select  * from type_question;
-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo
-- trong tháng hiện tại.
delimiter $$ 
create procedure thong_ke_type_question (  ) 
	begin	 
		select type_name  'Kiểu câu hỏi', count(qt.type_id) 'Số lượng'
        from type_question tq
        left join question qt on tq.type_id = qt.type_id 
        and month(qt.create_date) = month(now()) 
        and year(qt.create_date) = year(now()) 
        group by tq.type_id;
	end $$
delimiter ;

call thong_ke_type_question();

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất.
delimiter $$
create procedure return_id_of_type_question() 
	begin 
		select type_id id_question , count(qt.type_id) tong_so
        from type_question tq
        join question qt on  tq.type_id = qt.type_id 
        group by tq.type_id 
        having tong_so = ( select count(qt.type_id)  as tong_so
							from question qt
                            join type_question tq on tq.type_id = qt.type_id
                            group by tq.type_id
                            order by tong_so desc
                            limit 1 );
	end $$
    
delimiter ;


call return_id_of_type_question();
-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question.
delimiter $$
create procedure return_name_of_type_question() 
	begin 
		select tq.type_name name_question , count(qt.type_id) tong_so
        from type_question tq
        join question qt on  tq.type_id = qt.type_id 
        group by tq.type_id 
        having tong_so = ( select count(qt.type_id)  as tong_so
							from question qt
                            join type_question tq on tq.type_id = qt.type_id
                            group by tq.type_id
                            order by tong_so desc
                            limit 1 );
	end $$
    
delimiter ;
select  * from account;
call return_name_of_type_question();
-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên
-- chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào.

delimiter $$

create procedure search_group_or_account(in keyword_in varchar(100))
begin

    select 
        'group' as type,
        gr.group_id as id,
        gr.group_name as name
    from `group` gr
    where gr.group_name like concat('%', keyword_in, '%')

    union all

    select
        'account' as type,
        acc.account_id as id,
        acc.username as name
    from account acc
    where acc.username like concat('%', keyword_in, '%');

end $$

delimiter ; 
-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:
-- username sẽ giống email nhưng bỏ phần @..mail đi
-- positionID: sẽ có default là developer
-- departmentID: sẽ được cho vào 1 phòng chờ
-- Sau đó in ra kết quả tạo thành công
delimiter $$

create procedure create_account(
    in full_name_in varchar(100),
    in email_in varchar(100)
)
begin
    insert into account (
        email,
        username,
        full_name,
        department_id,
        position_id
    )
    values (
        email_in,
        substring_index(email_in, '@', 1),
        full_name_in,
        (
            select department_id
            from department
            where department_name = 'Waiting'
        ),
        (
            select position_id
            from `position`
            where position_name = 'DEV'
        )
    );
    select *
    from account
    where account_id = last_insert_id();
end $$

delimiter ;
  
        
call create_account( 'Nam','nam@gmail.com');


-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice
-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất
delimiter $$

create procedure find_longest_question(
    in type_name_in varchar(100)
)
begin

    select 
        qt.question_id,
        qt.content,
        length(qt.content) as content_length
    from question qt
    join type_question tq
        on tq.type_id = qt.type_id
    where tq.type_name = type_name_in
    order by length(qt.content) desc
    limit 1;

end $$

delimiter ;

call find_longest_question('Multiple-Choice');
-- 
-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID
delimiter $$

create procedure delete_exam(in exam_id_in int)
begin
    delete from exam_question
    where exam_id = exam_id_in;
    delete from exam
    where exam_id = exam_id_in;
end $$
delimiter ;
call delete_exam(5);
-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử
-- dụng store ở câu 9 để xóa)
-- Sau đó in số lượng record đã remove từ các table liên quan trong khi
-- removing
select *
from exam
where create_date < date_sub(now(), interval 3 year);

delimiter $$

create procedure delete_old_exam()
begin

    declare done int default 0;
    declare exam_id_value int;

    declare exam_cursor cursor for
        select exam_id
        from exam
        where create_date < date_sub(now(), interval 3 year);

    declare continue handler for not found set done = 1;

    create temporary table temp_delete_count (
        exam_question_count int,
        exam_count int
    );

    insert into temp_delete_count values (0, 0);

    open exam_cursor;

    read_loop: loop

        fetch exam_cursor into exam_id_value;

        if done = 1 then
            leave read_loop;
        end if;

        update temp_delete_count
        set exam_question_count = exam_question_count + (
            select count(*)
            from exam_question
            where exam_id = exam_id_value
        );

        call delete_exam(exam_id_value);

        update temp_delete_count
        set exam_count = exam_count + 1;

    end loop;

    close exam_cursor;

    select *
    from temp_delete_count;

    drop temporary table temp_delete_count;

end $$

delimiter ;

call delete_old_exam();
-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng
-- nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được
-- chuyển về phòng ban default là phòng ban chờ việc
delimiter $$

create procedure delete_department(
    in department_name_in varchar(100)
)
begin

    declare department_id_old bigint;
    declare department_id_waiting bigint;

    select department_id
    into department_id_old
    from department
    where department_name = department_name_in;

    select department_id
    into department_id_waiting
    from department
    where department_name = 'Waiting';

    update account
    set department_id = department_id_waiting
    where department_id = department_id_old;

    delete from department
    where department_id = department_id_old;

end $$

delimiter ;
-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
delimiter $$

create procedure statistic_question_each_month()
begin

    select
        month(create_date) as month_number,
        count(*) as question_count
    from question
    where year(create_date) = year(now())
    group by month(create_date)
    order by month_number;

end $$

delimiter ;
-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng")
delimiter $$

create procedure statistic_question_last_6_month()
begin

    with recursive months as (
        select date_format(date_sub(curdate(), interval 5 month), '%Y-%m') as month_value

        union all

        select date_format(
            date_add(str_to_date(concat(month_value, '-01'), '%Y-%m-%d'), interval 1 month),
            '%Y-%m'
        )
        from months
        where month_value < date_format(curdate(), '%Y-%m')
    )

    select
        m.month_value,
        case
            when count(q.question_id) = 0
                then 'không có câu hỏi nào trong tháng'
            else count(q.question_id)
        end as question_count
    from months m
    left join question q
        on date_format(q.create_date, '%Y-%m') = m.month_value
    group by m.month_value
    order by m.month_value;

end $$

delimiter ;