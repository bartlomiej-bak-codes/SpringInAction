package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "wheat", Type.WRAP),
                new Ingredient("COTO", "maize", Type.WRAP),
                new Ingredient("GRBF", "ground beef", Type.PROTEIN),
                new Ingredient("CARN", "pieces of meat", Type.PROTEIN),
                new Ingredient("TMTO", "diced tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "lettuce", Type.VEGGIES),
                new Ingredient("CHED", "cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterey Jack", Type.CHEESE),
                new Ingredient("SLSA", "spicy tomato sauce", Type.SAUCE),
                new Ingredient("SRCR", "cream", Type.SAUCE)
        );


        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors){
        if (errors.hasErrors()){
            return "design";
        }

        //Save the draft of the prepared taco
        //I'll be back here later in chapter 3
        log.info("Processing taco design: " + design);
        return "redirect:/orders/current";
    }

}