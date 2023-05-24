package ru.job4j.ood.isp.wrongdesign;

import com.sun.nio.sctp.IllegalReceiveException;

import java.util.List;

/**
 * Не верно указано предусловие - оно усилено в методе "canCourseStart()"
 * Разрешено ослаблять предусловие - убрана проверка что уже курс запущен в методе "startCourse()"
 * Не верно указанано постусловие - ослаблено в методе isStudentPassCourse() - не сделана проверка на количество экзаменов студента
 */
public class JavaCourse extends Course {

    public JavaCourse(int hours, int numberOfRequiredExams, List<Student> students) {
        super(hours, numberOfRequiredExams, students);
    }

    @Override
    public void canStartCourse() {
        if (students.size() < 20) {
            throw new IllegalReceiveException("Not enough students");
        }
    }

    @Override
    public void startCourse() {
        super.startCourse();
    }

    @Override
    public boolean isStudentPassCourse(Student student) {
        if (!students.contains(student)) {
            throw new IllegalReceiveException("Student has not start that course");
        }
        Integer passExams = studentPassExams.get(student);
        if (passExams == null) {
            return false;
        }
        return true;
    }


}
