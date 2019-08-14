package com.geektrust.familytree.relations;

import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.exception.NoSuchRelationshipException;
import com.geektrust.familytree.repository.RelativesRepository;

public class RelationFactory {
    public static Relation getRelationship(String relationshipName,
                                           RelativesRepository<EDescendant, String> descendantsRepository,
                                           RelativesRepository<EPartner, String> partnersRepository)
            throws NoSuchRelationshipException {

        switch (relationshipName) {
            case "Paternal-Uncle":
                return new PiblingRelation(true, true,
                        new SiblingRelation(descendantsRepository), partnersRepository);
            case "Maternal-Uncle":
                return new PiblingRelation(false, true,
                        new SiblingRelation(descendantsRepository), partnersRepository);
            case "Paternal-Aunt":
                return new PiblingRelation(true, false,
                        new SiblingRelation(descendantsRepository), partnersRepository);
            case "Maternal-Aunt":
                return new PiblingRelation(false, false,
                        new SiblingRelation(descendantsRepository), partnersRepository);
            case "Sister-In-Law":
                return new InLawRelation(false, partnersRepository, new SiblingRelation(descendantsRepository));
            case "Brother-In-Law":
                return new InLawRelation(true, partnersRepository, new SiblingRelation(descendantsRepository));
            case "Son":
                return new ChildRelation(true, partnersRepository, descendantsRepository);
            case "Daughter":
                return new ChildRelation(false, partnersRepository, descendantsRepository);
            case "Siblings":
                return new SiblingRelation(descendantsRepository);
            default:
                throw new NoSuchRelationshipException(relationshipName + " relationship is not supported.");
        }
    }
}
