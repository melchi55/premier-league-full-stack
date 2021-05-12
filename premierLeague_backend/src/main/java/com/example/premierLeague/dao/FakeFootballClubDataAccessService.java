package com.example.premierLeague.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.example.premierLeague.model.FootballClub;

import org.springframework.stereotype.Repository;

@Repository("fakeDao")
public class FakeFootballClubDataAccessService implements FootballClubDao {

    public static FootballClub[] DB = new FootballClub[20];


    @Override
    public FootballClub[] selectAllClubs() {
        DB = new FootballClub[20];
        loadInfo();
        ArrayList<FootballClub> sortedClubArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (DB[i] != null) {
                sortedClubArrayList.add(DB[i]);
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







    public static void saveInfo() {
        try {
            FileOutputStream fileOutClubs = new FileOutputStream(new File("/clubs"));
            ObjectOutputStream objectOutClubs = new ObjectOutputStream(fileOutClubs);


            for(FootballClub club:DB) {
                objectOutClubs.writeObject(club);
            }
            fileOutClubs.close();
            objectOutClubs.close();

            System.out.println("done saving Clubs information");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

 

    public static void loadInfo(){

        try {

            FileInputStream fiClub = new FileInputStream(new File("clubs"));
            ObjectInputStream oiClub = new ObjectInputStream(fiClub);

            for (int i = 0; i < 20; i++) {
                DB[i] = (FootballClub) oiClub.readObject();
            }
            
            oiClub.close();
            fiClub.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int updateClubByID(UUID id, FootballClub club) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<FootballClub> selectClubById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }





    
}
