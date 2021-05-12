package com.example.premierLeague.model;

import java.io.Serializable;
import java.util.UUID;

public class SportsClub implements Serializable{
    private String clubName;
    private String location;
    private UUID uuid;

    public SportsClub(String clubName, String location, UUID uuid) {
        this.clubName = clubName;
        this.location = location;
        this.setUuid(uuid);

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
