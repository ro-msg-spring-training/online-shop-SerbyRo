package ro.msg.learning.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("ro.msg.learning.shop.model")
//@ComponentScan("ro.msg.learning.shop.controller")
//@ComponentScan("ro.msg.learning.shop.repository")
//@ComponentScan("ro.msg.learning.shop.service")
public class OnlineShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

}
