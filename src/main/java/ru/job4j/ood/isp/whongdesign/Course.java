package ru.job4j.ood.isp.whongdesign;

import com.sun.nio.sctp.IllegalReceiveException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Course {

    private int hours;

    protected int numberOfRequiredExams;

    protected List<Student> students;

    protected Map<Student, Integer> studentPassExams;

    private boolean hasStarted = false;

    public Course(int hours, int numberOfRequiredExams, List<Student> students) {
        this.hours = hours;
        this.numberOfRequiredExams = numberOfRequiredExams;
        this.students = students;
    }

    public void canStartCourse() {
        if (hours < 1) {
            throw new IllegalReceiveException("Invalid course duration");
        }
        if (numberOfRequiredExams < 1) {
            throw new IllegalReceiveException("Invalid course required exams");
        }
        if (students.size() < 10) {
            throw new IllegalReceiveException("Not enough students");
        }
    }

    public void startCourse() {
        if (hasStarted) {
            throw new IllegalReceiveException("Course has already start");
        }
        canStartCourse();
        this.hasStarted = true;
        studentPassExams = students.stream().collect(Collectors.toMap(stu -> stu,
                stu -> numberOfRequiredExams));
    }

    public boolean isStudentPassCourse(Student student) {
        if (!students.contains(student)) {
            throw new IllegalReceiveException("Student has not start that course");
        }
        Integer passExams = studentPassExams.get(student);
        if (passExams == null) {
            return false;
        }
        if (passExams < numberOfRequiredExams) {
            return false;
        }
        return true;
    }
}
