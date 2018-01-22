package com.diligentia;

import javax.sql.DataSource;

import com.diligentia.model.Recipe;
import com.diligentia.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;

//@SpringBootApplication
//@EnableJpaRepositories("com.diligentia.repository")
//@EntityScan("com.diligentia.model")
public class CzerwonyAutobusApplication implements CommandLineRunner {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    RecipeRepository recipeRepository;

    public static void main(String[] args) {
        SpringApplication.run(CzerwonyAutobusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createTestData();
        Iterable<Recipe> systemlist = recipeRepository.findAll();
        System.out.println("radek");
        for (Recipe systemmodel : systemlist) {
            System.out.println("Here is a system: " + systemmodel.toString());
        }
    }

    private void createTestData() {
        Recipe recipe;

        recipe = new Recipe();
        recipe.setName("Apteka");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Biedronka");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);
    }
}
