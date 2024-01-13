package com.mbbm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);

		/* ------------- GOOGLE DRIVE ----------------*/
		// DriveQuickStart driveQuickStart = new DriveQuickStart();
		// driveQuickStart.start();

		/* ------------- SELENIUM ----------------*/
		// SeleniumWebDriver seleniumWebDriver = new SeleniumWebDriver();
		// WebDriver webDriver = seleniumWebDriver.startSeleniumWebDriver();
		// TaagerSeleniumLogin taagerSeleniumLogin = new TaagerSeleniumLogin();
		// if(webDriver != null){
		// 	taagerSeleniumLogin.login(webDriver);
		// }

	}
}
