package org.spring.ConsoleUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.models.Team;
import org.spring.services.TeamService;
import org.spring.utils.InputValidator;

import java.util.List;
import java.util.Scanner;

public class TeamMenu {
    private static final Logger logger = LoggerFactory.getLogger(TeamMenu.class);
    private static Scanner scanner;
    private static TeamService teamService;

    public TeamMenu(TeamService teamService) {
        TeamMenu.teamService = teamService;
    }

    public static void showTeamMenu(Scanner scan) {
        scanner = scan;
        while (true) {
            logger.info("=== Team Management Menu ===");
            logger.info("1. Add New Team");
            logger.info("2. Update Team");
            logger.info("3. Delete Team");
            logger.info("4. Display All Teams");
            logger.info("5. Search Team");
            logger.info("6. Back to Main Menu");
            logger.info("Please select an option (1-6): ");

            int choice = InputValidator.validatePositiveInteger();
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
                    logger.warn("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewTeam() {
        logger.info("Enter Team Name: ");
        String name = scanner.nextLine();

        logger.info("Enter Team Ranking: ");
        int ranking = InputValidator.validatePositiveInteger();

        scanner.nextLine();

        Team team = new Team();
        team.setName(name);
        team.setRanking(ranking);

        boolean isAdded = teamService.addTeam(team);
        if (isAdded) {
            logger.info("Team successfully added.");
        } else {
            logger.error("Error adding team.");
        }
    }

    private static void updateTeam() {
        logger.info("Enter Team ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Team existingTeam = teamService.getTeamById(id);
        if (existingTeam == null) {
            logger.warn("Team not found.");
            return;
        }

        logger.info("Enter new Team Name (current: {}): ", existingTeam.getName());
        String name = scanner.nextLine();

        logger.info("Enter new Team Ranking (current: {}): ", existingTeam.getRanking());
        int ranking = InputValidator.validatePositiveInteger();
        scanner.nextLine();

        existingTeam.setName(name);
        existingTeam.setRanking(ranking);

        boolean isUpdated = teamService.updateTeam(existingTeam);
        if (isUpdated) {
            logger.info("Team successfully updated.");
        } else {
            logger.error("Error updating team.");
        }
    }

    private static void deleteTeam() {
        logger.info("Enter Team ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        boolean isDeleted = teamService.deleteTeam(id);
        if (isDeleted) {
            logger.info("Team successfully deleted.");
        } else {
            logger.error("Error deleting team.");
        }
    }

    private static void displayAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        if (teams.isEmpty()) {
            logger.info("No teams found.");
        } else {
            logger.info("=== All Teams ===");
            for (Team team : teams) {
                logger.info("{}", team);
            }
        }
    }

    private static void searchTeam() {
        logger.info("Enter Team ID to search: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Team team = teamService.getTeamById(id);
        if (team != null) {
            logger.info("Team found: {}", team);
        } else {
            logger.warn("Team not found.");
        }
    }
}
