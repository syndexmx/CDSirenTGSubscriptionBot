package syndexmx.github.com.tgsiren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.Thread.sleep;
import static syndexmx.github.com.tgsiren.utils.oldHtmlBlockExtractor.extractTaggedBlock;

@SpringBootApplication
public class TgsirenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgsirenApplication.class, args);
	}

}
