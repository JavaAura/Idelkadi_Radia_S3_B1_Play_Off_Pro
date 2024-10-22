package org.spring.ConsoleUI;

import org.spring.models.Player;
import org.spring.services.PlayerService;

import java.util.List;
import java.util.Scanner;
public class PlayerMenu {
//    public static Scanner scanner;
////    public static PlayerService playerService;
////
////    public PlayerMenu(PlayerService pService) {
////        playerService = pService;
////    }
////
////    public static void showPlayerMenu(Scanner scan) {
////        scanner = scan;
////        while (true) {
////            System.out.println("=== Player Management Menu ===");
////            System.out.println("1. Add New Player");
////            System.out.println("2. Update Player");
////            System.out.println("3. Delete Player");
////            System.out.println("4. Display All Players");
////            System.out.println("5. Search Player");
////            System.out.println("6. Back to Main Menu");
////            System.out.print("Please select an option (1-6): ");
////
////            int choice = scanner.nextInt();
////            scanner.nextLine();
////
////            switch (choice) {
////                case 1:
////                    addNewPlayer();
////                    break;
////                case 2:
////                    updatePlayer();
////                    break;
////                case 3:
////                    deletePlayer();
////                    break;
////                case 4:
////                    displayAllPlayers();
////                    break;
////                case 5:
////                    searchPlayer();
////                    break;
////                case 6:
////                    return;
////                default:
////                    System.out.println("Invalid choice. Please try again.");
////            }
////        }
////    }
////
////    private static void addNewPlayer() {
////        System.out.println("Enter Player Pseudo: ");
////        String pseudo = scanner.nextLine();
////
////        System.out.println("Enter Player Age: ");
////        int age = scanner.nextInt();
////        scanner.nextLine(); // Consume newline character
////
////        Player player = new Player();
////        player.setPseudo(pseudo);
////        player.setAge(age);
////
////        boolean isAdded = playerService.addPlayer(player);
////        if (isAdded) {
////            System.out.println("Player successfully added.");
////        } else {
////            System.out.println("Error adding player.");
////        }
////    }
////
////    private static void updatePlayer() {
////        System.out.println("Enter Player ID to update: ");
////        Long id = scanner.nextLong();
////        scanner.nextLine(); // Consume newline character
////
////        Player existingPlayer = playerService.getPlayerById(id);
////        if (existingPlayer == null) {
////            System.out.println("Player not found.");
////            return;
////        }
////
////        System.out.println("Enter new Player Pseudo (current: " + existingPlayer.getPseudo() + "): ");
////        String pseudo = scanner.nextLine();
////
////        System.out.println("Enter new Player Age (current: " + existingPlayer.getAge() + "): ");
////        int age = scanner.nextInt();
////        scanner.nextLine(); // Consume newline character
////
////        existingPlayer.setPseudo(pseudo);
////        existingPlayer.setAge(age);
////
////        boolean isUpdated = playerService.updatePlayer(existingPlayer);
////        if (isUpdated) {
////            System.out.println("Player successfully updated.");
////        } else {
////            System.out.println("Error updating player.");
////        }
////    }
////
////    private static void deletePlayer() {
////        System.out.println("Enter Player ID to delete: ");
////        Long id = scanner.nextLong();
////        scanner.nextLine(); // Consume newline character
////
////        boolean isDeleted = playerService.deletePlayer(id);
////        if (isDeleted) {
////            System.out.println("Player successfully deleted.");
////        } else {
////            System.out.println("Error deleting player.");
////        }
////    }
////
////    private static void displayAllPlayers() {
////        List<Player> players = playerService.getAllPlayers();
////        if (players.isEmpty()) {
////            System.out.println("No players found.");
////        } else {
////            System.out.println("=== All Players ===");
////            players.forEach(System.out::println);
////        }
////    }
////
////    private static void searchPlayer() {
////        System.out.println("Enter Player ID to search: ");
////        Long id = scanner.nextLong();
////        scanner.nextLine(); // Consume newline character
////
////        Player player = playerService.getPlayerById(id);
////        if (player != null) {
////            System.out.println("Player found: " + player);
////        } else {
////            System.out.println("Player not found.");
////        }
////    }
}
