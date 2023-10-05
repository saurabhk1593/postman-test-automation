package com.baraq.merchantsystem;

import com.baraq.merchantsystem.controller.Merchant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MerchantSystemApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MerchantSystemApplication.class, args);
	}

}
