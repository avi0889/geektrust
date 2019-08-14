package com.geektrust.factory;

import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.kingdom.*;
import com.geektrust.utils.Constants;

import java.util.*;

public class KingdomFactoryImpl implements KingdomFactory {

    private Map<String, Kingdom> allKingdoms = new HashMap<>();

    public KingdomFactoryImpl() {
        init();
    }

    private void init() {
        allKingdoms.put(Constants.spaceKingdomName.toLowerCase(),
                new Kingdom(Constants.spaceKingdomEmblem, Constants.spaceKingdomName));
        allKingdoms.put(Constants.landKingdomName.toLowerCase(),
                new Kingdom(Constants.landKingdomEmblem, Constants.landKingdomName));
        allKingdoms.put(Constants.waterKingdomName.toLowerCase(),
                new Kingdom(Constants.waterKingdomEmblem, Constants.waterKingdomName));
        allKingdoms.put(Constants.iceKingdomName.toLowerCase(),
                new Kingdom(Constants.iceKingdomEmblem, Constants.iceKingdomName));
        allKingdoms.put(Constants.airKingdomName.toLowerCase(),
                new Kingdom(Constants.airKingdomEmblem, Constants.airKingdomName));
        allKingdoms.put(Constants.fireKingdomName.toLowerCase(),
                new Kingdom(Constants.fireKingdomEmblem, Constants.fireKingdomName));
    }

    @Override
    public Kingdom getKingdom(String kingdomName) throws NoSuchKingdomException {
        Kingdom kingdom = allKingdoms.get(kingdomName.toLowerCase());
        if(kingdom == null)
            throw new NoSuchKingdomException(Constants.noSuchKingdomErrorMessage + kingdomName);
        return kingdom;
    }

    @Override
    public List<Kingdom> getAllKingdoms() {
        return Collections.unmodifiableList(new ArrayList<> (allKingdoms.values()));
    }

    @Override
    public int totalNumberOfKingdoms() {
        return allKingdoms.size();
    }
}
