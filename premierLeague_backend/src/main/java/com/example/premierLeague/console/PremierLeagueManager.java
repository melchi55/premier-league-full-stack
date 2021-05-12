package com.example.premierLeague.console;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import com.example.premierLeague.model.Date;
import com.example.premierLeague.model.FootballClub;
import com.example.premierLeague.model.FootballMatch;

public class PremierLeagueManager implements LeagueManager{

    private static final Scanner scanner = new Scanner(System.in);
    public static int totalMatches ;
    public static boolean leagueNotStarted = true;
    public static ArrayList<FootballMatch> matches = new ArrayList<>();


    //rest the application
    public void resetTheApplication(){

        totalMatches = 0;
        leagueNotStarted = true;

        for (int i = 0; i < NUMBER_OF_TEAMS; i++) {
            footballClubs[i] = null;

        }

        matches = new ArrayList<>();

    Main.menu();

    }

    //create footballl club
    @Override
    public void createFootballClub() {

        if(leagueNotStarted) {
            if (totalClubs() < 20) {

                System.out.print("Enter your club name :- ");
                String clubName = scanner.nextLine().toLowerCase().trim();
                UUID id = UUID.randomUUID();
                if(clubName.equals("")){
                    System.out.println("Please enter a Club name ...");
                    createFootballClub();
                }
                if (clubNameNotExist(clubName) ) {
                    System.out.print("Enter your location :- ");
                    String location = scanner.nextLine();
                    for (int i = 0; i < NUMBER_OF_TEAMS; i++) {
                        if (footballClubs[i] == null) {
                            footballClubs[i] = new FootballClub(clubName,location,id);
                            System.out.println(footballClubs[i].getClubName() + " has been added to the premier league");
                            totalMatches();
                            break;

                        }
                    }
                } else {

                    System.out.println("Club name already Exist..");
                    Main.menu();

                }

            } else {

                System.out.println("cant add more team");
            }
        }
        else{

            System.out.println("league started");
        }

        Main.menu();

    }

    //matches left
    public void matchesLeft(){


        if( matches.size() > 0){

            leagueNotStarted = false;

        }
        totalMatches = totalMatches - matches.size();

    }


    //get total clubs
    public int totalClubs(){

        int total = 0;

        for (int i = 0; i < NUMBER_OF_TEAMS; i++) {

            if (footballClubs[i] != null) {
                total += 1;

            }

        }

        return total;

    }


    //total matches
    public void totalMatches(){

        totalMatches = 0;
        for(int i = (totalClubs() - 1) ; 0 < i ; i--){
            totalMatches += i;
        }

    }


    //delete club
    @Override
    public void deleteExistingClub() {

        if(leagueNotStarted) {
            if (totalClubs() > 0) {
                System.out.print("Enter your club name :- ");
                String clubName = scanner.nextLine().toLowerCase().trim();
                if (!clubNameNotExist(clubName)) {
                    for (int i = 0; i < NUMBER_OF_TEAMS; i++) {
                        if (footballClubs[i] != null) {
                            if (footballClubs[i].getClubName().equals(clubName)) {
                                System.out.println(footballClubs[i].getClubName() + " has been relegated from the premier league");
                                footballClubs[i] = null;
                                totalMatches();
                                break;
                            }

                        }
                    }
                } else {
                    System.out.println("Club name is not in the league..");
                    Main.menu();
                }

            } else {
                System.out.println("There is no teams registered at the moment ");
            }
        }
        else{
            System.out.println("league started");
        }
        Main.menu();
    }


    //check if club name excist
    public Boolean clubNameNotExist(String clubName){
        boolean notExist = true;
        for (int i = 0; i < NUMBER_OF_TEAMS; i++) {

            if (footballClubs[i] != null ) {
                if(footballClubs[i].getClubName().equals(clubName)){
                    notExist = false;
                    break;
                }

            }

        }
        return notExist;
    }


    //display statistic 
    @Override
    public void displayStatistic() {
        if( totalClubs() > 0 ){
            System.out.print("Enter your club name :- ");
            String clubName = scanner.nextLine().toLowerCase().trim();
            if(!clubNameNotExist(clubName)) {
                for (int i = 0; i < NUMBER_OF_TEAMS; i++) {
                    if (footballClubs[i] != null) {
                        if (footballClubs[i].getClubName().equals(clubName))
                        {
                            System.out.println(footballClubs[i].toString());
                            break;
                        }
                    }
                }
            }
            else {
                System.out.println("Club name is not in the league..");
            }

        }
        else{
            System.out.println("There is no teams registered at the moment ");

        }
        Main.menu();
    }


    //display the table
    public void displayTable() {

        if( totalClubs() > 0 ){
            System.out.println("Matches    wins    lost      draws      for      Against      GD      points        Club");
            FootballClub[] footballClubs = sortTheArrayPoints();
            for (FootballClub footballClub : footballClubs) {
                if (footballClub != null) {
                    System.out.println(footballClub.toStringForTable());
                }
            }
        }
        else{
            System.out.println("There is no teams registered at the moment ");
        }
        Main.menu();

    }




    //sort the arry by points 
    public FootballClub[] sortTheArrayPoints(){

        ArrayList<FootballClub> sortedClubArrayList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_TEAMS; i++) {
            if (footballClubs[i] != null) {
                sortedClubArrayList.add(footballClubs[i]);
            }
        }

        FootballClub[] sortedArray = new FootballClub[sortedClubArrayList.size()];
        for (int i = 0; i < sortedClubArrayList.size(); i++) {
                sortedArray[i] = sortedClubArrayList.get(i);
        }

        int n = sortedClubArrayList.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (sortedArray[j].getPoints() == sortedArray[j+1].getPoints())
                {
                    if(sortedArray[j].getGoalDiffrence() < sortedArray[j+1].getGoalDiffrence()){
                    FootballClub temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j+1];
                    sortedArray[j+1] = temp;
                }}
                else if(sortedArray[j].getPoints() < sortedArray[j+1].getPoints()){
                    FootballClub temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j+1];
                    sortedArray[j+1] = temp;

                }

                return sortedArray;

    }


    //choose random clubs
    @Override
    public void addPlayedMatch() {

        Random random = new Random();
        FootballClub[] footballClubs = sortTheArrayPoints();
        int total = footballClubs.length;

        if(total >= 3){

            if( totalMatches > 0 ) {

                    int team1Index = random.nextInt(total);
                    int team2Index = random.nextInt(total);

                    if (team1Index != team2Index) {

                        FootballClub team1 = footballClubs[team1Index];
                        FootballClub team2 = footballClubs[team2Index];

                        String team1name = team1.getClubName();
                        String team2name = team2.getClubName();

                        int check = 0;

                        if (0 != matches.size()) {
                            for (FootballMatch match : matches) {
                                check += match.check(team1name, team2name);
                            }
                            if (check == 0) {
                                updateStatistic(team1, team2);
                            } else {
                                addPlayedMatch();
                            }
                        } else {
                            updateStatistic(team1, team2);

                        }

                    } else {
                        addPlayedMatch();
                    }
                } else {
                    System.out.println("All the matches are finished");
                }
            }

        else{
            System.out.println("Not enough teams");
        }
        Main.menu();
    }



    //add statistic to the seleted clubs
    public void updateStatistic(FootballClub club1, FootballClub club2 ){

            Random random = new Random();
            int club1Score = random.nextInt(4);
            int club2Score = random.nextInt(4);
            UUID id = UUID.randomUUID();
            Date date = generateDate();
            FootballMatch match = new FootballMatch(id,club1,club2,date,club1Score,club2Score);
            System.out.println(match);
            matches.add(match);
            club1.addMatch();
            club2.addMatch();
            club1.addGoalsReceived(club2Score);
            club2.addGoalsReceived(club1Score);
            club1.addGoalsScored(club1Score);
            club2.addGoalsScored(club2Score);
            club1.setGoalDiffrence();
            club2.setGoalDiffrence();

            if(club1Score > club2Score){
                club1.winStatus("win");
                club2.addDefeat();
            }
            else if (club1Score < club2Score){
                club2.winStatus("win");
                club1.addDefeat();

            }
            else{
                club1.winStatus("draw");
                club2.winStatus("draw");
            }
            totalMatches -= 1;
    }

    //save date to the files
    @Override
    public void saveInfo() {
        try {
            FileOutputStream fileOutClubs = new FileOutputStream(new File(saveClubFile));
            ObjectOutputStream objectOutClubs = new ObjectOutputStream(fileOutClubs);
            FileOutputStream fileOutMatches = new FileOutputStream(new File(saveMatchFile));
            ObjectOutputStream objectOutMatches = new ObjectOutputStream(fileOutMatches);

            for(FootballClub club:footballClubs) {
                objectOutClubs.writeObject(club);
            }
            fileOutClubs.close();
            objectOutClubs.close();

            System.out.println("done saving Clubs information");

            for(FootballMatch match:matches){
                objectOutMatches.writeObject(match);
            }

            fileOutMatches.close();
            objectOutMatches.close();
            System.out.println("done saving Matches information");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Main.menu();
    }

    //gererate random date 
    public Date generateDate(){
        int day = randBetween(1,27);
        int month = randBetween(1,12);
        int year = randBetween(2020,2021);
        Date date = new Date(day,month,year);
        for (FootballMatch match : matches) {
            if(match.getDate().toString().equals(date.toString())){
                generateDate();
            }
        }
        return date;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }




    //loading data from the files
    public void loadInfo(){

        try {

            FileInputStream fiClub = new FileInputStream(new File(saveClubFile));
            ObjectInputStream oiClub = new ObjectInputStream(fiClub);
            FileInputStream fiMatch = new FileInputStream(new File(saveMatchFile));
            ObjectInputStream oiMatch = new ObjectInputStream(fiMatch);

            // Read objects
            for (int i = 0; i < NUMBER_OF_TEAMS; i++) {
                footballClubs[i] = (FootballClub) oiClub.readObject();
            }
            oiClub.close();
            fiClub.close();

            totalMatches();

            for(int i = 0; i < totalMatches; i++){

                
                matches.add((FootballMatch) oiMatch.readObject());

            }
            oiMatch.close();
            fiMatch.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}




