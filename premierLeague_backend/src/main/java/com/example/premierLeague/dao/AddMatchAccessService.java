package com.example.premierLeague.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import com.example.premierLeague.model.Date;
import com.example.premierLeague.model.FootballClub;
import com.example.premierLeague.model.FootballMatch;

import org.springframework.stereotype.Repository;


@Repository("addmatch")
public class AddMatchAccessService implements AddMatchDao {

    public static ArrayList<FootballMatch> footballMatches = new ArrayList<>();
    public static FootballClub[] footballClubs = new FootballClub[20];
    public static int totalMatches ;
    public static boolean leagueNotStarted = true;
    public static FootballMatch returnMatch;

    @Override
    public FootballMatch addMatcFootballMatch() {

        footballMatches = new ArrayList<>();
        footballClubs = new FootballClub[20];
        loadInfo();
        totalMatches();
        matchesLeft();
        if(totalMatches == 0){
            return null; 
        }
        addPlayedMatch();

        saveInfo();

        return returnMatch;
        
    }

    public void loadInfo(){

        try {

            FileInputStream fiClub = new FileInputStream(new File("clubs"));
            ObjectInputStream oiClub = new ObjectInputStream(fiClub);
            FileInputStream fiMatch = new FileInputStream(new File("matches"));
            ObjectInputStream oiMatch = new ObjectInputStream(fiMatch);

            // Read objects
            for (int i = 0; i < 20; i++) {
                footballClubs[i] = (FootballClub) oiClub.readObject();
            }
            oiClub.close();
            fiClub.close();

            totalMatches();

            for(int i = 0; i < totalMatches; i++){

                
                footballMatches.add((FootballMatch) oiMatch.readObject());

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

    public void matchesLeft(){


        if( footballMatches.size() > 0){

            leagueNotStarted = false;

        }
        totalMatches = totalMatches - footballMatches.size();

    }


    public int totalClubs(){

        int total = 0;

        for (int i = 0; i < 20; i++) {

            if (footballClubs[i] != null) {
                total += 1;

            }

        }

        return total;

    }


    public void totalMatches(){

        totalMatches = 0;
        for(int i = (totalClubs() - 1) ; 0 < i ; i--){
            totalMatches += i;
        }

    }


    public void saveInfo() {
        try {
            FileOutputStream fileOutClubs = new FileOutputStream(new File("clubs"));
            ObjectOutputStream objectOutClubs = new ObjectOutputStream(fileOutClubs);
            FileOutputStream fileOutMatches = new FileOutputStream(new File("matches"));
            ObjectOutputStream objectOutMatches = new ObjectOutputStream(fileOutMatches);

            for(FootballClub club : footballClubs) {
                objectOutClubs.writeObject(club);
            }
            fileOutClubs.close();
            objectOutClubs.close();

            System.out.println("done saving Clubs information");

            for(FootballMatch match: footballMatches){
                objectOutMatches.writeObject(match);
            }

            fileOutMatches.close();
            objectOutMatches.close();
            System.out.println("done saving Matches information");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



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

                        if (0 != footballMatches.size()) {
                            for (FootballMatch match : footballMatches) {
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

        
    }

    public FootballClub[] sortTheArrayPoints(){

        ArrayList<FootballClub> sortedClubArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
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




    public void updateStatistic(FootballClub club1, FootballClub club2 ){

            Random random = new Random();
            int club1Score = random.nextInt(4);
            int club2Score = random.nextInt(4);
            UUID id = UUID.randomUUID();
            Date date = generateDate();
            FootballMatch match = new FootballMatch(id,club1,club2,date,club1Score,club2Score);
            returnMatch = match;
            footballMatches.add(match);
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

    public Date generateDate(){
        int day = randBetween(1,27);
        int month = randBetween(1,12);
        int year = randBetween(2020,2021);
        Date date = new Date(day,month,year);
        for (FootballMatch match : footballMatches) {
            if(match.getDate().toString().equals(date.toString())){
                generateDate();
            }
        }
        return date;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    
}
