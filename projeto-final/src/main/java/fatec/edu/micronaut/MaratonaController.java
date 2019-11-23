package fatec.edu.micronaut;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Map;

@Controller("/maratona")
public class MaratonaController {
	
	public void createPythonFile (String sourcecode, String filename) {
		String decodedSourceCode = new String(Base64.getDecoder().decode(sourcecode));
		
		try {
			FileWriter pythonFile = new FileWriter("assets\\codes\\" + filename);
			PrintWriter pythonFileWriter = new PrintWriter(pythonFile);
			
			pythonFileWriter.printf(decodedSourceCode);
			pythonFileWriter.close();
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getExpectedOutput(String problem) {
		try {
			FileReader expectedFileReader = new FileReader("assets\\expected_results\\result" + problem + ".txt");
			BufferedReader expectedFileBuffer = new BufferedReader(expectedFileReader);
			
			String expectedResult = expectedFileBuffer.readLine();
			
			expectedFileBuffer.close();
			
			return expectedResult;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String validateResult (String problem, String filename) {
		String expectedOutput = getExpectedOutput(problem);
		
		try {
			String command = "cmd.exe /c python assets/codes/"+ filename +" < assets/inputs/input"+ problem +".txt";
			Process p = Runtime.getRuntime().exec(command);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String pythonOutput = in.readLine();
			
			if (pythonOutput == expectedOutput) {
				return "SUCCESS";
			}
			return "FAIL";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Get("/")
    public String index() {
        return "Hello Controller!";
    }
    
    @Post("/")
    public String postMaratona(@Body Map<String, String> body) {
    	String sourcecode = body.get("sourcecode");
    	String filename = body.get("filename");
    	String problem = body.get("problem");
    	
    	createPythonFile(sourcecode, filename);
    	
    	String result = validateResult(problem, filename);
    	
    	return result;
    }
}