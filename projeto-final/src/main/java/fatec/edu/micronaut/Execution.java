package fatec.edu.micronaut;

public class Execution {
	private String sourcecode;
	private String filename;
	private String problem;
	private String status;
	private String datetime;
	private int id;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Execution(String filename, String sourcecode, String problem) {
		this.setFilename(filename);
		this.setSourcecode(sourcecode);
		this.setProblem(problem);
		this.setDatetime(java.time.LocalDateTime.now().toString());
	}
	
}
