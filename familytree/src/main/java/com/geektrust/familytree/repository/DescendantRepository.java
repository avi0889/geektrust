package com.geektrust.familytree.repository;

import com.geektrust.familytree.domain.EDescendant;

import java.util.List;
import java.util.Map;


public class DescendantRepository implements RelativesRepository<EDescendant, String> {

    private Map<String, EDescendant> repository;

    public DescendantRepository(Map<String, EDescendant> repository) {
        this.repository = repository;
    }

    @Override
    public EDescendant save(EDescendant eDescendant) {
        repository.put(eDescendant.getPerson().getName(), eDescendant);
        return repository.get(eDescendant.getPerson().getName());
    }

    @Override
    public EDescendant findOne(String s) {
        return repository.get(s);
    }

    @Override
    public List<EDescendant> findAll(EDescendant eDescendant) {
        throw new UnsupportedOperationException();
    }
}
