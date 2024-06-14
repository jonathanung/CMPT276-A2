package com.sfucmpt276.asn2.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sfucmpt276.asn2.models.Rectangle;

@Repository
public interface RectangleRepository extends CrudRepository<Rectangle, Long> {
    
    Optional<Rectangle> findByName(String name);
    
}
