package starter;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages={"controller","api"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class FoundITClient extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FoundITClient.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FoundITClient.class, args);
	}
}
