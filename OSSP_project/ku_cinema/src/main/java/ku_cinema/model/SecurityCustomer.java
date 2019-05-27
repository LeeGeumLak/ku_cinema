package ku_cinema.model;

import org.springframework.security.core.userdetails.User;
// 한 번 뜯어봐야겠다... 이 안에 무엇이 있는지...
// reference : https://4urdev.tistory.com/53

public class SecurityCustomer extends User {
	
	private static final long serialVersionUID = 126L;
	
	private String ip;
	
	public SecurityCustomer(customer cus) {
		
		super(cus.getID(), cus.getPW(), cus.getAuthorities());
	}
	// 한번 user를 뜯어봐야 할 듯.
	
	public String getIP() {
		return this.ip;
	}
	
	public void setIP(String ip) {
		this.ip = ip;
	}

	// 추후 controller에서 사용하는 customer 객체는 이 security customer가 될 것.
	// 필요한 data는 여기에 넣어 두어야 사용 가능. 그래서 일단은 ip를 넣어둠.
	
	// security에서 제공하는 user 객체를 사용해야 하고, (userdetails)
	// security에서 제공하는 user service를 사용해서 user 객체를 다뤄야 한다. (userdetailservice)

}
