package com.diligentia.CzerwonyAutobus;

import javax.sql.DataSource;

import com.diligentia.CzerwonyAutobus.model.SystemTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.diligentia.CzerwonyAutobus.repository.SystemRepository;

@SpringBootApplication
@EnableJpaRepositories("com.diligentia.CzerwonyAutobus.repository")
@EntityScan("com.diligentia.CzerwonyAutobus.model")
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
		System.out.println("Our DataSource is = " + dataSource);
		Iterable<SystemTO> systemlist = systemRepository.findAll();
			System.out.println("radek");
		for(SystemTO systemmodel:systemlist){
			System.out.println("Here is a system: " + systemmodel.toString());
		}
	}
}
