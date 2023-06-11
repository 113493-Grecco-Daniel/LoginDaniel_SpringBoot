package ar.edu.utn.frc.tup.lciii.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app") //con el configuration de app busca //esta en resource app...
@Data
public class SpringDocConfig {

    private String url="http://localhost:8082";
    private String devName="Daniel";
    private String devEmail="anielazo@Danielazo.com";

    @Bean
    public OpenAPI openApi (

            @Value("@project.name@") String appName,
            @Value("@project.description@") String appDescription,
            @Value("@project.version@") String appVersion){


        Info info = new Info()
                .title("@project.name@")
                .version("@project.version@")
                .description("@project.description@")
                .contact(
                        new Contact()
                                .name(devName)
                                .email(devEmail));

        Server server = new Server()
                .url(url)
                .description(appDescription);

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .addServersItem(server);
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper){
        return new ModelResolver(objectMapper);
    }


}
