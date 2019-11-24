package fatec.edu.micronaut;

import java.util.HashMap;
import java.util.Map;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;


@Controller("/maratona")
public class MaratonaController {
	Validator validator = new Validator();
	
	@Get("/")
    public String getMaratona() {
        return "Hello Controller!";
    }
    
    @Post("/")
    public Map<String, String> postMaratona(@Body Map<String, String> body) {
    	String sourcecode = body.get("sourcecode");
    	String filename = body.get("filename");
    	String problem = body.get("problem");
    	
    	validator.createPythonFile(sourcecode, filename);
    	
    	String result = validator.validateResult(problem, filename);
    	
    	validator.saveTry(filename, problem, result, sourcecode);
    	
    	Map<String, String> response = new HashMap<>();
    	response.put("filename", filename);
    	response.put("problem", problem);
    	response.put("status", result);
    	
    	return response;
    }
}