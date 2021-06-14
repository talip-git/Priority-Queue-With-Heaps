public class Task implements Comparable{
    //-----------------------------------------------------
    // Title: Task class
    // Description: the class where we keep the info about tasks
    //-----------------------------------------------------
    private String task_name;
    private long deadLine_time;
    private long duration_time;

    //Default constructor for Task class
    public Task(String task_name, long deadLine_time, long duration_time) {
        this.task_name = task_name;
        this.deadLine_time = deadLine_time;
        this.duration_time = duration_time;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public long getDeadLine_time() {
        return deadLine_time;
    }

    public void setDeadLine_time(int deadLine_time) {
        this.deadLine_time = deadLine_time;
    }

    public long getDuration_time() {
        return duration_time;
    }

    public void setDuration_time(int duration_time) {
        this.duration_time = duration_time;
    }

    @Override
    public int compareTo(Object o) {
        //--------------------------------------------------------
        // Summary: Compares two objects
        // Precondition: Object is cated to Task
        // Postcondition: Two objects compared to each other
        //-------------------------------------------------------
        Task task = (Task)o;
        if (this.task_name.equals(task.task_name) && this.deadLine_time == task.deadLine_time && this.duration_time == task.duration_time){
            return 0;
        }
        else{
            return this.task_name.compareTo(task.task_name);

        }
    }
}
