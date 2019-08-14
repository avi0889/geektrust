package com.geektrust.utils;


import com.geektrust.kingdom.Kingdom;

public interface Election {
    void addContestant(Kingdom contestant);
    void elect();
    Kingdom winner();
}
