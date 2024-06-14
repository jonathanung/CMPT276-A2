package com.sfucmpt276.asn2.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sfucmpt276.asn2.models.Rectangle;
import com.sfucmpt276.asn2.services.RectangleService;

@Controller
@RequestMapping("/rectangles")
public class RectangleController {

    @Autowired
    private RectangleService rectangleService;

    @GetMapping("")
    public String index(Model model) {
        List<Rectangle> rectangles = rectangleService.findAll();
        System.out.println("Retrieved Rectangles: " + rectangles); // Debug statement
        model.addAttribute("rectangles", rectangles);
        model.addAttribute("newRectangle", new Rectangle());
        return "index";
    }

    @PostMapping("/create")
    public String createRectangle(@Valid @ModelAttribute("newRectangle") Rectangle newRectangle, 
            BindingResult result, Model model) {
        
        System.out.println("Creating Rectangle: " + newRectangle);
        
        if (result.hasErrors()) {
            List<Rectangle> rectangles = rectangleService.findAll();
            model.addAttribute("rectangles", rectangles);
            return "index";
        }

        rectangleService.create(newRectangle, result);
        return "redirect:/rectangles";
    }

    @GetMapping("/{id}")
    public String viewRectangle(@PathVariable Long id, Model model) {
        Rectangle rectangle = rectangleService.findById(id);
        if (rectangle == null) {
            return "redirect:/rectangles";
        }
        model.addAttribute("rectangle", rectangle);
        return "rectangle";
    }

    @PostMapping("/update/{id}")
    public String updateRectangle(@PathVariable Long id, @Valid @ModelAttribute("rectangle") Rectangle updatedRectangle, 
            BindingResult result, Model model) {
        
        if(result.hasErrors()) {
            model.addAttribute("rectangle", rectangleService.findById(id));
            return "rectangle";
        }

        rectangleService.update(id, updatedRectangle, result);
        return "rectangle";
    }
    @GetMapping("/delete/{id}")
    public String deleteRectangle(@PathVariable Long id) {
        rectangleService.delete(id);
        return "redirect:/rectangles";
    }

}
