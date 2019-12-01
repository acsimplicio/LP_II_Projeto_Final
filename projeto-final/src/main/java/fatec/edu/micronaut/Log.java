package fatec.edu.micronaut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Log {
	
	private ArrayList<Execution> logs = new ArrayList<Execution>(); // Lista que armazena os logs de execução
	private int executionId = 1; // Id (incremental) dado para cada log no salvamento
	
	// Salva a execução no Array de logs para que possa ser feita a busca posteriormente
	public void saveLog (Execution ex) {
		ex.setId(Integer.toString(this.executionId));
		this.logs.add(ex);
		this.executionId++;
	}
	
	// Realiza a pesquisa od logs das execuções pelos parâmetros passados
	public List<Execution> searchLog(Map<String, String> search){
		
		ArrayList<String> keySearch = new ArrayList<String>(search.keySet());
		ArrayList<Execution> found = new ArrayList<Execution>();
	
    	for(String key:keySearch) {
    		if (key.equals("status")) {
    			for (Execution log: logs) {
    				if (log.getStatus().equals(search.get(key))) 
    					found.add(log);
    			}
    			return found;
    		}
    		
    		if (key.equals("id"))  {
        			for (Execution log: logs) {
        				if (log.getId().equals(search.get("id")))
        					found.add(log);
        			}
        			return found;
        		}
    		if (key.equals("datetime"))  {
    			for (Execution log: logs) {
    				if (log.getDatetime().equals(search.get("datetime")))
    					found.add(log);
    			}
    			return found;
    		}
    	}
    	return found;
    }

}
