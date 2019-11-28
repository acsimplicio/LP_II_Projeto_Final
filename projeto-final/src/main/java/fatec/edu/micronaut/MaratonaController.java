package fatec.edu.micronaut;

import java.util.List;
import java.util.Map;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;


@Controller("/maratona")
public class MaratonaController {
	Validator validator = new Validator();
	
	@Get("/{?search*}")
    public List<Execution> getMaratona(Map<String, String> search) {
        return validator.searchExecutions(search);
    }
    
    @Post("/")
    public Map<String, String> postMaratona(@Body Map<String, String> body) {
    	Execution ex = new Execution(body.get("filename"), body.get("sourcecode"), body.get("problem"));
    	
    	validator.createPythonFile(ex);
    	
    	String result = validator.validateResult(ex);
    	
    	validator.saveTry(ex, result);
    	
    	return ex.mapResponse();
    }
}