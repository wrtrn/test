package complexTasks.taskManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService<T> {

    private List<Task<?>> tasksList = new ArrayList<>();

    public List<Task<?>> getTasksList() {
        return tasksList;
    }

    public void addTask(Task task) {
        boolean flag = false;
        for (Task taskFromList : tasksList) {
            if (taskFromList.getId() == task.getId() || taskFromList.getId().equals(task.getId())) {
                flag = true;
                System.out.println("We are already have a task with this id");
                break;
            }
        }
        if (!flag)
            tasksList.add(task);
    }

    public synchronized void removeTask(T id) {
        tasksList.removeIf(taskFromList -> taskFromList.getId() == id || taskFromList.getId().equals(id));
    }

    public List<Task> findTaskByStatus(String status) {
        return tasksList.stream().filter(el -> el.getStatus().equals(status)).collect(Collectors.toList());
    }

    public List<Task> findTaskByPriority(String priority) {
        return tasksList.stream().filter(el -> el.getPriority().equals(priority)).collect(Collectors.toList());
    }

    public void sortTasksByDate() {
        tasksList = tasksList.stream().sorted(Comparator.comparing(Task::getDate)).collect(Collectors.toList());
    }
}
