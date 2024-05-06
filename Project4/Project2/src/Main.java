import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static ArrayList<Task> listOfTasks = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);





    public static int promptUser() {
        System.out.println("Please select one.\n");
        System.out.println("(1) Add a task.");
        System.out.println("(2) Remove a task.");
        System.out.println("(3) Update a task.");
        System.out.println("(4) List all tasks.");
        System.out.println("(5) Update priority of task");
        System.out.println("(0) Exit.");
        System.out.println("Which task would you like to do?");
        int task = input.nextInt();
        input.nextLine();

        return task;
    }

    public static Task addNewTask() {

        while(true) {
            try {
                System.out.println("What is the name of the task?");
                String name = input.nextLine();

                System.out.println("Give the task a description:");
                String description = input.nextLine();

                System.out.println("What is the priority of the task (0-5)?");
                int priority = input.nextInt();
                input.nextLine();

                Task addedTask = new Task(name, description, priority);
                listOfTasks.add(addedTask);
                Collections.sort(listOfTasks);


                return addedTask;
            } catch (NumberFormatException e) {
                System.out.println("Error!");
            }
        }
    }

    public static void removeTask() {

        System.out.println("What task would use like to remove? 1-" + listOfTasks.size());
        int task = input.nextInt()-1;
        input.nextLine();


        listOfTasks.remove(task);
        System.out.println(listOfTasks);


    }

    public static void updateTask() {

        System.out.println(listOfTasks);
        System.out.println("What task would use like to update? 1-" + listOfTasks.size());
        int updatedList = input.nextInt()-1;

        Task task2 = listOfTasks.get(updatedList);

        System.out.println("Please add a description:");
        String descriptionTask = input.nextLine();

        task2.setDescription(descriptionTask);
        listOfTasks.set(updatedList, task2);


    }

    public static ArrayList<Task> returnList() {
        try {
            System.out.println("1: Print entire list");
            System.out.println("2: Print list by priority");
            System.out.println("Which would you like?");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    for (int i = 0; i < listOfTasks.size(); i++) {
                        System.out.println("Index value: " + i + " | " + "Task Name: " + listOfTasks.get(i).getName() +
                                " Description: " + listOfTasks.get(i).getDescription() +
                                " Priority: " + listOfTasks.get(i).getPriority() + "\n");
                    }
                    break;

                case 2:
                    System.out.println("Which priority would you like to view? (0-5)");
                    int wantedPriority = input.nextInt();
                    input.nextLine();
                    boolean isPriorInList = false;
                    for(Task task: listOfTasks) {
                        if (task.getPriority() == wantedPriority) {
                            int i = listOfTasks.indexOf(task);
                            System.out.println("Index value: " + i + " | " + "Task Name: " + listOfTasks.get(i).getName() +
                                    " Description: " + listOfTasks.get(i).getDescription() +
                                    " Priority: " + listOfTasks.get(i).getPriority() + "\n");
                            isPriorInList = true;
                        }



                    }
                    break;


            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error!");
        }

        return listOfTasks;
    }

    public static void updatePriority() {
        System.out.println(listOfTasks);
        System.out.println("Which task priority would you like to update? (0-" + listOfTasks.size()+ ")");
        int selectedTask = input.nextInt();
        input.nextLine();
        Task updatePriority = listOfTasks.get(selectedTask);



        System.out.println("What would you like to change the priority to? (0-5)");
        int newPriority = input.nextInt();
        input.nextLine();
        updatePriority.setPriority(newPriority);




    }


    static void serialize() {
        Gson gson = new Gson();
        try(FileWriter fw = new FileWriter("data.json"))
        {
            gson.toJson(listOfTasks,fw);
            System.out.println();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void deserialize() {
        try(FileReader fr = new FileReader("data.json")) {
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(fr);
            Gson gson = new Gson();
            Type test = new TypeToken<ArrayList<Task>>(){}.getType();
            listOfTasks = gson.fromJson(element,test);
            System.out.println(listOfTasks);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        deserialize();
        int task = promptUser();
        while (true) {
            switch (task) {
                case 1:
                    addNewTask();
                    task = promptUser();
                    serialize();
                    break;
                case 2:
                    removeTask();
                    task = promptUser();
                    break;
                case 3:
                    updateTask();
                    task = promptUser();
                    break;
                case 4:
                    returnList();
                    task = promptUser();
                    break;
                case 5:
                    updatePriority();
                    task = promptUser();
                    break;
                case 0:
                    serialize();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please select a valid number");
                    promptUser();

                    break;


            }


        }
    }
}
