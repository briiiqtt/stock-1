package com.briiiqtt.stockking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
	@SpringBootApplication
	내부에 @ComponentScan을 포함함.
	com.example.didem 패키지 이하에 있는 @Component, @Service, @Controller, @Repository 클래스들을 자동으로 스캔
*/

@Slf4j
public class StockkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockkingApplication.class, args);
	}

}
