//Angie Alvarez
//Date: 4/24/2026
//Final Project: To-Do-List

import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        ArrayList<String> tasks = new ArrayList<>();// added an array list that declares tasks
        int option;

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
                break;
            case 3:
                System.out.println("Remove task:");
                break;
        }
        }



}// ToDo list class
