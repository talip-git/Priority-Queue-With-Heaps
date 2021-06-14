import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;
//-----------------------------------------------------
// Title: Main class
// Description: main class that we read info from txt files
//-----------------------------------------------------
public class Main {

    public static void main(String[] args) {
        MyPriorityQueue<Task> task_list =  new MyPriorityQueue<>(); // priority queue

        long time = 0;
        //Try with resources for reading the text
        try (Scanner sc = new Scanner(new FileReader("C:\\Users\\sinop\\IdeaProjects\\Hmw4\\src\\sampleinput1.txt"))) {
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                //Splits the array into parts
                String [] parts  = line.split(" ");

                // add the schedules to the queue
                if (parts[0].equals("schedule")){
                    Task task =  new Task(parts[1],Long.parseLong(parts[2]),Long.parseLong(parts[3]));
                    task_list.add(task);
                    //Print the add operation
                    System.out.printf("%d: adding %s with deadline %d and duration %d\n",time,task.getTask_name(),task.getDeadLine_time(),task.getDuration_time());
                }
                //if the command is run
                if (parts[0].equals("run")){
                    long start_time = System.currentTimeMillis();

                    Iterator it = task_list.iterator();//Iterator for iterating in queue
                    Task task = task_list.delMax(); //Delete max

                    //Iterate through the list
                    while (it.hasNext() && task !=null){
                        System.out.printf("%d: Busy %s with deadline %d and duration %d\n",time,task.getTask_name(),task.getDeadLine_time(),task.getDuration_time());

                        time+=task.getDuration_time();//Update the time in every task
                        if (time>Integer.parseInt(parts[1])){
                            long deadline_time = task.getDeadLine_time();
                            long duration_time = time - task.getDuration_time();
                            Task addnew =  new Task(task.getTask_name(),deadline_time,duration_time);
                            System.out.printf("%d: adding %s with deadline %d and duration %d\n",Integer.parseInt(parts[1]),addnew.getTask_name(),addnew.getDeadLine_time(),addnew.getDuration_time());
                            task_list.add(addnew);
                            return;
                        }
                        //Print done operation
                        System.out.printf("%d: Done with: %s \n",time,task.getTask_name());
                        task = task_list.delMax();
                    }

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
