package complexTasks;

import complexTasks.studentsGrade.GradeService;
import complexTasks.studentsGrade.InvalidGradeException;
import complexTasks.studentsGrade.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GradeServiceTest {

    @Test
    public void addGradeTest() throws InterruptedException {

        GradeService<Integer> gradeService = new GradeService<>("Petr", "Russian", 4);

        Student student2 = new Student("Ivan", "Math", 5);
        Student student3 = new Student("Zinovyi", "Math", 0);
        Student student4 = new Student("Zinovyi", "Math", 5.5);

        Thread t1 = new Thread(() -> gradeService.addGrade(student2));
        Thread t2 = new Thread(() -> gradeService.addGrade(student3));
        Thread t3 = new Thread(() -> gradeService.addGrade(student4));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        int actualGradeListSize = gradeService.getGradeList().size();
        //size and multithreading check
        Assertions.assertEquals(4, gradeService.getGradeList().size());

        //contains exact users check
        Assertions.assertTrue(gradeService.getGradeList().contains(student2));
        Assertions.assertTrue(gradeService.getGradeList().contains(student4));

        Assertions.assertTrue(gradeService.getGradeList().getFirst().getName().equals("Petr"));
        Assertions.assertTrue(gradeService.getGradeList().getFirst().getSubject().equals("Russian"));
        Assertions.assertTrue(gradeService.getGradeList().getFirst().getGrade().equals(4));


        Student student9 = new Student("Zinovyi", "Math", -1);
        //exception test
        Assertions.assertThrows(InvalidGradeException.class, () -> gradeService.addGrade(student9));
    }

    @Test
    public void averageTest() {

        GradeService<Integer> gradeService = new GradeService<>("Petr", "Russian", 4);

        Student student2 = new Student("Ivan", "Russian", 5);
        Student student3 = new Student("Zinovyi", "Math", 0);
        Student student4 = new Student("Zinovyi", "Math", 5.5);

        gradeService.addGrade(student2);
        gradeService.addGrade(student3);
        gradeService.addGrade(student4);

        Assertions.assertEquals(2.75, gradeService.averageGradeForSubject("Math"));
        Assertions.assertEquals(4.5, gradeService.averageGradeForSubject("Russian"));
        Assertions.assertEquals(0.0, gradeService.averageGradeForSubject("1234"));
    }
}
