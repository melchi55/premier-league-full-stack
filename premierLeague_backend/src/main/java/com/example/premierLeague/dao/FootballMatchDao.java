package com.example.premierLeague.dao;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.example.premierLeague.model.Date;
import com.example.premierLeague.model.FootballMatch;

public interface FootballMatchDao {


    int insertFootballMatch(UUID id, FootballMatch footballMatch);

    default int insertFootballMatch(FootballMatch footballMatch){
        UUID id = UUID.randomUUID();
        return insertFootballMatch(id, footballMatch);
    }

    FootballMatch[] selectAllMatches();

    Optional<FootballMatch> selectMatchById(UUID id);

    ArrayList<FootballMatch> selectAllMatchesByDate(Date date);


}
