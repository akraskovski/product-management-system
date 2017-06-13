package by.kraskovski.pms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public abstract class Application implements CommandLineRunner {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
