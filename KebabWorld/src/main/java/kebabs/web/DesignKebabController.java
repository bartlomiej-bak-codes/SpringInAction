package kebabs.web;

import kebabs.Ingredient;
import kebabs.Ingredient.Type;
import kebabs.Kebab;
import kebabs.Order;
import kebabs.User;
import kebabs.data.IngredientRepository;
import kebabs.data.KebabRepository;
import kebabs.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignKebabController {

    private final IngredientRepository ingredientRepository;
    private final KebabRepository kebabRepository;
    private UserRepository userRepository;

    @Autowired
    public DesignKebabController(IngredientRepository ingredientRepository, KebabRepository kebabRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.kebabRepository = kebabRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "kebab")
    public Kebab design() {
        return new Kebab();
    }


    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        log.info("  --- Designing taco");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }


    @PostMapping

    public String processDesign(@Valid Kebab kebab, Errors errors, @ModelAttribute Order order, Model model) {
        if (errors.hasErrors()) {
            showDesignForm(model);
            return "design";
        }

        Kebab saved = kebabRepository.save(kebab);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
