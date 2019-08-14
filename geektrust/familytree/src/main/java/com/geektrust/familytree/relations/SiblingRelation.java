package com.geektrust.familytree.relations;

import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.repository.RelativesRepository;

import java.util.ArrayList;
import java.util.List;

public class SiblingRelation implements Relation {

    private RelativesRepository<EDescendant, String> descendantsRepository;

    public SiblingRelation(RelativesRepository<EDescendant, String> descendantsRepository) {
        this.descendantsRepository = descendantsRepository;
    }

    @Override
    public List<Person> getRelatives(Person person) {
        List<Person> siblings = new ArrayList<>();
        Person parent = person.getMother();
        if(parent != null) {
            EDescendant eDescendant = descendantsRepository.findOne(parent.getName());
            if(eDescendant != null) {
                for (Person child : eDescendant.getChildren()) {
                    if (!child.equals(person)) {
                        siblings.add(child);
                    }
                }
            }
        }
        return siblings;
    }
}
