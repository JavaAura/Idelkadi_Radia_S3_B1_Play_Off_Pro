package org.spring.ConsoleUI;

import org.spring.models.Player;
import org.spring.services.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.utils.InputValidator;

import java.util.List;
import java.util.Scanner;

public class PlayerMenu {
    private static final Logger logger = LoggerFactory.getLogger(PlayerMenu.class);
    public static Scanner scanner;
    public static PlayerService playerService;

    public PlayerMenu(PlayerService pService) {
        playerService = pService;
    }

    public static void showPlayerMenu(Scanner scan) {
        scanner = scan;
        while (true) {
            logger.info("=== Player Management Menu ===");
            logger.info("1. Add New Player");
            logger.info("2. Update Player");
            logger.info("3. Delete Player");
            logger.info("4. Display All Players");
            logger.info("5. Search Player");
            logger.info("6. Back to Main Menu");
            logger.info("Please select an option (1-6): ");

            int choice = InputValidator.validatePositiveInteger();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewPlayer();
                    break;
                case 2:
                    updatePlayer();
                    break;
                case 3:
                    deletePlayer();
                    break;
                case 4:
                    displayAllPlayers();
                    break;
                case 5:
                    searchPlayer();
                    break;
                case 6:
                    return;
                default:
                    logger.warn("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewPlayer() {
        logger.info("Enter Player Pseudo: ");
        String pseudo = scanner.nextLine();

        logger.info("Enter Player Age: ");
        int age = InputValidator.validateAge();
        scanner.nextLine(); 

        Player player = new Player();
        player.setPseudo(pseudo);
        player.setAge(age);

        boolean isAdded = playerService.addPlayer(player);
        if (isAdded) {
            logger.info("Player successfully added.");
        } else {
            logger.error("Error adding player.");
        }
    }

    private static void updatePlayer() {
        logger.info("Enter Player ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); 

        Player existingPlayer = playerService.getPlayerById(id);
        if (existingPlayer == null) {
            logger.warn("Player not found.");
            return;
        }

        logger.info("Enter new Player Pseudo (current: {}): ", existingPlayer.getPseudo());
        String pseudo = scanner.nextLine();

        logger.info("Enter new Player Age (current: {}): ", existingPlayer.getAge());
        int age = scanner.nextInt();
        scanner.nextLine(); 

        existingPlayer.setPseudo(pseudo);
        existingPlayer.setAge(age);

        boolean isUpdated = playerService.updatePlayer(existingPlayer);
        if (isUpdated) {
            logger.info("Player successfully updated.");
        } else {
            logger.error("Error updating player.");
        }
    }

    private static void deletePlayer() {
        logger.info("Enter Player ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); 

        boolean isDeleted = playerService.deletePlayer(id);
        if (isDeleted) {
            logger.info("Player successfully deleted.");
        } else {
            logger.error("Error deleting player.");
        }
    }

    private static void displayAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        if (players.isEmpty()) {
            logger.info("No players found.");
        } else {
            logger.info("=== All Players ===");
            players.forEach(player -> logger.info(player.toString()));
        }
    }

    private static void searchPlayer() {
        logger.info("Enter Player ID to search: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); 

        Player player = playerService.getPlayerById(id);
        if (player != null) {
            logger.info("Player found: {}", player);
        } else {
            logger.warn("Player not found.");
        }
    }
}
