package com.geektrust.familytree.relations;


import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.repository.RelativesRepository;

import java.util.ArrayList;
import java.util.List;

public class ChildRelation implements Relation {

    private boolean isMale;
    private RelativesRepository<EPartner, String> partnersRepository;
    private RelativesRepository<EDescendant, String> descendantsRepository;

    public ChildRelation(boolean isMale, RelativesRepository<EPartner, String> partnersRepository,
                       RelativesRepository<EDescendant, String> descendantsRepository) {
        this.isMale = isMale;
        this.partnersRepository = partnersRepository;
        this.descendantsRepository = descendantsRepository;
    }

    @Override
    public List<Person> getRelatives(Person person) {
        List<Person> children = new ArrayList<>();
        if(person.isMale()) {
            EPartner ePartner = partnersRepository.findOne(person.getName());
            if(ePartner != null)
                person = ePartner.getPartner();
        }
        EDescendant eDescendant = descendantsRepository.findOne(person.getName());
        if(eDescendant != null) {
            for (Person child : eDescendant.getChildren()) {
                if (child.isMale() == isMale) {
                    children.add(child);
                }
            }
        }
        return children;
    }
}
