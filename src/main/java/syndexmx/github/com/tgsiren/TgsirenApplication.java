package syndexmx.github.com.tgsiren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import static java.lang.Thread.sleep;

@SpringBootApplication
@EnableScheduling
public class TgsirenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgsirenApplication.class, args);
	}

}
