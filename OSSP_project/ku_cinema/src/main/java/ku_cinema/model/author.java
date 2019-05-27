package ku_cinema.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class author implements Serializable {

	private static final long serialVersionUID = 124L;
	
	@Id
	private String iD;
	private String role;
	
	
	public void setID (String ID) {
		this.iD = ID;
	}
	
	public String getID() {
		return this.iD;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
	
}
