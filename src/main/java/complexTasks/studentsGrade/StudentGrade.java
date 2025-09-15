package complexTasks.studentsGrade;

import java.lang.Number;

public abstract class StudentGrade<T extends Number> {
    private String name;
    private String subject;
    private T grade;

    public StudentGrade(String name, String subject, T grade) {
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public T getGrade() {
        return grade;
    }
}
