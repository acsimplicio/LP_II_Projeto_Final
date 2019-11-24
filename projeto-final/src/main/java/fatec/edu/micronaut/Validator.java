package fatec.edu.micronaut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Validator {
	
	public ArrayList<Map<String, String>> tries = new ArrayList<Map<String, String>>();
	private int tryId = 1;

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
			
			if (pythonOutput.equals(expectedOutput)) {
				return "SUCCESS";
			}
			return "FAIL";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveTry (String filename, String problem, String result, String sourcecode) {
		Map<String, String> newItem = new HashMap<>();

		newItem.put("sourcecode", sourcecode);
		newItem.put("filename", filename);
		newItem.put("status", result);
		newItem.put("problem", problem);
		newItem.put("datetime", java.time.LocalDateTime.now().toString());
		newItem.put("id", String.valueOf(this.tryId));
		
		this.tries.add(newItem);
		this.tryId++;
	}
	
}
