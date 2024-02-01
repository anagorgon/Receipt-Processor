package com.example.receiptprocessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ReceiptProcessorApplication implements CommandLineRunner {

	private static Receipt parseJsonInput(String jsonInput) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode rootNode = objectMapper.readTree(jsonInput);

			List<Product> products = objectMapper.reader()
					.forType(objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class))
					.readValue(rootNode);

			Receipt receipt = new Receipt();
			receipt.setProducts(products);
			return receipt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
			SpringApplication.run(ReceiptProcessorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String apiUrl = "https://interview-task-api.mca.dev/qr-scanner-codes/alpha-qr-gFpwhsQ8fkY1";

		ReceiptApiClient receiptApiClient = new ReceiptApiClient();
		try {
			String jsonInput = receiptApiClient.fetchReceiptDetails(apiUrl);
			Receipt receipt = parseJsonInput(jsonInput);

//			receipt.setProducts(List.of(
//					new Product("Tomato",true,30.5,150, "The species originated in western South America and Central America. The Nahuatl (the language used by the Aztecs) word tomatl gave rise to the Spanish word tomate, from which the English word tomato derived."),
//					new Product("Apple",true,14.5,0,"Apple trees are cultivated worldwide and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today."),
//					new Product("Banana",false,22.0,0,"A banana is an elongated, edible fruit – botanically a berry – produced by several kinds of large herbaceous flowering plants in the genus Musa. In some countries, bananas used for cooking may be called plantains, distinguishing them from dessert bananas.")));
			ReceiptProcessor receiptProcessor = new ReceiptProcessor(receipt);

			receiptProcessor.groupAndSortProducts();
			receiptProcessor.calculateTotals();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
