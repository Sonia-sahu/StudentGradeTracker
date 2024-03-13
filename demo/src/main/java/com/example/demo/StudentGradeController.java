package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping("/grades")
public class StudentGradeController {

    private final ArrayList<Double> grades = new ArrayList<>();

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("grades", grades);
        return "grades-form";
    }

    @PostMapping
    public String processForm(Double grade) {
        grades.add(grade);
        return "redirect:/grades";
    }

    @GetMapping("/result")
    public String showResult(Model model) {
        double average = calculateAvg(grades);
        double highest = calculateHighest(grades);
        double lowest = calculateLowest(grades);

        model.addAttribute("average", average);
        model.addAttribute("highest", highest);
        model.addAttribute("lowest", lowest);

        return "grades-result";
    }

    private double calculateAvg(ArrayList<Double> grades) {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    private double calculateHighest(ArrayList<Double> grades) {
        return grades.isEmpty() ? 0.0 : Collections.max(grades);
    }

    private double calculateLowest(ArrayList<Double> grades) {
        return grades.isEmpty() ? 0.0 : Collections.min(grades);
    }
}
