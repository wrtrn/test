package learning.javaCore;

import learning.expressJava.complexTasks.taskManager.Task;
import learning.expressJava.complexTasks.taskManager.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskServiceTest {

    @Test
    public void addTaskTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        TaskService taskService = new TaskService();
        taskService.addTask(new Task(1, "Active", "High", sdf.parse("02.02.02")));
        taskService.addTask(new Task(2, "Passive", "Low", sdf.parse("02.02.02")));
        taskService.addTask(new Task(true, "Neutral", "Medium", sdf.parse("02.02.03")));
        taskService.addTask(new Task(4, "Active", "High", sdf.parse("02.01.02")));
        taskService.addTask(new Task(5, "Active", "High", sdf.parse("01.05.02")));
        taskService.addTask(new Task(6, "Active", "High", sdf.parse("15.05.06")));
        taskService.addTask(new Task(5, "Active", "High", sdf.parse("01.05.02")));
        taskService.addTask(new Task("Saturday", "Active", "High", sdf.parse("15.05.06")));

        Assertions.assertEquals(7, taskService.getTasksList().size());
        Assertions.assertEquals("Passive", ((Task<?>) taskService.getTasksList().get(1)).getStatus());
        Assertions.assertEquals(2, ((Task<?>) taskService.getTasksList().get(1)).getId());
        Assertions.assertEquals("Low", ((Task<?>) taskService.getTasksList().get(1)).getPriority());
    }

    @Test
    public void removeTaskTest() throws ParseException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        TaskService taskService = new TaskService();
        taskService.addTask(new Task(1, "Active", "High", sdf.parse("02.02.02")));
        taskService.addTask(new Task(2, "Passive", "Low", sdf.parse("02.02.02")));
        taskService.addTask(new Task(3, "Neutral", "Medium", sdf.parse("02.02.03")));
        taskService.addTask(new Task(true, "Active", "High", sdf.parse("02.01.02")));
        taskService.addTask(new Task(5, "Active", "High", sdf.parse("01.05.02")));
        taskService.addTask(new Task("Saturday", "Active", "High", sdf.parse("15.05.06")));

        taskService.removeTask(3);
        Assertions.assertEquals(5, taskService.getTasksList().size());

        taskService.removeTask(3);
        Assertions.assertEquals(5, taskService.getTasksList().size());

        Thread t1 = new Thread(() -> taskService.removeTask(1));
        Thread t2 = new Thread(() -> taskService.removeTask(2));
        Thread t3 = new Thread(() -> taskService.removeTask(true));
        Thread t4 = new Thread(() -> taskService.removeTask(5));
        Thread t5 = new Thread(() -> taskService.removeTask("Saturday"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        Assertions.assertEquals(0, taskService.getTasksList().size());
    }

    @Test
    public void findTaskByStatusTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        TaskService taskService = new TaskService();
        taskService.addTask(new Task(1, "Active", "High", sdf.parse("02.02.02")));
        taskService.addTask(new Task(2, "Passive", "Low", sdf.parse("02.02.02")));
        taskService.addTask(new Task(3, "Neutral", "Medium", sdf.parse("02.02.03")));
        taskService.addTask(new Task(true, "Active", "High", sdf.parse("02.01.02")));
        taskService.addTask(new Task(5, "Active", "High", sdf.parse("01.05.02")));
        taskService.addTask(new Task("Saturday", "Active", "High", sdf.parse("15.05.06")));

        Assertions.assertEquals(4, taskService.findTaskByStatus("Active").size());
        Assertions.assertEquals("Active", ((Task) taskService.findTaskByStatus("Active").getFirst()).getStatus());
        Assertions.assertEquals(0, taskService.findTaskByStatus("NEW").size());
    }

    @Test
    public void findTaskByPriorityTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        TaskService taskService = new TaskService();
        taskService.addTask(new Task(1, "Active", "High", sdf.parse("02.02.02")));
        taskService.addTask(new Task(2, "Passive", "Low", sdf.parse("02.02.02")));
        taskService.addTask(new Task(3, "Neutral", "Medium", sdf.parse("02.02.03")));
        taskService.addTask(new Task(true, "Active", "High", sdf.parse("02.01.02")));
        taskService.addTask(new Task(5, "Active", "High", sdf.parse("01.05.02")));
        taskService.addTask(new Task("Saturday", "Active", "High", sdf.parse("15.05.06")));

        Assertions.assertEquals(4, taskService.findTaskByPriority("High").size());
        Assertions.assertEquals("High", ((Task) taskService.findTaskByPriority("High").getFirst()).getPriority());
        Assertions.assertEquals(0, taskService.findTaskByPriority("NEW").size());
    }

    @Test
    public void sortTasksByDateTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        TaskService taskService = new TaskService();
        taskService.addTask(new Task(1, "Active", "High", sdf.parse("02.02.2005")));
        taskService.addTask(new Task(2, "Passive", "Low", sdf.parse("02.02.2002")));
        taskService.addTask(new Task(3, "Passive", "Low", sdf.parse("02.02.2002")));
        taskService.addTask(new Task(7, "Neutral", "Medium", sdf.parse("02.02.2003")));
        taskService.addTask(new Task(true, "Active", "High", sdf.parse("02.01.2002")));
        taskService.addTask(new Task(5, "Active", "High", sdf.parse("01.05.2004")));
        taskService.addTask(new Task("Saturday", "Active", "High", sdf.parse("15.05.2006")));

        taskService.sortTasksByDate();
        Assertions.assertEquals(7, taskService.getTasksList().size());
        Assertions.assertEquals(sdf.parse("02.01.2002"), ((Task) taskService.getTasksList().getFirst()).getDate());
        Assertions.assertEquals(sdf.parse("02.02.2002"), ((Task) taskService.getTasksList().get(1)).getDate());
        Assertions.assertEquals(sdf.parse("15.05.2006"), ((Task) taskService.getTasksList().getLast()).getDate());
    }
}
