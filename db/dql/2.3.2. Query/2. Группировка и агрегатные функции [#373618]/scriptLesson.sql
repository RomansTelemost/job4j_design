select avg(mark) from students_subjects;
select min(mark) from students_subjects;
select max(mark) from students_subjects;
select count(id) from students_subjects;
select sum(mark) from students_subjects;

select s.name, avg(ss.mark) from students_subjects as ss
join students as s on ss.student_id = s.id group by s.name;

select s.name from students_subjects as ss
join subjects as s on ss.subject_id = s.id group by s.name
having avg(ss.mark) > 4.2;
