package com.geektrust.familytree.repository;


import java.io.Serializable;
import java.util.List;

/**
 * Repository interface to provide basic methods for maintaining
 * relationships that are created at runtime.
 *
 * @param <P> the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 */
public interface RelativesRepository<P, ID extends Serializable> {
    P save(P p);
    P findOne(ID id);
    List<P> findAll(P p);
}
