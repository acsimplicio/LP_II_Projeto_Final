package fatec.edu.micronaut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Validator {
	
	FileManager fileManager = new FileManager();
	
	// Valida a execução do código postado
	public String validateExecution (Execution ex) {
		ArrayList<String> results = new ArrayList<String>();
		int numberOfInputs = getNumberOfInputs(ex.getProblem());
		
		for (int inputNumber = 1; inputNumber <= numberOfInputs; inputNumber++) {
			results.add(validateOutput(ex, inputNumber));
		}
		
		if (results.contains("FAIL")) return "FAIL";
		return "SUCCESS";
	}
	
	// Retorna o número de inputs que cada problema necessita
	public int getNumberOfInputs (String problem) {
		if (problem.equals("A")) return 3;
		if (problem.equals("E")) return 2;
		return 1;
	}
	
	// Executa o script python do problema com o input específico para ele e valida se a saída é igual a esperada
	public String validateOutput (Execution ex, int inputNumber) {
		String expectedOutput = fileManager.getExpectedOutput(ex.getProblem(), inputNumber);
		String inputFile = "input" + ex.getProblem() + "_" + inputNumber + ".txt";
		
		try {
			String command = "cmd.exe /c python assets/codes/"+ ex.getFilename() +" < assets/inputs/" + inputFile;
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
	
}
