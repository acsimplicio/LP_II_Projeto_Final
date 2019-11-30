package fatec.edu.micronaut;

import java.util.List;
import java.util.Map;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;


@Controller("/maratona")
public class MaratonaController {
	
	FileManager fileManager = new FileManager();
	Validator validator = new Validator();
	Log logs = new Log();
	
	@Get("/{?searchParameter*}")
    public List<Execution> getMaratona(Map<String, String> searchParameter) {
        return logs.searchLog(searchParameter);
    }
    
    @Post("/")
    public Map<String, String> postMaratona(@Body Map<String, String> body) {
    	Execution ex = new Execution(body.get("filename"), body.get("sourcecode"), body.get("problem"));
    	
    	fileManager.createPythonFile(ex);
    	
    	String result = validator.validateExecution(ex);
    	
    	logs.saveLog(ex, result);
    	
    	return ex.mapResponse();
    }
}