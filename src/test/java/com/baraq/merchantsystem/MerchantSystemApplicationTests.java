package com.baraq.merchantsystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MerchantSystemApplicationTests {

	@Test
	public void runApiTests() throws Exception {
		// Use a process builder to execute Newman command
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("newman", "run", "/Users/saurabhkumar/Repository/merchant-system/src/test/resources/Test-Automation-Project.postman_collection_aaabss.json", "-e", "/Users/saurabhkumar/Repository/merchant-system/src/test/resources/dev.json", "-r", "htmlextra");

		Process process = processBuilder.start();
		int exitCode = process.waitFor();

		InputStream inputStream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		Assertions.assertEquals(0, exitCode, "Tests failed!");
	}
}
