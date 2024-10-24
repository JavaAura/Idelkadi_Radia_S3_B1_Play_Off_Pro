package org.spring.ConsoleUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.models.Player;
import org.spring.models.Team;
import org.spring.services.PlayerService;
import org.spring.services.TeamService;
import org.spring.utils.InputValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TeamMenu {
    private static final Logger logger = LoggerFactory.getLogger(TeamMenu.class);
    private static Scanner scanner;
    private static TeamService teamService;
    private static ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");


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
            logger.info("6. Add Player to Team");
            logger.info("7. Remove Player from Team");
            logger.info("8. Back to Main Menu");
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
                    addPlayerToTeam();
                    break;
                    case 7:
                        removePlayerFromTeam();
                        break;
                case 8:
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


    private static void addPlayerToTeam() {
        logger.info("Enter Team ID to add players: ");
        Long teamId = scanner.nextLong();
        scanner.nextLine();

        Team team = teamService.getTeamById(teamId);
        if (team == null) {
            logger.warn("Team not found.");
            return;
        }
        PlayerService playerService = (PlayerService) context.getBean("playerService");

        while (true) {
            logger.info("Enter Player ID to add to the team (or 0 to stop): ");
            Long playerId = scanner.nextLong();
            scanner.nextLine();

            if (playerId == 0) {
                break;
            }

            Player player = playerService.getPlayerById(playerId);
            if (player != null) {

                player.setTeam(team);
                playerService.updatePlayer(player);
                logger.info("Player {} added to team {}.", player.getPseudo(), team.getName());
            } else {
                logger.warn("Player with ID {} not found.", playerId);
            }
        }

        logger.info("All players have been added to the team.");
    }

    private static void removePlayerFromTeam() {
        logger.info("Enter Team ID to remove players from: ");
        Long teamId = scanner.nextLong();
        scanner.nextLine();

        Team team = teamService.getTeamById(teamId);
        if (team == null) {
            logger.warn("Team not found.");
            return;
        }

        PlayerService playerService = (PlayerService) context.getBean("playerService");

        List<Player> players = playerService.getAllPlayers().stream().filter(player -> player.getTeam() == team).collect(Collectors.toList());
        if (players.isEmpty()) {
            logger.warn("No players found in this team.");
            return;
        }

        logger.info("Players in team {}: ", team.getName());
        for (Player player : players) {
            logger.info("ID: {}, Pseudo: {}", player.getId(), player.getPseudo());
        }

        while (true) {
            logger.info("Enter Player ID to remove from the team (or 0 to stop): ");
            Long playerId = scanner.nextLong();
            scanner.nextLine();

            if (playerId == 0) {
                break;
            }

            Player player = playerService.getPlayerById(playerId);
            if (player != null && player.getTeam() != null && player.getTeam().getId().equals(teamId)) {
                player.setTeam(null);
                playerService.updatePlayer(player);
                logger.info("Player {} removed from team {}.", player.getPseudo(), team.getName());
            } else {
                logger.warn("Player with ID {} not found in this team.", playerId);
            }
        }

        logger.info("Players have been removed from the team.");
    }

}
