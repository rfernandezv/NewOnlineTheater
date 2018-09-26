package theater.Customer.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import theater.Customer.application.deserializer.CustomerCreateDeserializer;
import java.util.Date;

@JsonDeserialize(using = CustomerCreateDeserializer.class)
public class CustomerInListDto {
	
	private long CustomerID;
	private String  Name;
	private String Email;
	private Double MoneySpent;
	
	
	public long getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(long customerID) {
		CustomerID = customerID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Double getMoneySpent() {
		return MoneySpent;
	}
	public void setMoneySpent(Double moneySpent) {
		MoneySpent = moneySpent;
	}
	
	

}
