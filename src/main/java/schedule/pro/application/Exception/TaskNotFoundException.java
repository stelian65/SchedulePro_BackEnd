package schedule.pro.application.Exception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException() {
        super("Task not found");
    }
}
