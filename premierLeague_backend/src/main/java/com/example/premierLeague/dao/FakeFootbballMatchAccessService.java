package com.example.premierLeague.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.example.premierLeague.model.Date;
import com.example.premierLeague.model.FootballMatch;

import org.springframework.stereotype.Repository;

@Repository("fakeMatches")
public class FakeFootbballMatchAccessService implements FootballMatchDao {

    public static ArrayList<FootballMatch> DB = new ArrayList<>();

    @Override
    public int insertFootballMatch(UUID id, FootballMatch footballMatch) {
        DB.add(new FootballMatch(id, footballMatch.getTeam1(), footballMatch.getTeam2(), footballMatch.getDate(),
                footballMatch.getTeam1Score(), footballMatch.getTeam2Score()));
        return 1;
    }

    @Override
    public FootballMatch[] selectAllMatches() {
        DB.clear();
        loadInfo();
        FootballMatch[] sortedArray = new FootballMatch[DB.size()];
        for (int i = 0; i < DB.size(); i++) {
            sortedArray[i] = DB.get(i);
        }

        int n = sortedArray.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j].getDate().getYear() > sortedArray[j + 1].getDate().getYear()) {
                    FootballMatch temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;

                }
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j].getDate().getYear() == sortedArray[j + 1].getDate().getYear())
                    if (sortedArray[j].getDate().getMonth() > sortedArray[j + 1].getDate().getMonth()) {
                        FootballMatch temp = sortedArray[j];
                        sortedArray[j] = sortedArray[j + 1];
                        sortedArray[j + 1] = temp;

                    }
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j].getDate().getMonth() == sortedArray[j + 1].getDate().getMonth())
                    if (sortedArray[j].getDate().getDay() > sortedArray[j + 1].getDate().getDay()) {
                        FootballMatch temp = sortedArray[j];
                        sortedArray[j] = sortedArray[j + 1];
                        sortedArray[j + 1] = temp;

                    }
            }
        }

        return sortedArray;
    }

    @Override
    public Optional<FootballMatch> selectMatchById(UUID id) {
        return DB.stream().filter(match -> match.getId().equals(id)).findFirst();
    }


    public static void loadInfo() {
        try {

            FileInputStream fiMatch = new FileInputStream(new File("matches"));
            ObjectInputStream oiMatch = new ObjectInputStream(fiMatch);

            for (int i = 0; i < 100; i++) {
                DB.add((FootballMatch) oiMatch.readObject());
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

    @Override
    public ArrayList<FootballMatch> selectAllMatchesByDate(Date date) {
        DB.clear();
        loadInfo();
        FootballMatch[] sortedArray = new FootballMatch[DB.size()];
        for (int i = 0; i < DB.size(); i++) {
            sortedArray[i] = DB.get(i);
        }

        int n = sortedArray.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j].getDate().getYear() > sortedArray[j + 1].getDate().getYear()) {
                    FootballMatch temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;

                }
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j].getDate().getYear() == sortedArray[j + 1].getDate().getYear())
                    if (sortedArray[j].getDate().getMonth() > sortedArray[j + 1].getDate().getMonth()) {
                        FootballMatch temp = sortedArray[j];
                        sortedArray[j] = sortedArray[j + 1];
                        sortedArray[j + 1] = temp;

                    }
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j].getDate().getMonth() == sortedArray[j + 1].getDate().getMonth())
                    if (sortedArray[j].getDate().getDay() > sortedArray[j + 1].getDate().getDay()) {
                        FootballMatch temp = sortedArray[j];
                        sortedArray[j] = sortedArray[j + 1];
                        sortedArray[j + 1] = temp;

                    }
            }
        }
        
        ArrayList<FootballMatch> byDate = new ArrayList<>();
        for(FootballMatch match: sortedArray){
            if(match.getDate().toString().equals(date.toString())){
                byDate.add(match);
            }
        }

        return byDate;

}


    





  
    
}
