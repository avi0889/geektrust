package com.geektrust.factory;


import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.kingdom.Kingdom;

import java.util.List;

public interface KingdomFactory {
    Kingdom getKingdom(String kingdomName) throws NoSuchKingdomException;
    List<Kingdom> getAllKingdoms();
    int totalNumberOfKingdoms();
}
