package kebabs;

import kebabs.data.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import kebabs.Ingredient.Type;

@SpringBootApplication
public class KebabCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(KebabCloudApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Ingredient("KBWB", "Kebab w bułce", Type.KIND));
                repo.save(new Ingredient("KBWT", "Kebab w tortilli", Type.KIND));
                repo.save(new Ingredient("KBBX", "Kebab box", Type.KIND));
                repo.save(new Ingredient("BRNA", "Baranina", Type.PROTEIN));
                repo.save(new Ingredient("KRCZ", "Kurczak", Type.PROTEIN));
                repo.save(new Ingredient("SATA", "Sałata", Type.VEGGIES));
                repo.save(new Ingredient("PMDR", "Pomidor", Type.VEGGIES));
                repo.save(new Ingredient("OGRK", "Ogórek", Type.VEGGIES));
                repo.save(new Ingredient("KPUT", "Kapusta", Type.VEGGIES));
                repo.save(new Ingredient("KRBW", "Karbowane", Type.CHIPS));
                repo.save(new Ingredient("PRST", "Proste", Type.CHIPS));
                repo.save(new Ingredient("CZNK", "Czosnkowy", Type.SAUCE));
                repo.save(new Ingredient("OSTR", "Ostry", Type.SAUCE));
                repo.save(new Ingredient("LAGO", "Łagodny", Type.SAUCE));
            }
        };
    }

}
