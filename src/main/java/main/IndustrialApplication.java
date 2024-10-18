package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

//@EnableTransactionManagement
@SpringBootApplication(scanBasePackages={"Controllers", "Repos", "Services"})
//@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"Repos"})
@ComponentScan(basePackages = {"Controllers", "Repos", "Services","Serializers"})
@EntityScan(basePackages = {"Database"})
public class IndustrialApplication {
    public static void main(String[] args) {
        SpringApplication.run(IndustrialApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
}
