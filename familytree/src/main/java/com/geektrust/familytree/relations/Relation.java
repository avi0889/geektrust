package com.geektrust.familytree.relations;

import com.geektrust.familytree.entity.Person;

import java.util.List;

public interface Relation {
    List<Person> getRelatives(Person person);
}
