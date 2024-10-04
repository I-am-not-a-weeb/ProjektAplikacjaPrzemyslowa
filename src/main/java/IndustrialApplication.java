import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;

import org.springframework.context.annotation.ComponentScan;

@EnableTransactionManagement
@SpringBootApplication
@EnableNeo4jRepositories
@ComponentScan(basePackages = {"Controllers", "Services","Security","neo4j.neo4jMappings", "neo4j.repositories"})
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
