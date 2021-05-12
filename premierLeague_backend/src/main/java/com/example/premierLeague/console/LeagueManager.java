package com.example.premierLeague.console;

import com.example.premierLeague.model.FootballClub;

interface LeagueManager {

    int NUMBER_OF_TEAMS = 20;
    FootballClub[] footballClubs = new FootballClub[NUMBER_OF_TEAMS];
    String saveClubFile = "clubs";
    String saveMatchFile = "matches";

    void createFootballClub();
    void deleteExistingClub();
    void displayStatistic();
    void displayTable();
    void addPlayedMatch();
    void saveInfo();


}