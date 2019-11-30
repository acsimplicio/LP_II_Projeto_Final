package fatec.edu.micronaut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

public class FileManager {

	// Realiza a descriptografia do sourcecode e o escreve em um arquivo .py na pasta \codes
	public void createPythonFile (Execution ex) {
		String decodedSourceCode = new String(Base64.getDecoder().decode(ex.getSourcecode()));
		
		try {
			FileWriter pythonFile = new FileWriter("assets\\codes\\" + ex.getFilename());
			PrintWriter pythonFileWriter = new PrintWriter(pythonFile);
			
			pythonFileWriter.printf(decodedSourceCode);
			pythonFileWriter.close();
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Abre o arquivo com a saída esperada para o problema específico e retorna para o validador
	public String getExpectedOutput(String problem, int forInput) {
		String outputFile = "result" + problem + "_" + forInput + ".txt";
		
		try {
			FileReader expectedFileReader = new FileReader("assets\\expected_results\\" + outputFile);
			BufferedReader expectedFileBuffer = new BufferedReader(expectedFileReader);
			
			String expectedResult = expectedFileBuffer.readLine();
			
			expectedFileBuffer.close();
			
			return expectedResult;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
