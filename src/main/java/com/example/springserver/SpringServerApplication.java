package com.example.springserver;

import com.example.springserver.file_api.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "com.example.springserver")
@EnableConfigurationProperties({FileUploadProperties.class})
public class SpringServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}
}
