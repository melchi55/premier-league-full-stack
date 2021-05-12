package com.example.premierLeague.model;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FootballMatch implements Serializable{

    private UUID id;
    private FootballClub team1;
    private FootballClub team2;
    private Date date;
    private int team1Score;
    private int team2Score;

    public FootballMatch(@JsonProperty("id") UUID id, @JsonProperty("team1") FootballClub team1,
            @JsonProperty("team2") FootballClub team2, @JsonProperty("date") Date date,
            @JsonProperty("team1Score") int team1Score, @JsonProperty("team2Score") int team2Score) {

        this.setId(id);
        this.setTeam1(team1);
        this.setTeam2(team2);
        this.setDate(date);
        this.setTeam1Score(team1Score);
        this.setTeam2Score(team2Score);

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public FootballClub getTeam2() {
        return team2;
    }

    public void setTeam2(FootballClub team2) {
        this.team2 = team2;
    }

    public FootballClub getTeam1() {
        return team1;
    }

    public void setTeam1(FootballClub team1) {
        this.team1 = team1;
    }

    public int check(String team1name, String team2name){
        int check = 0;

        if((team1name.equals(team1.getClubName())) && (team2name.equals(team2.getClubName()))){
            check += 1;
        }

        if((team2name.equals(team1.getClubName())) && (team1name.equals(team2.getClubName()))){
            check += 1;
        }

        return check;


    }

    @Override
    public String toString(){
        return this.team1.getClubName() + " : " + this.team1Score + " vs " + this.team2.getClubName() +" : " + this.team2Score+ " at : "+ date;
    }



    public String getDetail(String name) {
        String output;
        switch (name) {
            case "Date":
                output = getDate().toString();
                break;
            case "team1":
                output = getTeam1().getClubName() + "(" + getTeam1Score() +")";
                break;
            case "team2":
                output = getTeam2().getClubName() + "(" + getTeam2Score() +")";
                break;

                default:
            System.out.println("Invalid input ....");
            return output =  "";
        }

        return output;
    }


}
