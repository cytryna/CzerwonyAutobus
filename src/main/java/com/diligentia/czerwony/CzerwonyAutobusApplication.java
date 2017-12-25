package com.diligentia.czerwony;

import javax.sql.DataSource;

import com.diligentia.czerwony.model.ExpenceCategory;
import com.diligentia.czerwony.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.diligentia.czerwony.repository.SystemRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@EnableJpaRepositories("com.diligentia.czerwony.repository")
@EntityScan("com.diligentia.czerwony.model")
public class CzerwonyAutobusApplication implements CommandLineRunner {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    SystemRepository systemRepository;

    public static void main(String[] args) {
        SpringApplication.run(CzerwonyAutobusApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createTestData();
        Iterable<Expense> systemlist = systemRepository.findAll();
        System.out.println("radek");
        for (Expense systemmodel : systemlist) {
            System.out.println("Here is a system: " + systemmodel.toString());
        }
    }

    private void createTestData() {
        Expense expense;

        expense = new Expense();
        expense.setName("Apteka");
        expense.setDate(LocalDate.now());
        expense.setAmount(BigDecimal.TEN);
        expense.setExpenceCategory(ExpenceCategory.HEALTH);
        systemRepository.save(expense);

        expense = new Expense();
        expense.setName("Biedronka");
        expense.setDate(LocalDate.now());
        expense.setAmount(BigDecimal.valueOf(13.9));
        expense.setExpenceCategory(ExpenceCategory.FOOD);
        systemRepository.save(expense);
    }
}
