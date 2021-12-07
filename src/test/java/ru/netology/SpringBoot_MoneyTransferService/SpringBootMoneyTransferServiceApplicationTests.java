package ru.netology.SpringBoot_MoneyTransferService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.netology.SpringBoot_MoneyTransferService.controller.MoneyTransferController;
import ru.netology.SpringBoot_MoneyTransferService.model.TransferTransaction;
/*
@SpringBootTest
@AutoConfigureMockMvc
class SpringBootMoneyTransferServiceApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private MoneyTransferController controller;

	@Test
	void testMoneyTransferController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/transfer").content("{\"cardFromNumber\":\"4276844029881001\",\"cardToNumber\":\"4276844029881002\",\"cardFromCVV\":\"001\",\"cardFromValidTill\":\"11/25\",\"amount\":{\"currency\":\"RUR\",\"value\":50000}}"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.content().json("0000"))
				.andExpect(MockMvcResultMatchers.status().isOk());
				//.andExpect(MockMvcResultMatchers.content().json("{\"cardFromNumber\":\"4276844029881001\",\"cardToNumber\":\"4276844029881002\",\"cardFromCVV\":\"001\",\"cardFromValidTill\":\"11/25\",\"amount\":{\"currency\":\"RUR\",\"value\":50000}}"));
	}

}
*/