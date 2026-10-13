
import java.util.ArrayList;
import java.util.Scanner;
public class TaskTrack {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== TASKTRACK =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. View Summary");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Enter a valid number.");
                sc.next();
                System.out.print("Enter choice: ");
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> completeTask();
                case 4 -> deleteTask();
                case 5 -> viewSummary();
                case 0 -> System.out.println("Thanks for using TaskTrack!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        if (title.isBlank()) {
            System.out.println("Task title cannot be empty.");
            return;
        }

        tasks.add(new Task(nextId++, title, description));
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task task : tasks) {
            task.display();
        }
    }

    private static void completeTask() {
        Task task = findTask();

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        if (task.isCompleted()) {
            System.out.println("Task is already completed.");
        } else {
            task.complete();
            System.out.println("Task marked as completed!");
        }
    }

    private static void deleteTask() {
        Task task = findTask();

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        tasks.remove(task);
        System.out.println("Task deleted successfully!");
    }

    private static Task findTask() {
        System.out.print("Enter task ID: ");

        if (!sc.hasNextInt()) {
            sc.nextLine();
            return null;
        }

        int id = sc.nextInt();
        sc.nextLine();

        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    private static void viewSummary() {
        int completed = 0;

        for (Task task : tasks) {
            if (task.isCompleted()) {
                completed++;
            }
        }

        System.out.println("\n===== TASK SUMMARY =====");
        System.out.println("Total tasks: " + tasks.size());
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + (tasks.size() - completed));
    }
}

