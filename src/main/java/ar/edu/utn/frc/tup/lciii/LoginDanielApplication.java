package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.configs.SpringDocConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SpringDocConfig.class)
public class LoginDanielApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginDanielApplication.class, args);
	}

}
