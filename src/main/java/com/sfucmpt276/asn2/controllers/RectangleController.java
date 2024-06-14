package com.sfucmpt276.asn2.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
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
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        List<Rectangle> rectangles = rectangleService.findAll();
        model.addAttribute("rectangles", rectangles);
        model.addAttribute("newRectangle", new Rectangle());
        return "rectangles/index.jsp";
    }

    @PostMapping("/create")
    public String createRectangle(@Valid @ModelAttribute("newRectangle") Rectangle newRectangle, 
            BindingResult result, Model model, HttpSession session) {
        
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }

        Rectangle rectangle = rectangleService.create(newRectangle, result);
        
        if(result.hasErrors()) {
            List<Rectangle> rectangles = rectangleService.findAll();
            model.addAttribute("rectangles", rectangles);
            return "rectangles/index.jsp";
        }
        
        return "redirect:/rectangles";
    }

    @GetMapping("/edit/{id}")
    public String editRectangle(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        Rectangle rectangle = rectangleService.findById(id);
        if (rectangle == null) {
            return "redirect:/rectangles";
        }
        model.addAttribute("rectangle", rectangle);
        return "rectangles/edit.jsp";
    }

    @PostMapping("/update/{id}")
    public String updateRectangle(@PathVariable Long id, @Valid @ModelAttribute("rectangle") Rectangle updatedRectangle, 
            BindingResult result, Model model, HttpSession session) {
        
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }

        Rectangle rectangle = rectangleService.update(id, updatedRectangle, result);
        
        if(result.hasErrors()) {
            return "rectangles/edit.jsp";
        }
        
        return "redirect:/rectangles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRectangle(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        rectangleService.delete(id);
        return "redirect:/rectangles";
    }
}
