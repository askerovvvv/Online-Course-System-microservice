insert into role(id, name) values (1, 'ROLE_USER')

insert into faculty (faculty_name, id) values ('Law', 1)
insert into faculty (faculty_name, id) values ('Computer Science', 2)
insert into faculty (faculty_name, id) values ('Marketing', 3)
insert into faculty (faculty_name, id) values ('Economics', 4)
insert into faculty (faculty_name, id) values ('Medicine', 5)

insert into group_ (faculty_id, group_name, id) values (1, 'LAW-2024', 1)
insert into group_ (faculty_id, group_name, id) values (2, 'SCA-2020A', 2)
insert into group_ (faculty_id, group_name, id) values (2, 'SCA-2023B', 3)
insert into group_ (faculty_id, group_name, id) values (3, 'MAR-2018B', 4)
insert into group_ (faculty_id, group_name, id) values (4, 'ECO-2020A', 5)
insert into group_ (faculty_id, group_name, id) values (5, 'MED-2019C', 6)

insert into user_ (email, email_verified, firstname, lastname, password, id) values ('student1@test.com', true, 'John', 'Smith', 'hashedPass', 1)
insert into student (citizenship, credit, house_num, living_city, street, zipcode, student_group_id, id) values ('USA', 45, '23A', 'Bishkek', '12-mkr', '72343', 1, 1)

insert into user_ (email, email_verified, firstname, lastname, password, id) values ('student2@test.com', true, 'Kutman', 'Bekeshev', 'hashedPass', 2)
insert into student (citizenship, credit, house_num, living_city, street, zipcode, student_group_id, id) values ('Kyrgyzstan', 42, '12/2', 'Bishkek', 'Gagarina 23', '4323', 2, 2)

insert into user_ (email, email_verified, firstname, lastname, password, id) values ('student3@test.com', true, 'Keremet', 'Akylova', 'hashedPass', 3)
insert into student (citizenship, credit, house_num, living_city, street, zipcode, student_group_id, id) values ('Kazakhstan', 33, '5/2', 'Astana', 'street1/2', '423', 2, 3)

insert into user_ (email, email_verified, firstname, lastname, password, id) values ('student4@test.com', true, 'Emanual', 'Macron', 'hashedPass', 4)
insert into student (citizenship, credit, house_num, living_city, street, zipcode, student_group_id, id) values ('France', 23, '55', 'Paris', 'paris street/2', '7643', 4, 4)

insert into user_ (email, email_verified, firstname, lastname, password, id) values ('student5@test.com', true, 'Aktan', 'Akasov', 'hashedPass', 5)
insert into student (citizenship, credit, house_num, living_city, street, zipcode, student_group_id, id) values ('Uzbekistan', 43, '23A', 'Bishkek', '8-mkr', '72343', 4, 5)

insert into user_ (email, email_verified, firstname, lastname, password, id) values ('student6@test.com', true, 'Petr', 'Gena', 'hashedPass', 6)
insert into student (citizenship, credit, house_num, living_city, street, zipcode, student_group_id, id) values ('Kyrgyzstan', 45, '23A', 'Osh', 'teststreet/23', '6534', 5, 6)

insert into user_ (email, email_verified, firstname, lastname, password, id) values ('student7@test.com', true, 'Mac', 'Macbook', 'hashedPass', 7)
insert into student (citizenship, credit, house_num, living_city, street, zipcode, student_group_id, id) values ('Canada', 55, '23A', 'Bishkek', 'tes12/ww', '532', 5, 7)

insert into user_ (email, email_verified, firstname, lastname, password, id) values ('student8@test.com', true, 'Hasan', 'Hasanbay', 'hashedPass', 8)
insert into student (citizenship, credit, house_num, living_city, street, zipcode, student_group_id, id) values ('Turkmenistan', 23, '54F', 'Tokmok', 'sham/32', '7654', 5, 8)

insert into user_roles (user_id, role_id) values (1,1)
insert into user_roles (user_id, role_id) values (2,1)
insert into user_roles (user_id, role_id) values (3,1)
insert into user_roles (user_id, role_id) values (4,1)
insert into user_roles (user_id, role_id) values (5,1)
insert into user_roles (user_id, role_id) values (6,1)
insert into user_roles (user_id, role_id) values (7,1)
insert into user_roles (user_id, role_id) values (8,1)
