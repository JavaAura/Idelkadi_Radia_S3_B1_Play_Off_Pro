package org.spring.ConsoleUI;

import org.spring.models.Game;
import org.spring.services.GameService;
import org.spring.models.enums.GameDifficulty;

import java.util.List;
import java.util.Scanner;

public class GameMenu {
    public static Scanner scanner;
    public static GameService gameService;

    public GameMenu(GameService gService) {
        gameService = gService;
    }

    public static void showGameMenu(Scanner scan) {
        scanner = scan;
        while (true) {
            System.out.println("=== Game Management Menu ===");
            System.out.println("1. Add New Game");
            System.out.println("2. Update Game");
            System.out.println("3. Delete Game");
            System.out.println("4. Display All Games");
            System.out.println("5. Search Game");
            System.out.println("6. Back to Main Menu");
            System.out.print("Please select an option (1-6): ");

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
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewGame() {
        System.out.println("Enter Game Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter Average Match Duration: ");
        double avgMatchDuration = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("Select Game Difficulty (EASY, MEDIUM, HARD , EXPERT): ");
        GameDifficulty difficulty = GameDifficulty.valueOf(scanner.nextLine().toUpperCase());

        Game game = new Game();
        game.setName(name);
        game.setAvgMatchDuration(avgMatchDuration);
        game.setDifficulty(difficulty);

        boolean isAdded = gameService.addGame(game);
        if (isAdded) {
            System.out.println("Game successfully added.");
        } else {
            System.out.println("Error adding game.");
        }
    }

    private static void updateGame() {
        System.out.println("Enter Game ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); 

        Game existingGame = gameService.getGameById(id);
        if (existingGame == null) {
            System.out.println("Game not found.");
            return;
        }

        System.out.println("Enter new Game Name (current: " + existingGame.getName() + "): ");
        String name = scanner.nextLine();

        System.out.println("Enter new Average Match Duration (current: " + existingGame.getAvgMatchDuration() + "): ");
        double avgMatchDuration = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("Select new Game Difficulty (current: " + existingGame.getDifficulty() + "): ");
        GameDifficulty difficulty = GameDifficulty.valueOf(scanner.nextLine().toUpperCase());

        existingGame.setName(name);
        existingGame.setAvgMatchDuration(avgMatchDuration);
        existingGame.setDifficulty(difficulty);

        boolean isUpdated = gameService.updateGame(existingGame);
        if (isUpdated) {
            System.out.println("Game successfully updated.");
        } else {
            System.out.println("Error updating game.");
        }
    }

    private static void deleteGame() {
        System.out.println("Enter Game ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); 

        boolean isDeleted = gameService.deleteGame(id);
        if (isDeleted) {
            System.out.println("Game successfully deleted.");
        } else {
            System.out.println("Error deleting game.");
        }
    }

    private static void displayAllGames() {
        List<Game> games = gameService.getAllGames();
        if (games.isEmpty()) {
            System.out.println("No games found.");
        } else {
            System.out.println("=== All Games ===");
            games.forEach(System.out::println);
        }
    }

    private static void searchGame() {
        System.out.println("Enter Game ID to search: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); 

        Game game = gameService.getGameById(id);
        if (game != null) {
            System.out.println("Game found: " + game);
        } else {
            System.out.println("Game not found.");
        }
    }
}
