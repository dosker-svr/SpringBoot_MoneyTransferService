package ru.netology.SpringBoot_MoneyTransferService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.netology.SpringBoot_MoneyTransferService.repository.CardsRepository;

@SpringBootApplication
public class SpringBootMoneyTransferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMoneyTransferServiceApplication.class, args);
	}

}
