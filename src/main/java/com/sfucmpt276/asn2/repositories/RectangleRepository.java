package com.sfucmpt276.asn2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sfucmpt276.asn2.models.Rectangle;

@Repository
public interface RectangleRepository extends JpaRepository<Rectangle, Long> {
}
