package fatec.edu.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class MaratonaControllerTest {

	 @Inject
	 @Client("/")
	 RxHttpClient client;

	 @Test
	 public void testIndex() throws Exception {
		 HttpRequest<String> request = HttpRequest.GET("/maratona");
	     String body = client.toBlocking().retrieve(request);
	        
	     assertNotNull(body);
	     assertEquals("Hello Controller!",body);
	 }
}
