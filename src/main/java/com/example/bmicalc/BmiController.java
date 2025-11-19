package com.example.bmicalc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BmiController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam double weightKg,
                            @RequestParam double heightCm,
                            Model model) {

        double heightM = heightCm / 100.0;
        double bmi = weightKg / (heightM * heightM);

        String category = categorize(bmi);

        model.addAttribute("bmi", String.format("%.2f", bmi));
        model.addAttribute("category", category);

        return "result";
    }

    private String categorize(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal Weight";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }
}
