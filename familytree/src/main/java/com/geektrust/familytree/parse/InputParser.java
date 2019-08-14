package com.geektrust.familytree.parse;


import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.exception.NoSuchRelationshipException;
import com.geektrust.familytree.relations.Relation;
import com.geektrust.familytree.relations.RelationFactory;
import com.geektrust.familytree.repository.RelativesRepository;
import com.geektrust.familytree.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParser {

    private Map<String, Person> personNameMap;
    private RelativesRepository<EDescendant, String> descendantsRepository;
    private RelativesRepository<EPartner, String> partnersRepository;

    public InputParser(Map<String, Person> map, RelativesRepository<EDescendant, String> descendantsRepository,
                       RelativesRepository<EPartner, String> partnersRepository) {
        this.personNameMap = map;
        this.descendantsRepository = descendantsRepository;
        this.partnersRepository = partnersRepository;
    }

    public void execute(String[] commands) {
        try {
            if (commands[0].equals(Constants.addChildCommand)) {
                String motherName = commands[1];
                String childToBeAddedName = commands[2];
                String childToBeAddedGender = commands[3];
                Person mother = personNameMap.get(motherName);
                if (mother == null)
                    System.out.println(Constants.personNotFoundError);
                else {
                    if (mother.isMale())
                        System.out.println(Constants.childAddedFailureMessage);
                    else {
                        Person child = new Person(childToBeAddedName, childToBeAddedGender, mother);
                        EDescendant eDescendant = descendantsRepository.findOne(motherName);
                        if(eDescendant == null)
                            eDescendant = new EDescendant(mother);
                        eDescendant.addChild(child);
                        descendantsRepository.save(eDescendant);
                        personNameMap.put(childToBeAddedName, child);
                        System.out.println(Constants.childAddedSuccessMessage);
                    }
                }
            } else {
                String name = commands[1];
                Person person = personNameMap.get(name);
                if (person != null) {
                    String relationship = commands[2];
                    Relation relation = RelationFactory.getRelationship(
                            relationship, descendantsRepository, partnersRepository);
                    List<Person> relatives = relation.getRelatives(person);
                    if (relatives.size() == 0)
                        System.out.println(Constants.noRelativeFound);
                    else {
                        for (Person person1 : relatives) {
                            System.out.print(person1.toString() + " ");
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println(Constants.personNotFoundError);
                }
            }
        } catch (NoSuchRelationshipException nsre) {
            System.out.println(nsre.getMessage());
        }
    }
}
