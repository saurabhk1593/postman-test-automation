package com.baraq.merchantsystem;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootTest
public class MerchantSystemApplicationTests {

//	@Container
//	public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest")
//			.withDatabaseName("mytestdb")
//			.withUsername("dbuser")
//			.withPassword("dbpassword");
//
//	@BeforeAll
//	public void setup() {
//		mysqlContainer.start();
//	}
//
//	@Test
//	public void testFindUserByUsername() {
//		// Your test logic here
//		User user = new User();
//		user.setUsername("user1");
//		userRepository.save(user);
//
//		User savedUser = userRepository.findByUsername("user1");
//		assertNotNull(savedUser);
//		assertEquals("user1", savedUser.getUsername());
//	}
//	private static ConfigurableApplicationContext context;
//	private static String serverUrl;  // Store the server's base URL
//
//	@BeforeClass
//	public static void setUp() {
//		// Start your Spring Boot application
//		context = SpringApplication.run(MerchantSystemApplicationTests.class);
//
//		// Get the server's base URL
//		serverUrl = "http://localhost:" + context.getEnvironment().getProperty("server.port");
//		System.out.println("this is your server url" + serverUrl);
//	}
//
//	@Test
//	public void runApiTests() throws Exception {
//		// Use a process builder to execute Newman command
//		ProcessBuilder processBuilder = new ProcessBuilder();
//		processBuilder.command("newman", "run", "/Users/saurabhkumar/personal/repository/postman-test-automation/src/test/resources/Test-Automation-Project.postman_collection.json", "-e", "/Users/saurabhkumar/personal/repository/postman-test-automation/src/test/resources/development.postman_environment.json", "-r", "html");
//
//		Process process = processBuilder.start();
//		int exitCode = process.waitFor();
//
//		InputStream inputStream = process.getInputStream();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//		String line;
//		while ((line = reader.readLine()) != null) {
//			System.out.println(line);
//		}
//
//		Assertions.assertEquals(0, exitCode, "Tests failed!");
//	}
//
//	@AfterClass
//	public static void tearDown() {
//		context.close();
//	}
}
