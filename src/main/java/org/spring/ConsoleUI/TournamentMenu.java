package org.spring.ConsoleUI;

import org.spring.models.Game;
import org.spring.models.Tournament;
import org.spring.models.enums.GameDifficulty;
import org.spring.services.TournamentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TournamentMenu {

    private static Scanner scanner;
    private static TournamentService tournamentService;

    public TournamentMenu(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    public static void showTournamentMenu(Scanner scan) {
        scanner = scan;
        boolean running = true;
        while (running) {
            System.out.println("===== Tournament Management Menu =====");
            System.out.println("1. Create Tournament");
            System.out.println("2. Read Tournament");
            System.out.println("3. Read All Tournaments");
            System.out.println("4. Update Tournament");
            System.out.println("5. Delete Tournament");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createTournament();
                    break;
                case 2:
                    readTournament();
                    break;
                case 3:
                    readAllTournaments();
                    break;
                case 4:
                    updateTournament();
                    break;
                case 5:
                    deleteTournament();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createTournament() {
        Tournament tournament = new Tournament();
        System.out.print("Enter tournament title: ");
        tournament.setTitle(scanner.nextLine());
        System.out.print("Enter number of spectators: ");
        tournament.setNumSpectators(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter start date (YYYY-MM-DD): ");
        tournament.setStartDate(LocalDate.parse(scanner.nextLine()));
        System.out.print("Enter end date (YYYY-MM-DD): ");
        tournament.setEndDate(LocalDate.parse(scanner.nextLine()));
        System.out.print("Enter ceremony time: ");
        tournament.setCeremonyTime(scanner.nextDouble());
        System.out.print("Enter break time between matches: ");
        tournament.setBreakTimeBetweenMatches(scanner.nextDouble());
        System.out.print("Enter estimated duration: ");
        tournament.setEstimatedDuration(scanner.nextDouble());

        scanner.nextLine();

        System.out.println("===== Game Details =====");
        System.out.print("Enter game name: ");
        String gameName = scanner.nextLine();

        Game game = new Game();
        game.setName(gameName);
        game.setAvgMatchDuration(20);
        game.setDifficulty(GameDifficulty.EASY);


        tournament.setGame(game);

        if (tournamentService.createTournament(tournament)) {
            System.out.println("Tournament created successfully!");
        } else {
            System.out.println("Failed to create tournament.");
        }
    }

    private static void readTournament() {
        System.out.print("Enter tournament ID: ");
        Long id = scanner.nextLong();
        Tournament tournament = tournamentService.readTournament(id);
        if (tournament != null) {
            System.out.println(tournament);
        } else {
            System.out.println("Tournament not found.");
        }
    }

    private static void readAllTournaments() {
        List<Tournament> tournaments = tournamentService.readAllTournaments();
        if (tournaments != null && !tournaments.isEmpty()) {
            for (Tournament tournament : tournaments) {
                System.out.println(tournament);
            }
        } else {
            System.out.println("No tournaments found.");
        }
    }

    private static void updateTournament() {
        System.out.print("Enter tournament ID to update: ");
        Long id = scanner.nextLong();
        Tournament tournament = tournamentService.readTournament(id);
        if (tournament != null) {
            System.out.print("Enter new title (leave blank to keep current): ");
            String title = scanner.nextLine();
            if (!title.isEmpty()) {
                tournament.setTitle(title);
            }

            if (tournamentService.updateTournament(tournament)) {
                System.out.println("Tournament updated successfully!");
            } else {
                System.out.println("Failed to update tournament.");
            }
        } else {
            System.out.println("Tournament not found.");
        }
    }

    private static void deleteTournament() {
        System.out.print("Enter tournament ID to delete: ");
        Long id = scanner.nextLong();
        if (tournamentService.deleteTournament(id)) {
            System.out.println("Tournament deleted successfully!");
        } else {
            System.out.println("Failed to delete tournament.");
        }
    }
}