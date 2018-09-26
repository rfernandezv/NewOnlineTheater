package theater.Customer.application.validation;


import theater.Customer.application.dto.CustomerCreateDto;
import theater.common.application.notification.Notification;
import org.springframework.stereotype.Component;

@Component
public class CustomerCreateValidation {
	public void validate(CustomerCreateDto customerCreateDto) {
		Notification notification = this.validateData(customerCreateDto);
		if (notification.hasErrors()) {
                    throw new IllegalArgumentException(notification.errorMessage());
                }
	}
	
	public Notification validateData(CustomerCreateDto customerCreateDto) {
		Notification notification = new Notification();
		if (customerCreateDto == null) {
			notification.addError("Missing Customer parameters");
			return notification;
		}
		
		if (customerCreateDto.getName() == null) {
			notification.addError("Missing name parameter");
			return notification;
		}
		if (customerCreateDto.getMoneySpent() <= 0.0) {
			notification.addError("Balance must be grater than zero");
		}
		return notification;
	}
}
