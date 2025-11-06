package learning.expressJava.complexTasks.studentsGrade;

import java.util.ArrayList;
import java.util.List;

public class GradeService<T extends Number> extends StudentGrade {
    private List<StudentGrade<T>> gradeList = new ArrayList<>();

    public GradeService(String name, String subject, T grade) {
        super(name, subject, grade);
        addGrade(new Student(name, subject, grade));
    }

    public List<StudentGrade<T>> getGradeList() {
        return gradeList;
    }

    public synchronized boolean addGrade(StudentGrade<T> grade) {
        if (grade.getGrade().doubleValue() >= 0) {
            gradeList.add(grade);
            return true;
        } else {
            throw new InvalidGradeException("Incorrect grade");
        }
    }

    public double averageGradeForSubject(String subject) {
        return gradeList.stream()
                .filter((el) -> el.getSubject().equals(subject))
                .mapToDouble(el -> (el.getGrade().doubleValue()))
                .average()
                .orElse(0);
    }
}
