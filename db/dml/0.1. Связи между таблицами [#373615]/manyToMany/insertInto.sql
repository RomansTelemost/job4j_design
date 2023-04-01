insert into students(name) values('Ivan1');
insert into students(name) values('Ivan2');
insert into students(name) values('Ivan3');

insert into courses(name) values('1c');
insert into courses(name) values('Python');
insert into courses(name) values('Java');

insert into students_courses(student_id, course_id) values(1, 1);
insert into students_courses(student_id, course_id) values(1, 2);
insert into students_courses(student_id, course_id) values(1, 3);

insert into students_courses(student_id, course_id) values(2, 2);
insert into students_courses(student_id, course_id) values(2, 3);

insert into students_courses(student_id, course_id) values(3, 1);