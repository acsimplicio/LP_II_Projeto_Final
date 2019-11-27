package fatec.edu.micronaut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;

public class Validator {
	
	public ArrayList<Execution> tries = new ArrayList<Execution>();
	private int tryId = 1;

	// Realiza a descriptografia do sourcecode e o escreve em um arquivo .py na pasta \codes
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
	
	// Abre o arquivo com a sa�da esperada para o problema espec�fico e retorna para o validador
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
	
	// Executa o script python do problema com o input espec�fico para ele e valida se a sa�da � igual a esperada
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
	
	// Salva a tentativa num Array para que possa ser feita a busca posteriormente
	public void saveTry (Execution ex, String result) {
		ex.setStatus(result);
		ex.setId(this.tryId);
	
		this.tries.add(ex);
		this.tryId++;
	}
	
}
