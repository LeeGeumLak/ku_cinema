package ku_cinema.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class author implements Serializable {

	private static final long serialVersionUID = 124L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serial;
	//@ManyToOne
	//@JoinColumn(name="customer_iD")
	private String iD;
	@Column(name="role")
	private String authorities;
	
	
	public void setID (String ID) {
		this.iD = ID;
	}
	
	public String getID() {
		return this.iD;
	}
	
	public void setRole(String authorities) {
		this.authorities = authorities;
	}
	
	public String getRole() {
		return this.authorities;
	}
	
}
