package com.example.premierLeague.console;
import java.util.Scanner;

public class Main  {

    private static final PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args){
        premierLeagueManager.loadInfo();
        premierLeagueManager.totalClubs();
        premierLeagueManager.matchesLeft();
        menu();
    }

    public static void menu(){

        System.out.print("// Premier League Manager //" +
                "\nCreate a new FootBall club (C) "  +
                "\nRelegate an existing club  (R) " +
                "\nDisplay the statistics     (D) " +
                "\nDisplay the league table   (T) " +
                "\nAdd a played match         (M) " +
                "\nSave all the Information   (S) " +
                "\nReset the application      (A) " +
                "\nEnd The Application        (E)"  +
                "\n--Select one letter to continue" +
                "\n--: ");

        String input = scanner.next().toLowerCase().trim();

        switch (input) {
            case "t" :
                premierLeagueManager.displayTable();
                break;
            case "c" :
                premierLeagueManager.createFootballClub();
                break;
            case "r" :
                premierLeagueManager.deleteExistingClub();
                break;
            case "d" :
                premierLeagueManager.displayStatistic();
                break;
            case "m" :
                premierLeagueManager.addPlayedMatch();
                break;
            case "s" :
                premierLeagueManager.saveInfo();
                break;
            case "a" :
                premierLeagueManager.resetTheApplication();
                break;
            case "e" :
                System.exit(0);
                break;
            default :
                System.out.println("Invalid input ....");
                Main.menu();

        }
    }
}
