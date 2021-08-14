package kebabs.web;

import kebabs.Ingredient;
import kebabs.Ingredient.Type;
import kebabs.Kebab;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignKebabController {

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("KBWB", "Kebab w bułce", Ingredient.Type.KIND),
                new Ingredient("KBWT", "Kebab w tortilli", Ingredient.Type.KIND),
                new Ingredient("KBBX", "Kebab box", Ingredient.Type.KIND),
                new Ingredient("BRNA", "Baranina", Ingredient.Type.PROTEIN),
                new Ingredient("KRCZ", "Kurczak", Ingredient.Type.PROTEIN),
                new Ingredient("SATA", "Sałata", Ingredient.Type.VEGGIES),
                new Ingredient("PMDR", "Pomidor", Ingredient.Type.VEGGIES),
                new Ingredient("OGRK", "Ogórek", Ingredient.Type.VEGGIES),
                new Ingredient("KPUT", "Kapusta", Ingredient.Type.VEGGIES),
                new Ingredient("KRBW", "Karbowane", Ingredient.Type.CHIPS),
                new Ingredient("PRST", "Proste", Ingredient.Type.CHIPS),
                new Ingredient("CZNK", "Czosnkowy", Ingredient.Type.SAUCE),
                new Ingredient("OSTR", "Ostry", Ingredient.Type.SAUCE),
                new Ingredient("LAGO", "Łagodny", Ingredient.Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model){
        model.addAttribute("kebab", new Kebab());
        return "design";
    }
    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Kebab kebab, Errors errors){
        if(errors.hasErrors()){
            return "design";
        }
        log.info("Przetwarzanie projektu kebaba:"  + kebab);
        return "redirect:/orders/current";
    }
}
