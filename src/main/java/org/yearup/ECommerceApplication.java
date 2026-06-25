package org.yearup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication
{

    public static void main(String[] args) {
        System.setProperty("spring.banner.location", "classpath:banner-grocerystore.txt");
        SpringApplication.run(ECommerceApplication.class, args);
    }

}
