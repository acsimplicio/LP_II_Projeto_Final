package fatec.edu.micronaut;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Execution {
	private String sourcecode;
	private String filename;
	private String problem;
	private String status;
	private String datetime;
	private String id;
	
	public String getSourcecode() {
		return sourcecode;
	}
	public void setSourcecode(String sourcecode) {
		this.sourcecode = sourcecode;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Execution(String filename, String sourcecode, String problem) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		this.setFilename(filename);
		this.setSourcecode(sourcecode);
		this.setProblem(problem);
		this.setDatetime(LocalDateTime.now().format(dateFormat));
	}
	
	public Map<String, String> mapResponse() {
		Map<String, String> response = new HashMap<String, String>();
		response.put("filename", this.getFilename());
		response.put("problem", this.getProblem());
		response.put("status", this.getStatus());
		
		return response;
	}
	
}
