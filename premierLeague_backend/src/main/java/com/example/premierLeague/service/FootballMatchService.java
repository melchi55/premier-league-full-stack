package com.example.premierLeague.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.example.premierLeague.dao.FootballMatchDao;
import com.example.premierLeague.model.Date;
import com.example.premierLeague.model.FootballMatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FootballMatchService {
    private final FootballMatchDao footballMatchDao;

    @Autowired
    public FootballMatchService(@Qualifier("fakeMatches") FootballMatchDao match){
        this.footballMatchDao = match;
    }

    public int addFootballMatch(FootballMatch footballMatch){
        return this.footballMatchDao.insertFootballMatch(footballMatch);
    }

    public FootballMatch[] getAllMatches(){
        return this.footballMatchDao.selectAllMatches();
    }

    public Optional<FootballMatch> getMatchById(UUID id){
        return this.footballMatchDao.selectMatchById(id);
    }

    public ArrayList<FootballMatch> getAllClubsByDate(Date date){
        return this.footballMatchDao.selectAllMatchesByDate(date);
     }

}
