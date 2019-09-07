package cn.edu.nefu.lib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAutoConfiguration
//一般@SpringBootApplication
/**
 * war包
 *
 */
//@SpringBootApplication
//public class MainApplication extends SpringBootServletInitializer{
//	public static void main(String[] args) {
//		SpringApplication.run(MainApplication.class, args);
//	}
//	
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		//return super.configure(builder);
//		return builder.sources(MainApplication.class);
//	}
//}

/**
 * jar包
 */
@EnableTransactionManagement
@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}