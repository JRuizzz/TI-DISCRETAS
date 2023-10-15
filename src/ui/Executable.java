package ui;
import java.util.Date;
import java.util.Scanner;
import model.Controller;

public class Executable {
    private Scanner scanner;
    private Controller controller;

    public Executable() {
        scanner = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {
        Executable executable = new Executable();
        executable.menu();
    }

    private void menu() {
        boolean flag = false;

        System.out.println("Welcome to Task Manager");
        while (!flag) {
            System.out.println("MENU");
            System.out.println("1. Manage Tasks");
            System.out.println("2. Finish Task");
            System.out.println("3. Undo Last Action");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("1. Register Task");
                    System.out.println("2. Modify Task");
                    System.out.println("3. Remove Task");
                    int option2 = scanner.nextInt();

                    switch (option2) {
                        case 1:
                            registerTask();
                            break;
                        case 2:
                            modifyTask();
                            break;
                        case 3:
                            removeTask();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1. Priority\n2. Deadline");
                    int finishTask = scanner.nextInt();
                    if (finishTask == 1) {
                        showTasksByPriority();
                    } else {
                        showTasksByDeadline();
                    }
                    break;
                case 3:
                    undoLastAction();
                    break;
            }
        }
    }

    public void registerTask() {
        String id = controller.randomID();

        System.out.println("Enter the title of your task");
        scanner.nextLine();
        String title = scanner.nextLine();

        System.out.println("Enter the description");
        String description = scanner.nextLine();

        System.out.println("Choose the priority of your task:\n1. HIGH PRIORITY\n2. MEDIUM PRIORITY\n3. LOW PRIORITY");
        int priority = scanner.nextInt();

        System.out.println("ENTER YOUR DEADLINE");
        System.out.println("Day");
        int day = scanner.nextInt();

        System.out.println("Month");
        int month = scanner.nextInt();

        System.out.println("Year");
        int year = scanner.nextInt();

        Date deadline = controller.constructDeadline(year, month, day);
        controller.addTask(id, title, description, priority, deadline);

        System.out.println("Task registered successfully!");
        System.out.println("YOUR TASK KEY IS **" + id + "**. DON'T LOSE IT");
    }

    public void modifyTask() {
        String query = controller.showTasks();
        if (query.isEmpty()) {
            System.out.println("You don't have any registered tasks.");
            return;
        } else {
            System.out.println("These are the tasks you have registered:");
            System.out.println(query);
        }

        scanner.nextLine();

        System.out.println("Enter the ID of the task you want to modify:");
        String id = scanner.nextLine();

        System.out.println("Choose the attribute you want to modify for your task:");
        System.out.println("1. Title\n2. Description\n3. Deadline\n4. Priority");
        int option = scanner.nextInt();

        scanner.nextLine();

        String msg = "";

        switch (option) {
            case 1:
                System.out.println("Enter the new title:");
                String newTitle = scanner.nextLine();
                msg = controller.modifyTask(id, option, newTitle, null, null, 0);
                break;
            case 2:
                System.out.println("Enter the new description:");
                String newDescription = scanner.nextLine();
                msg = controller.modifyTask(id, option, null, newDescription, null, 0);
                break;
            case 3:
                System.out.println("Enter the new day:");
                int newDay = scanner.nextInt();
                System.out.println("Enter the new month:");
                int newMonth = scanner.nextInt();
                System.out.println("Enter the new year:");
                int newYear = scanner.nextInt();
                scanner.nextLine();
                Date newDeadline = controller.constructDeadline(newYear, newMonth, newDay);
                msg = controller.modifyTask(id, option, null, null, newDeadline, 0);
                break;
            case 4:
                System.out.println("Choose the new priority:\n1. HIGH PRIORITY\n2. MEDIUM PRIORITY\n3. LOW PRIORITY");
                int newPriority = scanner.nextInt();
                scanner.nextLine();
                msg = controller.modifyTask(id, option, null, null, null, newPriority);
                break;
            default:
                System.out.println("Choose a valid option.");
                break;
        }

        System.out.println(msg);
    }

    public void removeTask() {
        String query = controller.showTasks();
        if (query.isEmpty()) {
            System.out.println("You don't have any registered tasks.");
            return;
        } else {
            System.out.println("These are the tasks you have registered:");
            System.out.println(query);
        }

        scanner.nextLine();
        System.out.println("Enter the ID of the task you want to delete:");
        String id = scanner.nextLine();
        System.out.println(controller.removeTask(id));
    }

    public void showTasksByPriority() {
        String query = controller.showTasksByPriority();
        System.out.println(query);
    }

    public void showTasksByDeadline() {
        String query = controller.showTasksByDeadline();
        System.out.println(query);
    }

    public void undoLastAction() {
        if (controller.undoAction()) {
            System.out.println("Undo action successful.");
        } else {
            System.out.println("No actions to undo.");
        }
    }
}
