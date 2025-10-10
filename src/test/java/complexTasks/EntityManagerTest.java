package complexTasks;

import expressJava.complexTasks.entityManager.EntityManager;
import expressJava.complexTasks.entityManager.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityManagerTest {

    @Test
    public void addElementTest() {
        Student entity = new Student("Ivan", 10, true);
        Student expectedStudent = new Student("Ivan", 10, true);
        EntityManager<Student> entityManager = new EntityManager<>();

        int initialSize = entityManager.getAllElements().size();

        entityManager.addElement(entity);

        assertTrue(entityManager.getAllElements().contains(entity));

        assertEquals(initialSize + 1, entityManager.getAllElements().size());

        Student actualStudent = entityManager.getAllElements().getFirst();
        assertEquals(expectedStudent.getAge(), actualStudent.getAge());
        assertEquals(expectedStudent.getName(), actualStudent.getName());
        assertEquals(expectedStudent.isActive(), actualStudent.isActive());
    }

    @Test
    public void removeElementTest() {
        Student entity1 = new Student("Ivan", 10, true);
        Student entity2 = new Student("Petr", 12, false);
        EntityManager<Student> entityManager = new EntityManager<>();

        //removing from empty test
        entityManager.removeElement(entity1);
        int actualSize = entityManager.getAllElements().size();
        assertEquals(0, actualSize);

        //element not found returns false test
        Assertions.assertFalse(entityManager.removeElement(entity1));

        entityManager.addElement(entity1);
        entityManager.addElement(entity2);

        //removing returns true test
        Assertions.assertTrue(entityManager.removeElement(entity1));

        //we have only 1 element test
        actualSize = entityManager.getAllElements().size();
        assertEquals(1, actualSize);

        //necessary entity removed
        assertEquals(entityManager.getAllElements().getFirst(), entity2);

        entityManager.removeElement(entity2);
        actualSize = entityManager.getAllElements().size();
        //we have 0 elements after removing last test
        assertEquals(0, actualSize);
    }

    @Test
    public void getAllElementsTest() {
        Student entity1 = new Student("Ivan", 10, true);
        Student entity2 = new Student("Petr", 12, false);
        Student entity3 = new Student("Sergey", 13, true);
        EntityManager<Student> entityManager = new EntityManager<>();

        List<Student> recievedList = new ArrayList<>();

        entityManager.addElement(entity1);
        entityManager.addElement(entity2);
        recievedList = entityManager.getAllElements();

        assertEquals(recievedList.getFirst(), entity1);
        assertEquals(recievedList.getLast(), entity2);

        List<Student> finalRecievedList = recievedList;
        Assertions.assertThrows(UnsupportedOperationException.class, () -> finalRecievedList.add(entity3));
    }


    @Test
    public void ageFilterTest() {
        Student entity1 = new Student("Ivan", 10, true);
        Student entity2 = new Student("Petr", 15, false);
        Student entity3 = new Student("Sergey", 19, true);
        EntityManager<Student> entityManager = new EntityManager<>();

        entityManager.addElement(entity1);
        entityManager.addElement(entity2);
        entityManager.addElement(entity3);
        List<Student> age11to99 = entityManager.ageFilter(11, 99);

        assertEquals(2, age11to99.size());
        assertEquals(entity2, age11to99.getFirst());
        assertEquals(entity3, age11to99.getLast());

        List<Student> ageLessThan9 = entityManager.ageFilter(0, 9);
        assertEquals(0, ageLessThan9.size());

    }

    @Test
    public void nameFilterTest() {
        Student entity1 = new Student("Ivan", 10, true);
        Student entity2 = new Student("Petr", 15, false);
        Student entity3 = new Student("Petr", 25, false);
        Student entity4 = new Student("Sergey", 19, true);
        EntityManager<Student> entityManager = new EntityManager<>();

        entityManager.addElement(entity1);
        entityManager.addElement(entity2);
        entityManager.addElement(entity3);
        entityManager.addElement(entity4);

        List<Student> petrList = entityManager.nameFilter("Petr");

        assertEquals(2, petrList.size());
        assertEquals(entity2, petrList.getFirst());
        assertEquals(entity3, petrList.getLast());

        List<Student> noNames = entityManager.nameFilter("Gregory");
        assertEquals(0, noNames.size());
    }

    @Test
    public void activityFilterTest() {
        Student entity1 = new Student("Ivan", 10, true);
        Student entity2 = new Student("Petr", 15, false);
        Student entity3 = new Student("Leonid", 25, false);
        Student entity4 = new Student("Sergey", 19, true);
        EntityManager<Student> entityManager = new EntityManager<>();

        entityManager.addElement(entity1);
        entityManager.addElement(entity2);
        entityManager.addElement(entity3);
        entityManager.addElement(entity4);

        List<Student> falseList = entityManager.activityFilter(false);
        List<Student> trueList = entityManager.activityFilter(true);

        assertEquals(2, falseList.size());
        assertEquals(2, trueList.size());
        assertTrue(falseList.contains(entity2) && falseList.contains(entity3));
        assertTrue(trueList.contains(entity1) && trueList.contains(entity4));

    }
}
