package org.spring;

import org.spring.ConsoleUI.PlayerMenu;
import org.spring.ConsoleUI.TeamMenu;
import org.spring.ConsoleUI.TournamentMenu;
import org.spring.utils.ScannerUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static void showMainMenu() {
        scanner = ScannerUtil.getInstance();
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Manage Teams");
            System.out.println("2. Manage Players");
            System.out.println("3. Manage Tournaments");
            System.out.println("4. Exit");
            System.out.print("Please select an option (1-4): ");

            int choice = scanner.nextInt();
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
                    TournamentMenu tournamentMenu = (TournamentMenu) context.getBean("tournamentMenu");
                    TournamentMenu.showTournamentMenu(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        showMainMenu();
        scanner.close();
    }

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("tournament");
//    EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();
//        em.getTransaction().commit();
//
//        em.close();
//        emf.close();
}