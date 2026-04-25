//Angie Alvarez
//Date: 4/24/2026
//Final Project: To-Do-List

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Stack;
import java.util.Queue; // addings for future use if needed
import java.util.LinkedList;

class Task {
    private String description;

    public Task( String description) {
    this.description = description;
}

    public String getDescription() {
    return description;
}
    @Override
     public String toString() {
         return description;
    }
}

class TaskManager{
    private ArrayList<Task> tasks = new ArrayList<>();
    private Stack<Task> undoStack = new Stack<>();
    private Queue<Task> taskQueue = new LinkedList<>();


    
        public void addTask(String desc){
            tasks.add(new Task(desc));
            taskQueue.add(new Task(desc));
        }
        public void removeTask(int index){
            if(index >= 0 && index < tasks.size()) {         // tasknumber has been switched to index           GITHUB FIXED TASK REMOVAL ERROR
                    Task removed = tasks.remove(index);        //along with <= becoming just < and 
                    undoStack.push(removed);
                System.out.println( "Task removed successfully.");           
            } else {
                    System.out.println("Uh-oh! Invalid task number. Please try again.");
            }
        }

    /// undo remobe tasks method as wel asn the process of the nect task method are here
            public void undoRemove() {
                if (!undoStack.isEmpty()) {
                    Task restored = undoStack.pop();
                    System.out.println("Task restored: " + restored);
                } else {
                    System.out.println( "Nothing to undo.");
                }
            }//end of undoRemove

        public void processNextTask() {
            if ( !taskQueue.isEmpty()) {
                Task next = taskQueue.poll();
                System.out.println( "Processing the next task: " + next);
            }else{ 
                System.out.println("No tasks in the queue to process.");
            }
        }


        public void listTasks() {
            if (tasks.isEmpty()){
                    System.out.println("There are no tasks available to view.");
                } else {
                for (int i =0; i < tasks.size(); i++){
                    System.out.println((i+1)+ ". " + tasks.get(i));
        }
    }
}

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> loadedTasks) {
        tasks = loadedTasks;
        taskQueue.addAll(loadedTasks); // GITHUB added this to ensure that loaded tasks are also added to the queue for processing
    }
}// class taskManager

class FileHandler {
    private static final String FILE_NAME = "tasks.txt";

    //Method allows for tasks to be saved
        // BufferedWriter allows for files to be created an d handles
        public static void save(ArrayList<Task> tasks) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {   // GUTHUB ADDED THIS
                for (Task task : tasks) {                                                 // PLUS SOURCE for understanding:
                    writer.write(task.getDescription());                                                         // W3schhols Java Switch
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error saving tasks: " + e.getMessage());
            }
        }

        // Similar to previous method, yet now it's to load tasks from file
        public static ArrayList<Task> load(){
            ArrayList<Task> tasks = new ArrayList<>();
            File file = new File(FILE_NAME);

            if (!file.exists()) return tasks;
            
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {  //GITHUB FIX AND ADDED THIS SECTION
                    tasks.add(new Task(line));
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }

            return tasks;
        }
    }// class FileHandler


class MenuScreen {
    private Scanner scanner = new Scanner (System.in);
    private TaskManager manager; //Removed the == new TaskManager();

    public MenuScreen( TaskManager manager) {
        this.manager = manager;
    }
    public void start() {
        int option;

        //removed of   loadTasks(tasks);

        System.out.println( "To-Do List Menu");
        System.out.println( "1. View Tasks");
        System.out.println("2. Add a Task");
        System.out.println("3. Remove a Task");
        System.out.println("4. Undo Removal");
        System.out.println("5. Continue with Next Task");
        System.out.println("6. Leave");
        System.out.println("Choose an option: ");

        option = scanner.nextInt();
        scanner.nextLine();

        //Source: Java Switch Statements from W3Schools
        //Modified by: Angie Alvarez

        /* rather than using if else statements, the switch statement provides
         a easier view of when a choice is made */
        switch(option){
            case 1:
                manager.listTasks();
                break;
            case 2:
                System.out.println("Enter task: ");
                String task = scanner.nextLine();// GITHUB added this
                manager.addTask(task);
                FileHandler.save(manager.getTasks()); // GITHUB added this to save tasks after they are added using the file 
                break;
            case 3:
                 System.out.println("Enter Task # to remove task:");
                 int taskNumber = scanner.nextInt();
                 manager.removeTask(taskNumber-1);
                 FileHandler.save(manager.getTasks());
                    break;

            case 4:
                manager.undoRemove();
                FileHandler.save(manager.getTasks()); //Github added this
                break;

            case 5:
                manager.processNextTask();
                FuleHandler.save(manager.getTasks());
                    
             case 6:
                System.out.println("Bye!");
                break;
                }

                } while (option !=6); //LOOP ADDED LETS GOO
            }






public class mainList {
    public static void main(String[] args){
        TaskManager manager = new TaskManager();

        manager.setTasks(FileHandler.load());
        
        MenuScreen menu = new MenuScreen(manager); // GITHUB FIXED THIS
        menu.start();
    }



    }