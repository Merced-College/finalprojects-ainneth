//Angie Alvarez
//Date: 4/24/2026
//Final Project: To-Do-List

import java.util.ArrayList;
import java.util.Scanner;
import java.io*;

public class TodoList {
    public static void main(String[] args) {
        private static final String FILE_NAME = "tasks.txt"; //GITHUB ADDED THIS
        Scanner scanner = new Scanner (System.in);
        ArrayList<String> tasks = new ArrayList<>();// added an array list that declares tasks
        int option;

        loadTasks(tasks);

        System.out.println( "To-Do List Menu");
        System.out.println( "1. View Tasks");
        System.out.println("2. Add a Task");
        System.out.println("3. Remove a Task");
        System.out.println("Choose an option: ");

        option = scanner.nextInt();
        scanner.nextLine();

        //Source: Java Switch Statements from W3Schools
        //Modified by: Angie Alvarez

        /* rather than using if else statements, the switch statement provides
         a easier view of when a choice is made */
        switch(option){
            case 1:
                if (tasks.isEmpty()){
                    System.out.println("There are no tasks available to view.");
                } else {
                System.out.println("Viewing tasks...");
                }
                break;
            case 2:
                System.out.println("Enter task: ");
                String task = scanner.nextLine();// GITHUB added this
                tasks.add(task);
                saveTasks(tasks);
                System.out.println( "Your task has been added!");
                break;
            case 3:
                if(tasks.isEmpty()) { 
                    System.out.println("There are no tasks to remove.");
                }else {
                 System.out.println("Remove task:");
                 int taskNumber = scanner.nextInt();
                 if(taskNumber > 0 && taskNumber <= tasks.size()) {         // couple errors fixed by GITHUB AI
                    tasks.remove(taskNumber -1);
                    saveTasks(tasks);
                    System.out.println("Tasks successfully removed!");
                 } else {
                    System.out.println("Uh-oh! Invalid task number. Please try again.");
                 }
                 }
                    break;
                }
                
        }

        //Method allows for tasks to be saved
        private static void saveTasks(ArrayList<String> tasks) {
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
        private static void loadTasks(ArrayList<String> tasks){
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                while (reader.ready()) {
                    String task = reader.readLine();
                    tasks.add(task);
                }
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());

            }
        }
        }



}// ToDo list class
