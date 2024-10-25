package org.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.ConsoleUI.GameMenu;
import org.spring.ConsoleUI.PlayerMenu;
import org.spring.ConsoleUI.TeamMenu;
import org.spring.ConsoleUI.TournamentMenu;
import org.spring.utils.InputValidator;
import org.spring.utils.ScannerUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void showMainMenu() {
        scanner = ScannerUtil.getInstance();
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        while (true) {
            logger.info("=== Main Menu ===");
            logger.info("1. Manage Teams");
            logger.info("2. Manage Players");
            logger.info("3. Manage Games");
            logger.info("4. Manage Tournaments");
            logger.info("5. Exit");
            logger.info("Please select an option (1-4): ");

            int choice = InputValidator.validatePositiveInteger();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    TeamMenu teamMenu = (TeamMenu) context.getBean("teamMenu");
                    TeamMenu.showTeamMenu(scanner);
                    break;
                case 2:
                    PlayerMenu playerMenu = (PlayerMenu) context.getBean("playerMenu");
                    PlayerMenu.showPlayerMenu(scanner);
                    break;
                case 3:
                    GameMenu gameMenu = (GameMenu) context.getBean("gameMenu");
                    GameMenu.showGameMenu(scanner);
                    break;
                case 4:
                    TournamentMenu tournamentMenu = (TournamentMenu) context.getBean("tournamentMenu");
                    TournamentMenu.showTournamentMenu(scanner);
                    break;
                case 5:
                    logger.info("Exiting...");
                    return;
                default:
                    logger.warn("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        logger.info("Starting application...");
        showMainMenu();
        scanner.close();
    }
}
