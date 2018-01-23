package com.diligentia;

import javax.sql.DataSource;

import com.diligentia.model.Meal;
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
        Iterable<Meal> systemlist = recipeRepository.findAll();
        for (Meal systemmodel : systemlist) {
            System.out.println("Here is a meal: " + systemmodel.toString());
        }
    }

    private void createTestData() {
        Meal meal;

        meal = new Meal();
        meal.setName("Leczo z ciecierzycą");
        meal.setDescription("http://www.agamasmaka.pl/2016/10/leczo-prosty-zdrowy-obiad-na-dwa-dni.html");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Ryż z jabłkami");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Placuszki z dyni");
        meal.setDescription("http://skutecznie.tv/2013/07/placki-z-cukinii/");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Pulpeciki");
        meal.setDescription("http://www.evernote.com/l/ASDPRz0GMT9Em6ag8Mh1CmA5dBs2wPn3R7Q/");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Mielone z piekarnika");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Risotto z cukinią");
        meal.setDescription("http://www.agamasmaka.pl/2017/01/risotto-cukinia-proste-zdrowe-danie-jednogarnkowe.html");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Burgery z kaszy jaglanej");
        meal.setDescription("http://www.agamasmaka.pl/2015/01/weganski-bezglutenowy-jaglany-burger.html");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Burgery z kaszy jaglanej");
        meal.setDescription("http://www.agamasmaka.pl/2015/01/weganski-bezglutenowy-jaglany-burger.html");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Indyk z grilla kasza buraczki");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Udka z piekarnika, warzywa z patelni surówka");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Szybkie gołąbki");
        meal.setDescription("https://www.olgasmile.com/szybkie-golabki.html");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

        meal = new Meal();
        meal.setName("Pancake");
        meal.setDescription("http://www.pannaannabiega.pl/przepisy/platki-owsiane-wcale-musza-zle-smakowac-muffiny-ciastka-pancakes/");
        meal.setCreationDate(LocalDate.now());

        recipeRepository.save(meal);
        meal = new Meal();
        meal.setName("Makaron z szpinakeim");
        meal.setCreationDate(LocalDate.now());
        recipeRepository.save(meal);

    }
}
