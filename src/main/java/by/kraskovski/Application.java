package by.kraskovski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public abstract class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
