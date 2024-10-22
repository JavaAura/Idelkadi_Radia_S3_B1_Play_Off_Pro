package org.spring.ConsoleUI;

import java.util.Scanner;

public class TeamMenu {
    public static Scanner scanner;

    public static void showTeamMenu(Scanner scan){
        scanner = scan;
        while (true) {
            System.out.println("=== Team Management Menu ===");
            System.out.println("1. Add New Team");
            System.out.println("2. Update Team");
            System.out.println("3. Delete Team");
            System.out.println("4. Display All Teams");
            System.out.println("5. Search Team");
            System.out.println("6. Back to Main Menu");
            System.out.print("Please select an option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    addNewTeam();
                    break;
                case 2:
                    updateTeam();
                    break;
                case 3:
                    deleteTeam();
                    break;
                case 4:
                    displayAllTeams();
                    break;
                case 5:
                    searchTeam();
                    break;
                case 6:
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void searchTeam() {
    }

    private static void displayAllTeams() {
    }

    private static void deleteTeam() {
    }

    private static void updateTeam() {
    }

    private static void addNewTeam() {

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter team ranking: ");
        int ranking = scanner.nextInt();


    }
}
