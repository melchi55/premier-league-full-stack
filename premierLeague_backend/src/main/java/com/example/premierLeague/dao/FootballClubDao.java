package com.example.premierLeague.dao;

import java.util.Optional;
import java.util.UUID;

import com.example.premierLeague.model.FootballClub;

public interface FootballClubDao {


    FootballClub[] selectAllClubs();

    int updateClubByID(UUID id, FootballClub club);

    Optional<FootballClub> selectClubById(UUID id);


    
}
