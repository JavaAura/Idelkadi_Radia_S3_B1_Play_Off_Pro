package org.spring.ConsoleUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.models.Game;
import org.spring.models.Team;
import org.spring.models.Tournament;
import org.spring.models.enums.GameDifficulty;
import org.spring.services.PlayerService;
import org.spring.services.TeamService;
import org.spring.services.TournamentService;
import org.spring.utils.InputValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TournamentMenu {
    private static final Logger logger = LoggerFactory.getLogger(TournamentMenu.class);
    private static Scanner scanner;
    private static TournamentService tournamentService;
    private static ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");


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
            logger.info("6. Add Teams to Tournament");
            logger.info("7. Remove Teams from Tournament");
            logger.info("8. Exit");
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
                    addTeamsToTournament();
                    break;
                case 7:
                    removeTeamsFromTournament();
                    break;
                case 8:
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

    private static void addTeamsToTournament() {
                TeamService teamService = (TeamService) context.getBean("teamService");


        logger.info("Enter Tournament ID to add teams: ");
        Long tournamentId = scanner.nextLong();
        scanner.nextLine();

        Tournament tournament = tournamentService.readTournament(tournamentId); // Méthode à implémenter dans votre service
        if (tournament == null) {
            logger.warn("Tournament not found.");
            return;
        }

        while (true) {
            logger.info("Enter Team ID to add to the tournament (or 0 to stop): ");
            Long teamId = scanner.nextLong();
            scanner.nextLine();

            if (teamId == 0) {
                break;
            }

            Team team = teamService.getTeamById(teamId);
            if (team != null) {
                tournament.addTeam(team);
                tournamentService.updateTournament(tournament); // Mettez à jour le tournoi
                logger.info("Team {} added to tournament {}.", team.getName(), tournament.getTitle());
            } else {
                logger.warn("Team with ID {} not found.", teamId);
            }
        }

        logger.info("All specified teams have been added to the tournament.");
    }

    private static void removeTeamsFromTournament() {
        TeamService teamService = (TeamService) context.getBean("teamService");


        logger.info("Enter Tournament ID to remove teams from: ");
        Long tournamentId = scanner.nextLong();
        scanner.nextLine();

        Tournament tournament = tournamentService.readTournament(tournamentId); // Méthode à implémenter dans votre service
        if (tournament == null) {
            logger.warn("Tournament not found.");
            return;
        }

        while (true) {
            logger.info("Enter Team ID to remove from the tournament (or 0 to stop): ");
            Long teamId = scanner.nextLong();
            scanner.nextLine();

            if (teamId == 0) {
                break;
            }

            Team team = teamService.getTeamById(teamId);
            if (team != null) {
                tournament.removeTeam(team);
                tournamentService.updateTournament(tournament);
                logger.info("Team {} removed from tournament {}.", team.getName(), tournament.getTitle());
            } else {
                logger.warn("Team with ID {} not found.", teamId);
            }
        }

        logger.info("All specified teams have been removed from the tournament.");
    }
}
