package com.example.premierLeague.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FootballClub extends SportsClub {

    private int wins;
    private int defeats;
    private int draws;
    private int goalsReceived;
    private int goalsScored;
    private int goalDiffrence;
    private int points;
    private int matches;

    public FootballClub(@JsonProperty("name") String clubName, @JsonProperty("location") String location,
            @JsonProperty("id") UUID uuid) {
        super(clubName, location, uuid);

    }

    public int getGoalDiffrence() {
        return goalDiffrence;
    }

    public void setGoalDiffrence() {
        this.goalDiffrence = this.goalsScored - this.goalsReceived;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }


    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }


    @Override
    public String toString(){
        return  "\nClub name  : "  + this.getClubName() +
                "\nLocation   : "  + this.getLocation() +
                "\nTotal mat  : "  + this.getMatches() +
                "\nWins       : "  + this.getWins() +
                "\nDefeats    : "  + this.getDefeats() +
                "\nDraws      : "  + this.getDraws() +
                "\nleague sco : "  + this.getPoints() +
                "\nGA         : "  + this.getGoalsReceived() +
                "\nGD         : "  + this.getGoalDiffrence() +
                "\nGS         : "  + this.getGoalsScored() ;


    }

    public String toStringForTable(){
        return    "   " + this.getMatches() +
                "        " + this.getWins() +
                "       "+ this.getDefeats()
                +"         "+ this.draws +
                "          " + (this.getGoalsScored())+
                "          " + this.getGoalsReceived()+
                "         " + this.getGoalDiffrence()+
                "         "  + this.getPoints()+"          "+
                this.getClubName();

    }


    public void addMatch(){
        this.matches += 1;
    }

    public void addDefeat(){
        this.defeats += 1;
    }


    public void addGoalsReceived(int goals){
        this.goalsReceived += goals;
    }

    public void addGoalsScored(int goals){
        this.goalsScored += goals;
    }

    public void winStatus(String status){
        if(status.equals("win")){
            this.wins +=1;
            this.points += 3;
        }
        else {
            this.draws += 1;
            this.points += 1;
        }
    }

    public String getDetail(String name) {
        String output;
        switch (name) {
            case "Name":
                output = getClubName();
                break;
            case "Matches":
                output = String.valueOf(getMatches());
                break;
            case "Win":
                output = String.valueOf(getWins());
                break;
            case "Lost":
                output = String.valueOf(getDefeats());
                break;
            case "Draw":
                output = String.valueOf(getDraws());
                break;
            case "For":
                output = String.valueOf(getGoalsScored());
                break;
            case "Against":
                output = String.valueOf(getGoalsReceived());
                break;
            case "GD":
                output = String.valueOf(getGoalDiffrence());
                break;
            case "Score":
                output = String.valueOf(getPoints());
                break;
            default: {
                System.out.println("Invalid input ....");
                return output =  "";
            }

        }

        return output;
    }


    
}
