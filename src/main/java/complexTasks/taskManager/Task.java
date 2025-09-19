package complexTasks.taskManager;

import java.util.Date;

public class Task<T> {
    private T id;
    private String status;
    private String priority;
    private Date date;

    public Task(T id, String status, String priority, Date date) {
        this.id = id;
        this.status = status;
        this.priority = priority;
        this.date = date;
    }

    public T getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public Date getDate() {
        return date;
    }
}
