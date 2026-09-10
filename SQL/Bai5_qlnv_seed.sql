CREATE DATABASE IF NOT EXISTS qlnv;
USE qlnv;

-- Sửa lỗi enum trong script gốc: enum('DEV, MANAGER, LEAD, TESTER') chỉ là 1 giá trị
ALTER TABLE position MODIFY position_name ENUM('DEV', 'MANAGER', 'LEAD', 'TESTER');

-- 4 vị trí
INSERT INTO position (position_name) VALUES
('DEV'),
('MANAGER'),
('LEAD'),
('TESTER');

-- 3 phòng ban (id_maneger sẽ được cập nhật sau khi có account)
INSERT INTO department (id_maneger, department_name, number_people) VALUES
(NULL, 'Marketing',   3),
(NULL, 'Development', 5),
(NULL, 'Support',     2);

-- 10 account (giả định id lần lượt 1 -> 10 trên bảng rỗng mới)
INSERT INTO account (`name`, location, account_name, `password`, id_position, id_department) VALUES
('Nguyễn Văn An',     'Hà Nội',     'an.nv',     '123456', 1, 2),
('Trần Thị Bình',     'Hồ Chí Minh', 'binh.tt',    '123456', 2, 2),
('Lê Văn Cường',      'Đà Nẵng',    'cuong.lv',   '123456', 1, 2),
('Phạm Thị Dung',     'Hà Nội',     'dung.pt',    '123456', 3, 2),
('Hoàng Văn Đạt',     'Hải Phòng',  'dat.hv',     '123456', 4, 2),
('Ngô Thị Hà',        'Cần Thơ',    'ha.nt',      '123456', 1, 1),
('Đỗ Văn Hùng',       'Hà Nội',     'hung.dv',    '123456', 2, 1),
('Vũ Thị Lan',        'Đà Nẵng',    'lan.vt',     '123456', 3, 1),
('Bùi Văn Minh',      'Hồ Chí Minh', 'minh.bv',    '123456', 4, 3),
('Đặng Thị Ngọc',     'Huế',        'ngoc.dt',    '123456', 1, 3);

-- Gán trưởng phòng (id_maneger -> account)
-- Marketing: Hùng (7), Development: Bình (2), Support: Minh (9)
UPDATE department SET id_maneger = 7 WHERE department_name = 'Marketing';
UPDATE department SET id_maneger = 2 WHERE department_name = 'Development';
UPDATE department SET id_maneger = 9 WHERE department_name = 'Support';