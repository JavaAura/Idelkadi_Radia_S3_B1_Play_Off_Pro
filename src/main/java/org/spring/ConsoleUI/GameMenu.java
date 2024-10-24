package org.spring.ConsoleUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.models.Game;
import org.spring.services.GameService;
import org.spring.models.enums.GameDifficulty;

import java.util.List;
import java.util.Scanner;

public class GameMenu {
    public static Scanner scanner;
    public static GameService gameService;
    private static final Logger logger = LoggerFactory.getLogger(GameMenu.class);

    public GameMenu(GameService gService) {
        gameService = gService;
    }

    public static void showGameMenu(Scanner scan) {
        scanner = scan;
        while (true) {
            logger.info("=== Game Management Menu ===");
            logger.info("1. Add New Game");
            logger.info("2. Update Game");
            logger.info("3. Delete Game");
            logger.info("4. Display All Games");
            logger.info("5. Search Game");
            logger.info("6. Back to Main Menu");
            logger.info("Please select an option (1-6): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewGame();
                    break;
                case 2:
                    updateGame();
                    break;
                case 3:
                    deleteGame();
                    break;
                case 4:
                    displayAllGames();
                    break;
                case 5:
                    searchGame();
                    break;
                case 6:
                    return;
                default:
                    logger.warn("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewGame() {
        logger.info("Enter Game Name: ");
        String name = scanner.nextLine();

        logger.info("Enter Average Match Duration: ");
        double avgMatchDuration = scanner.nextDouble();
        scanner.nextLine();

        logger.info("Select Game Difficulty (EASY, MEDIUM, HARD, EXPERT): ");
        GameDifficulty difficulty = GameDifficulty.valueOf(scanner.nextLine().toUpperCase());

        Game game = new Game();
        game.setName(name);
        game.setAvgMatchDuration(avgMatchDuration);
        game.setDifficulty(difficulty);

        boolean isAdded = gameService.addGame(game);
        if (isAdded) {
            logger.info("Game successfully added.");
        } else {
            logger.error("Error adding game.");
        }
    }

    private static void updateGame() {
        logger.info("Enter Game ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Game existingGame = gameService.getGameById(id);
        if (existingGame == null) {
            logger.warn("Game not found.");
            return;
        }

        logger.info("Enter new Game Name (current: {}): ", existingGame.getName());
        String name = scanner.nextLine();

        logger.info("Enter new Average Match Duration (current: {}): ", existingGame.getAvgMatchDuration());
        double avgMatchDuration = scanner.nextDouble();
        scanner.nextLine();

        logger.info("Select new Game Difficulty (current: {}): ", existingGame.getDifficulty());
        GameDifficulty difficulty = GameDifficulty.valueOf(scanner.nextLine().toUpperCase());

        existingGame.setName(name);
        existingGame.setAvgMatchDuration(avgMatchDuration);
        existingGame.setDifficulty(difficulty);

        boolean isUpdated = gameService.updateGame(existingGame);
        if (isUpdated) {
            logger.info("Game successfully updated.");
        } else {
            logger.error("Error updating game.");
        }
    }

    private static void deleteGame() {
        logger.info("Enter Game ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        boolean isDeleted = gameService.deleteGame(id);
        if (isDeleted) {
            logger.info("Game successfully deleted.");
        } else {
            logger.error("Error deleting game.");
        }
    }

    private static void displayAllGames() {
        List<Game> games = gameService.getAllGames();
        if (games.isEmpty()) {
            logger.info("No games found.");
        } else {
            logger.info("=== All Games ===");
            games.forEach(game -> logger.info(game.toString())); // Utilisation de logger pour afficher chaque jeu
        }
    }

    private static void searchGame() {
        logger.info("Enter Game ID to search: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Game game = gameService.getGameById(id);
        if (game != null) {
            logger.info("Game found: {}", game);
        } else {
            logger.warn("Game not found.");
        }
    }
}
