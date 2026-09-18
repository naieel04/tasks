package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt"); //use relative path
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);
        printAllDataUsingStreams(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStreams(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStreams(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }
    private static int countDeadlinesUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Using Streams ...");
        int count = (int)tasks.stream()
                .filter(t->t instanceof Deadline)
                .count();
        return count;
    }



    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Using Iteration ...");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }
    public static void printAllDataUsingStreams(ArrayList<Task> tasks){
        System.out.println("Using Streams ...");
        //for every element in the stream, apply the method `System.out::println` aka print each element
        tasks.stream().forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Using Parallel Streams ...");
        tasks.parallelStream()
                .filter(t -> t instanceof Deadline)
                .forEach(System.out::println);


        System.out.println("Using Streams ...");
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .forEach(System.out::println);


    }


}
