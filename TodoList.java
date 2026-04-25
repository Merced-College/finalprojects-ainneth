//Angie Alvarez
//Date: 4/24/2026
//Final Project: To-Do-List

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
        public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        ArrayList<String> tasks = new ArrayList<>();// added an array list that declares tasks
        int option;

        loadTasks(tasks);

        System.out.println( "To-Do List Menu");
        System.out.println( "1. View Tasks");
        System.out.println("2. Add a Task");
        System.out.println("3. Remove a Task");
        System.out.println("4. Leave");
        System.out.println("Choose an option: ");

        option = scanner.nextInt();
        scanner.nextLine();

        //Source: Java Switch Statements from W3Schools
        //Modified by: Angie Alvarez

        /* rather than using if else statements, the switch statement provides
         a easier view of when a choice is made */
        switch(option){
            case 1:
                * listtask
                break;
            case 2:
                System.out.println("Enter task: ");
                String task = scanner.nextLine();// GITHUB added this
                maager
                handler
                break;
            case 3:
                if(tasks.isEmpty()) { 
                    System.out.println("There are no tasks to remove.");
                }else {
                 System.out.println("Remove task:");
                 int taskNumber = scanner.nextInt();
                    System.out.println("Tasks successfully removed!");
                 } else {
                    System.out.println("Uh-oh! Invalid task number. Please try again.");
                     }
                 }
                    break;
                    
                    case 4:
                        System.out.println("Bye!");
                    break;
                }

                    scanner.close();
                }
                

        
        }
        }// ToDo list class

class TaskManager{
    private ArrayList<Task> tasks = new ArrayList<>();
        public void addTask(String desc){}
            tasks.add(task);
}
        public void removeTask(int index){}
            if(index > 0 && index <= tasks.size()) {         // tasknumber has been switched to index
                    tasks.remove(index -1);
            } else {
                    System.out.println("Uh-oh! Invalid task number. Please try again.");
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

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}

class FileHandler {
    private static final String FILE_NAME = "tasks.txt";

    //Method allows for tasks to be saved
        // BufferedWriter allows for files to be created an d handles
        public static void saveTasks(ArrayList<String> tasks) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {   // GUTHUB ADDED THIS
                for (String task : tasks) {                                                 // PLUS SOURCE for understanding:
                    writer.write(task);                                                         // W3schhols Java Switch
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error saving tasks: " + e.getMessage());
            }
        }

        // Similar to previous method, yet now it's to load tasks from file
        public static void loadTasks(ArrayList<String> tasks){
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                while (reader.ready()) {
                    String task = reader.readLine();
                    tasks.add(task);
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());

            }
}


public class mainList {
    public static void main(String[] args){
        TaskManager manager = new TaskManager();

    }