package com.sfucmpt276.asn2.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.sfucmpt276.asn2.models.Rectangle;
import com.sfucmpt276.asn2.repositories.RectangleRepository;

@Service
public class RectangleService {

    @Autowired
    private RectangleRepository rectangleRepo;

    public Rectangle create(Rectangle newRectangle, BindingResult result) {
        Optional<Rectangle> dupe = rectangleRepo.findByName(newRectangle.getName());
        if (dupe.isPresent()) {
            result.rejectValue("name", "taken", "That name is already taken!");
        }
        if (result.hasErrors()) {
            return null;
        }
        return rectangleRepo.save(newRectangle);
    }

    public Rectangle update(Long id, Rectangle updatedRectangle, BindingResult result) {
        Optional<Rectangle> existingRectangle = rectangleRepo.findById(id);
        if (!existingRectangle.isPresent()) {
            result.rejectValue("id", "notfound", "Rectangle not found!");
            return null;
        }
        if (result.hasErrors()) {
            return null;
        }
        Rectangle rectangle = existingRectangle.get();
        rectangle.setName(updatedRectangle.getName());
        rectangle.setColor(updatedRectangle.getColor());
        rectangle.setHeight(updatedRectangle.getHeight());
        rectangle.setWidth(updatedRectangle.getWidth());
        return rectangleRepo.save(rectangle);
    }

    public void delete(Long id) {
        rectangleRepo.deleteById(id);
    }

    public Rectangle findById(Long id) {
        Optional<Rectangle> rectangle = rectangleRepo.findById(id);
        if (!rectangle.isPresent()) {
            return null;
        }
        return rectangle.get();
    }

    public Rectangle findByName(String name) {
        Optional<Rectangle> rectangle = rectangleRepo.findByName(name);
        if (!rectangle.isPresent()) {
            return null;
        }
        return rectangle.get();
    }

    public List<Rectangle> findAll() {
        return (List<Rectangle>) rectangleRepo.findAll();
    }
}
