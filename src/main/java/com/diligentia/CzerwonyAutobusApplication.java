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
        recipeRepository.deleteAll();
        createTestData();
        Iterable<Recipe> systemlist = recipeRepository.findAll();
        for (Recipe systemmodel : systemlist) {
            System.out.println("Here is a meal: " + systemmodel.toString());
        }
    }

    private void createTestData() {
        Recipe recipe;

        recipe = new Recipe();
        recipe.setName("Leczo z ciecierzycą");
        recipe.setDescription("http://www.agamasmaka.pl/2016/10/leczo-prosty-zdrowy-obiad-na-dwa-dni.html");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Ryż z jabłkami");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Placuszki z dyni");
        recipe.setDescription("http://skutecznie.tv/2013/07/placki-z-cukinii/");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Pulpeciki");
        recipe.setDescription("http://www.evernote.com/l/ASDPRz0GMT9Em6ag8Mh1CmA5dBs2wPn3R7Q/");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Mielone z piekarnika");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Risotto z cukinią");
        recipe.setDescription("http://www.agamasmaka.pl/2017/01/risotto-cukinia-proste-zdrowe-danie-jednogarnkowe.html");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Burgery z kaszy jaglanej");
        recipe.setDescription("http://www.agamasmaka.pl/2015/01/weganski-bezglutenowy-jaglany-burger.html");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Burgery z kaszy jaglanej");
        recipe.setDescription("http://www.agamasmaka.pl/2015/01/weganski-bezglutenowy-jaglany-burger.html");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Indyk z grilla kasza buraczki");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Udka z piekarnika, warzywa z patelni surówka");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Szybkie gołąbki");
        recipe.setDescription("https://www.olgasmile.com/szybkie-golabki.html");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

        recipe = new Recipe();
        recipe.setName("Pancake");
        recipe.setDescription("http://www.pannaannabiega.pl/przepisy/platki-owsiane-wcale-musza-zle-smakowac-muffiny-ciastka-pancakes/");
        recipe.setCreationDate(LocalDate.now());

        recipeRepository.save(recipe);
        recipe = new Recipe();
        recipe.setName("Makaron z szpinakeim");
        recipe.setCreationDate(LocalDate.now());
        recipeRepository.save(recipe);

    }
}
