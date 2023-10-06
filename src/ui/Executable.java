package ui;
import java.util.Date;
import java.util.Scanner;
import model.Controller;
public class Executable {

	private Scanner lector;
	private Controller controller;

	public Executable() {

	    lector = new Scanner(System.in);
		controller = new Controller();
	}

	public static void main(String[] args) {

		Executable ejecutable = new Executable();
		ejecutable.menu();

	}

	private void menu() {

		boolean flag = false;

        System.out.println("Bienvenido a Task");
		while (!flag) {
        
            System.out.println("MENU");
            System.out.println("1.Gestionar tareas");
            System.out.println("2.Gestion de prioridad");
            System.out.println("3.Imprimir tabla");
            System.out.println("4.Mostrar por importancia");
		    int option = lector.nextInt();
		
            switch (option) {

                case 1:
                    System.out.println("1.Registrar tarea");
                    System.out.println("2.Modificar tarea");
                    System.out.println("3.Eliminar tarea");
                    int option2 = lector.nextInt();

                    switch(option2){
                        case 1:
                            registerTk();
                        break;
                        case 2:
                            modifyTk();
                        break;
                        case 3:
                            removeTk();
                        break;
                        
                    }
                break;
                case 2:
                break;
                case 3:
                    imprimirTabla();
                break;

                case 4: 
                showTasksByPriority();
                break; 
            }
        
        }
        
    }

    public void registerTk() {
        
        String id = controller.randomID();
    
        System.out.println("Type the title of your task");
        lector.nextLine();
        String title = lector.nextLine();
    
        System.out.println("Type the description");
        String description = lector.nextLine();
    
        System.out.println("Choose the priority of your task:\n1. HIGH PRIORITY\n2. MEDIUM PRIORITY\n3. LOW PRIORITY");
        int priority = lector.nextInt();
    
        System.out.println("TYPE YOUR DEADLINE");
        System.out.println("Day");
        int day = lector.nextInt();
    
        System.out.println("Month");
        int month = lector.nextInt();
    
        System.out.println("Year");
        int year = lector.nextInt();

        Date deadline = controller.constructDeadline(year, month, day);
        controller.addTask(id,title,description,priority,deadline);


        System.out.println("¡Task registered succesfully!");
        System.out.println("YOUR TASK KEY WILL BE **" + id + "** DON'T LOSE IT");

    }
    

    public void modifyTk() {
        String query = controller.showTask();
        if (query.isEmpty()) {
            System.out.println("You don't have registered tasks");
            return;
        } else {
            System.out.println("These are the tasks you have registered");
            System.out.println(query);
        }
        
        lector.nextLine();

        System.out.println("Type the id of the task you want to modify");
        String id = lector.nextLine();
    
        System.out.println("Choose the attribute you want to modify for your task");
        System.out.println("1.Tittle \n2.Description \n3.Deadline \4.Priority");
        int option = lector.nextInt();
    
        lector.nextLine();
    
        String msg = ""; 
    
        switch (option) {
            case 1:
                System.out.println("Type the new title");
                String newTitle = lector.nextLine();
                msg = controller.modifyTask(id, option, newTitle, null, null, 0);
                break;
            case 2:
                System.out.println("Type the new description");
                String newDescription = lector.nextLine();
                msg = controller.modifyTask(id, option, null, newDescription, null, 0);
                break;
            case 3:
                System.out.println("Type the new day");
                int newDay = lector.nextInt();
                System.out.println("Type the new month");
                int newMonth = lector.nextInt();
                System.out.println("Type the new year");
                int newYear = lector.nextInt();
                lector.nextLine();
                Date newDeadline = controller.constructDeadline(newYear, newMonth, newDay);
                msg = controller.modifyTask(id, option, null, null, newDeadline, 0);
                break;
            case 4:
                System.out.println("Choose the new priority \n1.HIGH PRIORITY \n2.LOW PRIORITY");
                int newPriority = lector.nextInt();
                lector.nextLine();
                msg = controller.modifyTask(id, option, null, null, null, newPriority);
                break;
            default:
                System.out.println("Choose a valid option");
                break;
        }
    
        System.out.println(msg);
    }
    
    public void removeTk(){

        String query = controller.showTask();
        if (query.isEmpty()) {
            System.out.println("You don't have registered tasks");
            return;
        } else {
            System.out.println("These are the tasks you have registered");
            System.out.println(query);
        }

        lector.nextLine();
        System.out.println("Type the id of the task you want to delete");
        String id = lector.nextLine();

        System.out.println(controller.removeTask(id));
    }

    public void imprimirTabla(){
        System.out.println(controller.showTask());
    }

    public void showTasksByPriority(){
        String query = controller.showTasksByPriority();
        System.out.println(query);
    }


    public void DequeuePriority(){
        
    }
}
