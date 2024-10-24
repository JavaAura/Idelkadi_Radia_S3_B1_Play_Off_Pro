package org.spring.ConsoleUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.models.Game;
import org.spring.models.Tournament;
import org.spring.models.enums.GameDifficulty;
import org.spring.services.TournamentService;
import org.spring.utils.InputValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TournamentMenu {
    private static final Logger logger = LoggerFactory.getLogger(TournamentMenu.class);
    private static Scanner scanner;
    private static TournamentService tournamentService;

    public TournamentMenu(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    public static void showTournamentMenu(Scanner scan) {
        scanner = scan;
        boolean running = true;
        while (running) {
            logger.info("===== Tournament Management Menu =====");
            logger.info("1. Create Tournament");
            logger.info("2. Read Tournament");
            logger.info("3. Read All Tournaments");
            logger.info("4. Update Tournament");
            logger.info("5. Delete Tournament");
            logger.info("6. Exit");
            logger.info("Choose an option: ");

            int choice = InputValidator.validatePositiveInteger();
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
                    logger.warn("Invalid option. Please try again.");
            }
        }
    }

    private static void createTournament() {
        Tournament tournament = new Tournament();
        logger.info("Enter tournament title: ");
        tournament.setTitle(scanner.nextLine());
        logger.info("Enter number of spectators: ");
        tournament.setNumSpectators(scanner.nextInt());
        scanner.nextLine();
        logger.info("Enter start date (YYYY-MM-DD): ");
        tournament.setStartDate(LocalDate.parse(scanner.nextLine()));
        logger.info("Enter end date (YYYY-MM-DD): ");
        tournament.setEndDate(LocalDate.parse(scanner.nextLine()));
        logger.info("Enter ceremony time: ");
        tournament.setCeremonyTime(InputValidator.validatePositiveDouble());
        logger.info("Enter break time between matches: ");
        tournament.setBreakTimeBetweenMatches(InputValidator.validatePositiveDouble());
        logger.info("Enter estimated duration: ");
        tournament.setEstimatedDuration(InputValidator.validatePositiveDouble());

        scanner.nextLine();

        logger.info("===== Game Details =====");
        logger.info("Enter game name: ");
        String gameName = scanner.nextLine();

        Game game = new Game();
        game.setName(gameName);
        game.setAvgMatchDuration(20);
        game.setDifficulty(GameDifficulty.EASY);
        tournament.setGame(game);

        if (tournamentService.createTournament(tournament)) {
            logger.info("Tournament created successfully!");
        } else {
            logger.error("Failed to create tournament.");
        }
    }

    private static void readTournament() {
        logger.info("Enter tournament ID: ");
        Long id = scanner.nextLong();
        Tournament tournament = tournamentService.readTournament(id);
        if (tournament != null) {
            logger.info("Tournament details: {}", tournament);
        } else {
            logger.warn("Tournament not found.");
        }
    }

    private static void readAllTournaments() {
        List<Tournament> tournaments = tournamentService.readAllTournaments();
        if (tournaments != null && !tournaments.isEmpty()) {
            for (Tournament tournament : tournaments) {
                logger.info("Tournament: {}", tournament);
            }
        } else {
            logger.info("No tournaments found.");
        }
    }

    private static void updateTournament() {
        logger.info("Enter tournament ID to update: ");
        Long id = scanner.nextLong();
        Tournament tournament = tournamentService.readTournament(id);
        if (tournament != null) {
            logger.info("Enter new title (leave blank to keep current): ");
            String title = scanner.nextLine();
            if (!title.isEmpty()) {
                tournament.setTitle(title);
            }

            if (tournamentService.updateTournament(tournament)) {
                logger.info("Tournament updated successfully!");
            } else {
                logger.error("Failed to update tournament.");
            }
        } else {
            logger.warn("Tournament not found.");
        }
    }

    private static void deleteTournament() {
        logger.info("Enter tournament ID to delete: ");
        Long id = scanner.nextLong();
        if (tournamentService.deleteTournament(id)) {
            logger.info("Tournament deleted successfully!");
        } else {
            logger.error("Failed to delete tournament.");
        }
    }
}
