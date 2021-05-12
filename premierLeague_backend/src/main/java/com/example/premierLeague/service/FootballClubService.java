package com.example.premierLeague.service;

import java.util.Optional;
import java.util.UUID;

import com.example.premierLeague.dao.FootballClubDao;
import com.example.premierLeague.model.FootballClub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FootballClubService {

    private final FootballClubDao footballClubDao;

    @Autowired
    public FootballClubService(@Qualifier("fakeDao") FootballClubDao footBallClubDao){
        this.footballClubDao = footBallClubDao;
    }

    public FootballClub[] getAllClubs(){
        return this.footballClubDao.selectAllClubs();
    }

    public Optional<FootballClub> getClubById(UUID id){
        return this.footballClubDao.selectClubById(id);
    }

    public int updateClubById(UUID id, FootballClub club){
        return this.footballClubDao.updateClubByID(id,club);
    }




}
