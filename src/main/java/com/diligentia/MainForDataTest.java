package com.diligentia;

import javax.sql.DataSource;

import com.diligentia.model.Meal;
import com.diligentia.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

//@SpringBootApplication
//@EnableJpaRepositories("com.diligentia.repository")
//@EntityScan("com.diligentia.model")
public class MainForDataTest implements CommandLineRunner {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    MealRepository mealRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainForDataTest.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mealRepository.deleteAll();
        createTestData();
        Iterable<Meal> systemlist = mealRepository.findAll();
        for (Meal systemmodel : systemlist) {
            System.out.println("Here is a meal: " + systemmodel.toString());
        }
    }

    private void createTestData() {
        Meal meal;

        meal = new Meal();
        meal.setName("Leczo z ciecierzycą");
        meal.setDescription("http://www.agamasmaka.pl/2016/10/leczo-prosty-zdrowy-obiad-na-dwa-dni.html");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Ryż z jabłkami");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Placuszki z dyni");
        meal.setDescription("http://skutecznie.tv/2013/07/placki-z-cukinii/");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Pulpeciki");
        meal.setDescription("http://www.evernote.com/l/ASDPRz0GMT9Em6ag8Mh1CmA5dBs2wPn3R7Q/");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Mielone z piekarnika");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Risotto z cukinią");
        meal.setDescription("http://www.agamasmaka.pl/2017/01/risotto-cukinia-proste-zdrowe-danie-jednogarnkowe.html");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Burgery z kaszy jaglanej");
        meal.setDescription("http://www.agamasmaka.pl/2015/01/weganski-bezglutenowy-jaglany-burger.html");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Burgery z kaszy jaglanej");
        meal.setDescription("http://www.agamasmaka.pl/2015/01/weganski-bezglutenowy-jaglany-burger.html");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Indyk z grilla kasza buraczki");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Udka z piekarnika, warzywa z patelni surówka");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Szybkie gołąbki");
        meal.setDescription("https://www.olgasmile.com/szybkie-golabki.html");
        mealRepository.save(meal);

        meal = new Meal();
        meal.setName("Pancake");
        meal.setDescription("http://www.pannaannabiega.pl/przepisy/platki-owsiane-wcale-musza-zle-smakowac-muffiny-ciastka-pancakes/");

        mealRepository.save(meal);
        meal = new Meal();
        meal.setName("Makaron z szpinakeim");
        mealRepository.save(meal);

    }
}
