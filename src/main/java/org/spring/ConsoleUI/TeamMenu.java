package org.spring.ConsoleUI;

import org.spring.models.Team;
import org.spring.services.TeamService;

import java.util.List;
import java.util.Scanner;

public class TeamMenu {
    private static Scanner scanner;
    private static TeamService teamService;

    public TeamMenu(TeamService teamService) {
        TeamMenu.teamService = teamService;
    }

    public static void showTeamMenu(Scanner scan) {
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

    private static void addNewTeam() {
        System.out.println("Enter Team Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Team Ranking: ");
        int ranking = scanner.nextInt();
        scanner.nextLine(); 

        Team team = new Team();
        team.setName(name);
        team.setRanking(ranking);

        boolean isAdded = teamService.addTeam(team);
        if (isAdded) {
            System.out.println("Team successfully added.");
        } else {
            System.out.println("Error adding team.");
        }
    }

    private static void updateTeam() {
        System.out.println("Enter Team ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); 

        Team existingTeam = teamService.getTeamById(id);
        if (existingTeam == null) {
            System.out.println("Team not found.");
            return;
        }

        System.out.println("Enter new Team Name (current: " + existingTeam.getName() + "): ");
        String name = scanner.nextLine();

        System.out.println("Enter new Team Ranking (current: " + existingTeam.getRanking() + "): ");
        int ranking = scanner.nextInt();
        scanner.nextLine(); 

        existingTeam.setName(name);
        existingTeam.setRanking(ranking);

        boolean isUpdated = teamService.updateTeam(existingTeam);
        if (isUpdated) {
            System.out.println("Team successfully updated.");
        } else {
            System.out.println("Error updating team.");
        }
    }


    private static void deleteTeam() {
        System.out.println("Enter Team ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); 

        boolean isDeleted = teamService.deleteTeam(id);
        if (isDeleted) {
            System.out.println("Team successfully deleted.");
        } else {
            System.out.println("Error deleting team.");
        }
    }

    private static void displayAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
        } else {
            System.out.println("=== All Teams ===");
            for (Team team : teams) {
                System.out.println(team);
            }
        }
    }

    private static void searchTeam() {
        System.out.println("Enter Team ID to search: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Team team = teamService.getTeamById(id);
        if (team != null) {
            System.out.println("Team found: " + team);
        } else {
            System.out.println("Team not found.");
        }
    }
}
