package ku_cinema.model;

import java.io.Serializable;		// for using java serialization. -> I/O byte transforming.
import javax.persistence.*;

@Entity
public class customer implements Serializable {
	
	private static final long serialVersionUID = 123L;
	
	@Id
	private String ID;
	
	private String PW;
	private String Name;
	private String Cellphone;
	private String Email;
	private int Mileage;
	private int Coupon;
	
	public String getID() {
		return this.ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getPW() {
		return this.PW;
	}
	
	public void setPW(String PW) {
		this.PW = PW;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getCellphone() {
		return this.Cellphone;
	}
	
	public void setCellphone(String Cellphone) {
		this.Cellphone = Cellphone;
	}
	
	public String getEmail() {
		return this.Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}

	public int getMileage() {
		return this.Mileage;
	}
	
	public void setMileage(int Mileage) {
		this.Mileage = Mileage;
	}
	
	public int getCoupon() {
		return this.Coupon;
	}
	
	public void setCoupon(int Coupon) {
		this.Coupon = Coupon;
	}
}
