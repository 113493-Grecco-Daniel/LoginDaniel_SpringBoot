package ar.edu.utn.frc.tup.lciii.configs;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Qualifier("mergerMapper")
    public ModelMapper mergerMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return  objectMapper;
    }
}
