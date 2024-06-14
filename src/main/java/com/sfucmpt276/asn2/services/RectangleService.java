package com.sfucmpt276.asn2.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sfucmpt276.asn2.models.Rectangle;
import com.sfucmpt276.asn2.repositories.RectangleRepository;
import org.springframework.validation.BindingResult;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RectangleService {

    @Autowired
    private RectangleRepository rectangleRepository;

    public List<Rectangle> findAll() {
        return StreamSupport.stream(rectangleRepository.findAll().spliterator(), false)
                            .collect(Collectors.toList());
    }

    public Rectangle create(Rectangle newRectangle, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            return null;
        }
        System.out.println("Saving Rectangle: " + newRectangle);
        return rectangleRepository.save(newRectangle);
    }

    public Rectangle findById(Long id) {
        return rectangleRepository.findById(id).orElse(null);
    }

    public Rectangle update(Long id, Rectangle updatedRectangle, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        updatedRectangle.setId(id);
        return rectangleRepository.save(updatedRectangle);
    }

    public void delete(Long id) {
        rectangleRepository.deleteById(id);
    }
}
