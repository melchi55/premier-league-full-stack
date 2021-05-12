package com.example.premierLeague.service;

import com.example.premierLeague.dao.AddMatchDao;
import com.example.premierLeague.model.FootballMatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AddMatchService {
    

    private final AddMatchDao addMatchDao;

    @Autowired
    public AddMatchService(@Qualifier("addmatch") AddMatchDao addMatchDao){
        this.addMatchDao = addMatchDao;
    }

    public FootballMatch addMatch(){
        return this.addMatchDao.addMatcFootballMatch();
    }
    
}
