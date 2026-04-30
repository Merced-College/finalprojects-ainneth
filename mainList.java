//Angie Alvarez
//Date: 4/24/2026
//Final Project: To-Do-List Project 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Stack;
import java.util.Queue; 
import java.util.LinkedList;

class Task {  // representationof a single tasks within the to-do list
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
}//class task end

class TaskManager{ // class of TaskManager handles much of the logistics for the to-do list: adding, removing, etc
    private ArrayList<Task> tasks = new ArrayList<>(); // tasks stored from the arraylist
    private Stack<Task> undoStack = new Stack<>(); // undoes the previous removal
    private Queue<Task> taskQueue = new LinkedList<>(); // tasks are placed in order from which they are added


    
        public void addTask(String desc){ // as noted before, adds a task to list AND queue
            Task task = new Task(desc);
            tasks.add(task);                // section fixed from assistance of CHATPGT, since original
            taskQueue.add(task);            // problem was creating 2 different task ovjects 
        }
        public void removeTask(int index){  // removes a task from list AND includes it to the stack in case of wanting to undo the removal
            if(index >= 0 && index < tasks.size()) {         // tasknumber has been switched to index           GITHUB FIXED TASK REMOVAL ERROR
                    Task removed = tasks.remove(index);        //along with <= becoming just < and 
                   taskQueue.remove(removed); // tasks is removed from queue, and won't be processed
                    undoStack.push(removed);
                System.out.println( "\nTask removed successfully.\n");           
            } else {
                    System.out.println(); // space
                    System.out.println("Uh-oh! Invalid task number. Please try again.");
            }
        }

    /// undo remobe tasks method as wel asn the process of the nect task method are here
            public void undoRemove() { // undoes the removal from previous option, addint back to the list
                if (!undoStack.isEmpty()) {
                    Task restored = undoStack.pop(); 
                    tasks.add(restored); // asistance from CHATGPT, since task wasn't added back
                    taskQueue.add(restored); // task added back to queue
                    System.out.println(); // space
                    System.out.println("Task restored: " + restored);
                } else {
                    System.out.println(); // space
                    System.out.println( "Nothing to undo.");
                }
            }//end of undoRemove

        public void processNextTask() {
            if ( !taskQueue.isEmpty()) {
                Task next = taskQueue.poll(); // checks queue for the next task in order to be processed and display it
                System.out.println(); // space
                System.out.println( "Processing the next task: " + next);
            }else{ 
                System.out.println(); // space
                System.out.println("No tasks in the queue to process.");
            }
        }


        public void listTasks() {
            if (tasks.isEmpty()){ // when there are no tasks, the program lets the user know
                    System.out.println(); // space
                    System.out.println("There are no tasks available to view.");
                } else {
                    System.out.println(); // space
                    System.out.println("TASKS:");
                for (int i =0; i < tasks.size(); i++){ // tasks lists ordered with their corresponing number
                    System.out.println((i+1)+ ". " + tasks.get(i));
        }
        System.out.println();
    }
}

    public ArrayList<Task> getTasks() {
        return tasks; // gets the current list of tasks, allowing for FileHandler to access it
    }

    public void setTasks(ArrayList<Task> loadedTasks) {
        tasks = loadedTasks;
        taskQueue.clear(); // clear queue to make sure there's no duplication
        taskQueue.addAll(loadedTasks); // GITHUB added this to ensure that loaded tasks are also added to the queue for processing
    }
}// class taskManager

class FileHandler {
    private static final String FILE_NAME = "tasks.txt"; // file name, where final tasks cannot be changed yet accessed

    //Method allows for tasks to be saved
        // BufferedWriter allows for files to be created an d handles
        public static void save(ArrayList<Task> tasks) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {   // GUTHUB ADDED THIS
                for (Task task : tasks) {                                                 // PLUS SOURCE for understanding:
                    writer.write(task.getDescription());                          // W3schhols Java Switch || tasks per line
                    writer.newLine();                                           // the FileWrtiter connects file, while the BufferedWriter allows for writing 
                }
            } catch (IOException e) {
                System.out.println("Error saving tasks: " + e.getMessage());
            }
        }

        // Similar to previous method, yet now it's to load tasks from file
        public static ArrayList<Task> load(){
            ArrayList<Task> tasks = new ArrayList<>(); // will holf loaded tasks
            File file = new File(FILE_NAME);

            if (!file.exists()) return tasks; // checks if file exists
            
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
        int option =0;

        //removed of   loadTasks(tasks);
    do {
        System.out.println("===================");
        System.out.println( "To-Do List Menu");
        System.out.println("===================");
        System.out.println( "1. View Tasks");
        System.out.println("2. Add a Task");
        System.out.println("3. Remove a Task");
        System.out.println("4. Undo Removal");
        System.out.println("5. Continue with Next Task");
        System.out.println("6. Leave");
        System.out.print("Choose an option: ");

        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
        } else {
            System.out.println("Uh-oh. That's not right. Please enter a number listed in the menu!");
            scanner.nextLine();
            continue;
        }


        scanner.nextLine();

        //Source: Java Switch Statements from W3Schools
        //Modified by: Angie Alvarez

        /* rather than using if else statements, the switch statement provides
         a easier view of when a choice is made */
        switch(option) {  // handles the options for the menu from 1-6
            case 1: // view tasks 
                manager.listTasks();
                System.out.println(); // space
                break;
            case 2: // add a task
                System.out.println("Enter task: ");
                String task = scanner.nextLine();// GITHUB added this
                manager.addTask(task);
                FileHandler.save(manager.getTasks()); // GITHUB added this to save tasks after they are added using the file 
                 System.out.println(); // space
                break;
            case 3: // remove a task
                 System.out.print("Enter Task # to remove task: ");
                 int taskNumber = scanner.nextInt();
                 scanner.nextLine(); // Assistance from CHATPT to fix error after removing task
                 manager.removeTask(taskNumber-1);
                 FileHandler.save(manager.getTasks());
                 System.out.println(); // space
                 break;
            case 4: // undo the previos removal of a task
                manager.undoRemove();
                FileHandler.save(manager.getTasks()); //Github added this
                System.out.println(); // space
                break;

            case 5: // process the next task
                manager.processNextTask();
                FileHandler.save(manager.getTasks());
                System.out.println(); // space
                break;
                    
             case 6: //exit the program
                System.out.println("Bye!");
                System.out.println(); // space
                break;

                default:
                    System.out.println( "Invalid option. Please choose a listed number.");
            }
            } while (option !=6); //LOOP ADDED LETS GOO , in general keeps the program going until the user exits
        }
    }
           






public class mainList { // class where program starts, including loaded tasks from files along with bits frmo taskmanager and handler
    public static void main(String[] args){
        TaskManager manager = new TaskManager(); // manager created 

        manager.setTasks(FileHandler.load()); // tasks are loaded from the file, setting to manager
        
        MenuScreen menu = new MenuScreen(manager); // GITHUB FIXED THIS  |||| starts the menu with manager
        menu.start();
    }

    }//class mainList end
