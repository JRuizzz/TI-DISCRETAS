package ui;
import java.util.Scanner;
import model.TaskReminderController;

public class Main {

	private Scanner sc;
	private TaskReminderController controller;

	public Main() {

		sc = new Scanner(System.in);
		controller = new TaskReminderController();
	 }
	public static void main(String[] args) {

		Main ejecutable = new Main();
		ejecutable.menu();

	}

	private void menu() {

		boolean flag = false;

		while (!flag) {


        System.out.println("▓█████▄ ▓█████   ██████ ▓█████▄     ▄████████ ████████▄     ▄████████    ▄████████     ▄████████");
        System.out.println("▒██▀ ██▌▓█   ▀ ▒██    ▒ ▒██▀ ██▌   ███    ███ ██▀     ▄█   ███    ███    ███    ███    ███    ███");
        System.out.println("░██   █▌▒███   ░ ▓██▄   ░██   █▌   ███    █▀  ██        ██   ███    ███    ███    █▀     ███    █▀");
        System.out.println("░▓█▄   ▌▒▓█  ▄   ▒   ██▒░▓█▄   ▌  ▄███▄▄▄     ████▄▄▄██▀   ███    ███   ▄███▄▄▄        ███   ▄██");
        System.out.println("                                TASK AND REMINDER MANAGMENT SYSTEM");
        System.out.println("░▒████▓ ░▒████▒▒██████▒▒░▒████▓  ▀▀███▀▀▀     ████▀██▄   ███    ███  ▀▀███▀▀▀        ███    ███");
        System.out.println(" ▒▒▓  ▒ ░░ ▒░ ░▒ ▒▓▒ ▒ ░ ▒▒▓  ▒   ███    █▄  ██ ██▄▄▄██   ███    ███    ███    █▄     ███    ███");
        System.out.println(" ░ ▒  ▒  ░ ░  ░░ ░▒  ░ ░ ░ ▒  ▒   ███    ███ ██ ██    ███  ███    ███    ███    ███    ███    ███");
        System.out.println(" ░ ░  ░    ░   ░  ░  ░   ░ ░  ░   ██████████ ▀▀ ███    █▀   ▀██████▀     ██████████     ████████▀ ");
        System.out.println("   ░       ░  ░      ░       ░      ░░░░░░░░░     ▀");
 

		


        System.out.println("+-------------------------------------+");
        System.out.println("|      MAIN MENU                      |");
        System.out.println("+-------------------------------------+");
        System.out.println("| 1. TASKS & REMINDERS               |");
        System.out.println("| 2. PRIORITY MANAGEMENT             |");
        System.out.println("| 3.                                 |");
        System.out.println("| 4.                                 |");
        System.out.println("| 5. EXIT                            |");
        System.out.println("+-------------------------------------+");
        
		
		int option = sc.nextInt();
		
            switch (option) {
                case 1:
                System.out.println("\n+-------------------------------------+");
                System.out.println("|      TASKS & REMINDERS MENU        |");
                System.out.println("+-------------------------------------+");
                System.out.println("| 1. REGISTER TASK/REMINDER          |");
                System.out.println("| 2. MODIFY TASK/REMINDER            |");
                System.out.println("| 3. DELETE TASK/REMINDER            |");
                System.out.println("+-------------------------------------+");
                
                int option1 = sc.nextInt();

                switch(option1){
                    case 1: 
                    registerT_R();
                    break; 

                    case 2:

                    break; 

                    case 3: 
                    break;
                }

                break; 
            
                case 2:
                break; 
            
                case 3:
                break; 
                
            
                case 4:
                break; 
                
                case 5:
                break; 
                
                default:
                    System.out.println("INVALID OPTION");
                break;
            }
        } 
    }

    public void registerT_R(){
        String id = controller.randomID();

        System.out.println("TYPE THE TITTLE OF YOUR TASK/REMAINDER");
        String title = sc.nextLine(); 

        sc.nextLine();

        System.out.println("TYPE THE DESCRIPTION");
        String description = sc.nextLine();

        System.out.println("TYPE: \n1.HIGH PRIORITY \n2.LOW PRIORITY ");
        int priority = sc.nextInt();
        
        System.out.println("TYPE YOUR DEADLINE");
        System.out.println("DAY");
        int publicationDay = sc.nextInt();
    
        System.out.println("MONTH");
        int publicationMonth = sc.nextInt();
    
        System.out.println("YEAR");
        int publicationYear = sc.nextInt();
        

        
        if(controller.registerT_R(id, title, description, priority, publicationDay, publicationMonth, publicationYear)){
            System.out.println("SUCCESS REGISTRATION");
            System.out.println("YOUR TASK KEY WILL BE " + id + " DONT LOSE IT ");


        } else {
            System.out.println("¡ERROR!: INVALID VALUES");
        }
    }


    public void modify(){

    }

    public void remove(){

    }
}
