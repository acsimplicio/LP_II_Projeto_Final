package fatec.edu.micronaut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class Validator {
	
	private ArrayList<Execution> tries = new ArrayList<Execution>();
	private int tryId = 1;

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
	
	// Executa o script python do problema com o input específico para ele e valida se a saída é igual a esperada
	public String validateResult (Execution ex) {
		String expectedOutput = getExpectedOutput(ex.getProblem());
		
		try {
			String command = "cmd.exe /c python assets/codes/"+ ex.getFilename() +" < assets/inputs/input"+ ex.getProblem() +".txt";
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
		ex.setId(Integer.toString(this.tryId));
	
		this.tries.add(ex);
		this.tryId++;
	}
	
	// Realiza a pesquisa das execuções pelos parâmetros passados
	public List<Execution> searchExecutions(Map<String, String> search){
		
		ArrayList<String> keySearch = new ArrayList<String>(search.keySet());
		ArrayList<Execution> found = new ArrayList<Execution>();
	
    	for(String key:keySearch) {
    		if (key.equals("status")) {
    			for (Execution execucao: tries) {
    				if (execucao.getStatus().equals(search.get(key))) 
    					found.add(execucao);
    			}
    			return found;
    		}
    		
    		if (key.equals("id"))  {
        			for (Execution execucao: tries) {
        				if (execucao.getId().equals(search.get("id")))
        					found.add(execucao);
        			}
        			return found;
        		}
    		if (key.equals("datetime"))  {
    			for (Execution execucao: tries) {
    				if (execucao.getDatetime().equals(search.get("datetime")))
    					found.add(execucao);
    			}
    			return found;
    		}
    	}
    	return found;
    }
	
}
