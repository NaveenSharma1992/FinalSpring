package dis.model;

public class UserData {
	private long personNumber;
	private String status;
	
	public UserData(final long personNumber, final String status) {
		this.personNumber = personNumber;
		this.status = status;
	}
	
	public long getPersonNumber() {
		return this.personNumber;
	}
	
	public String getStatus() {
		return this.status;
	}
}
