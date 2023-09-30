package com.mbbm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableTransactionManagement
@EntityScan(basePackages = {"com.mbbm.app.model.base"})
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.mbbm.app.model.base"})
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
