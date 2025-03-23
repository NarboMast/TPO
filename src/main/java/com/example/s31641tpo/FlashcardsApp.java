package com.example.s31641tpo;

import com.example.s31641tpo.controller.FlashcardsController;
import com.example.s31641tpo.services.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class FlashcardsApp {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(FlashcardsApp.class, args);
		context.getBean(FileService.class).readFile();
		context.getBean(FlashcardsController.class).start();
	}

}
