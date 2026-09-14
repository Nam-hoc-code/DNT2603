DROP DATABASE IF EXISTS qlnv;

CREATE DATABASE qlnv;

USE qlnv;


-- =========================================================
-- POSITION
-- =========================================================

CREATE TABLE `position` (
                            id_position INT PRIMARY KEY AUTO_INCREMENT,
                            position_name ENUM('DEV', 'MANAGER', 'LEAD', 'TESTER') NOT NULL
);


-- =========================================================
-- DEPARTMENT
-- =========================================================

CREATE TABLE department (
                            id_department INT PRIMARY KEY AUTO_INCREMENT,
                            id_manager INT,
                            department_name VARCHAR(100) NOT NULL,
                            number_people INT
);


-- =========================================================
-- ACCOUNT
-- =========================================================

CREATE TABLE account (
                         id_account INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100),
                         location VARCHAR(100),
                         account_name VARCHAR(50),
                         password VARCHAR(20),

                         id_position INT,
                         id_department INT,

                         CONSTRAINT fk_account_to_position
                             FOREIGN KEY (id_position)
                                 REFERENCES `position`(id_position),

                         CONSTRAINT fk_account_to_department
                             FOREIGN KEY (id_department)
                                 REFERENCES department(id_department)
);

alter table department
    add constraint fk_department_to_account foreign key (id_manager) references `account` ( id_account );
-- =========================================================
-- INSERT POSITION
-- =========================================================

INSERT INTO `position` (position_name) VALUES
                                           ('DEV'),
                                           ('MANAGER'),
                                           ('LEAD'),
                                           ('TESTER');


-- =========================================================
-- INSERT DEPARTMENT
-- =========================================================

INSERT INTO department (
    id_manager,
    department_name,
    number_people
) VALUES
      (NULL, 'Marketing', 3),
      (NULL, 'Development', 5),
      (NULL, 'Support', 2);


-- =========================================================
-- INSERT ACCOUNT
-- =========================================================

INSERT INTO account (
    name,
    location,
    account_name,
    password,
    id_position,
    id_department
) VALUES
      ('Nguyễn Văn An',     'Hà Nội',       'an.nv',    '123456', 1, 2),
      ('Trần Thị Bình',     'Hồ Chí Minh',  'binh.tt',  '123456', 2, 2),
      ('Lê Văn Cường',      'Đà Nẵng',      'cuong.lv', '123456', 1, 2),
      ('Phạm Thị Dung',     'Hà Nội',       'dung.pt',  '123456', 3, 2),
      ('Hoàng Văn Đạt',     'Hải Phòng',    'dat.hv',   '123456', 4, 2),
      ('Ngô Thị Hà',        'Cần Thơ',      'ha.nt',    '123456', 1, 1),
      ('Đỗ Văn Hùng',       'Hà Nội',       'hung.dv',  '123456', 2, 1),
      ('Vũ Thị Lan',        'Đà Nẵng',      'lan.vt',   '123456', 3, 1),
      ('Bùi Văn Minh',      'Hồ Chí Minh',  'minh.bv',  '123456', 4, 3),
      ('Đặng Thị Ngọc',     'Huế',          'ngoc.dt',  '123456', 1, 3);


-- =========================================================
-- GÁN TRƯỞNG PHÒNG
-- =========================================================

-- UPDATE department
-- SET id_manager = 7
-- WHERE department_name = 'Marketing';

-- UPDATE department
-- SET id_manager = 2
-- WHERE department_name = 'Development';

-- UPDATE department
-- SET id_manager = 9
-- WHERE department_name = 'Support';


-- =========================================================
-- PROCEDURE: XÓA ACCOUNT
-- =========================================================

DELIMITER $$

CREATE PROCEDURE xoaTaiKhoan(IN id_input INT)
BEGIN
DELETE FROM account
WHERE id_account = id_input;
END $$

DELIMITER ;


-- =========================================================
-- PROCEDURE: XÓA DEPARTMENT
-- =========================================================

DELIMITER $$

CREATE PROCEDURE xoaPhong(IN id_input INT)
BEGIN
DELETE FROM department
WHERE id_department = id_input;
END $$

DELIMITER ;


-- =========================================================
-- PROCEDURE: XÓA POSITION
-- =========================================================

DELIMITER $$

CREATE PROCEDURE xoaViTri(IN id_input INT)
BEGIN
DELETE FROM `position`
WHERE id_position = id_input;
END $$

DELIMITER ;


-- =========================================================
-- PROCEDURE: ĐỔI TÊN ACCOUNT
-- =========================================================

DELIMITER $$

CREATE PROCEDURE capNhatTenTaiKhoan(
    IN id_input INT,
    IN ten_input VARCHAR(100)
)
BEGIN
UPDATE account
SET name = ten_input
WHERE id_account = id_input;
END $$

DELIMITER ;


-- =========================================================
-- PROCEDURE: ĐỔI TÊN DEPARTMENT
-- =========================================================

DELIMITER $$

CREATE PROCEDURE capNhatTenPhong(
    IN id_input INT,
    IN ten_input VARCHAR(100)
)
BEGIN
UPDATE department
SET department_name = ten_input
WHERE id_department = id_input;
END $$

DELIMITER ;

 -- ------------------------------
-- thêm thông tin
--     name,
--     location,
--     account_name,
--     password,    id_position,
 --   id_department
delimiter $$
create procedure themTaiKhoan(in `name` varchar(100), in location varchar (50), in account_name varchar(30), in `password` varchar (50),in id_position int, in id_department int)
begin
insert into `account` (name,location,account_name,password, id_position,id_department)
values
    ( `name`,location,account_name,`password`,id_position,id_department);
end;
delimiter;

drop procedure themTaiKhoan;
delimiter $$

create procedure themPhong (in id_maneger int, in department_name varchar(50),in number_people int)
begin
INSERT INTO department (
    id_manager,
    department_name,
    number_people
) VALUES
    (id_maneger, department_name, number_people);
end;
delimiter;

DELIMITER $$

CREATE PROCEDURE timKiem(IN p_name VARCHAR(100))
BEGIN
SELECT *
FROM account acc
WHERE acc.name LIKE CONCAT('%', p_name, '%');
END $$

DELIMITER ;