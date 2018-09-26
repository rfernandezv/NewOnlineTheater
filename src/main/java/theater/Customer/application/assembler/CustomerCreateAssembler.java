package theater.Customer.application.assembler;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import theater.Customer.application.dto.CustomerCreateDto;
import theater.Customer.application.dto.CustomerInListDto;
import theater.Customer.domain.entity.Customer;
import theater.common.infrastructure.persistence.hibernate.UnitOfWorkHibernate;
import java.util.List;
import org.modelmapper.TypeToken;

@Component
public class CustomerCreateAssembler {
	@Autowired
	protected UnitOfWorkHibernate unitOfWork;
	
	public Customer toEntity(CustomerCreateDto customerCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Customer customer = modelMapper.map(customerCreateDto, Customer.class);
		return customer;
	}
        
        public List<CustomerInListDto> toDtoList(List<Customer> customerInListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<CustomerInListDto> bankAccountListDto = modelMapper.map(customerInListDto, new TypeToken<List<CustomerInListDto>>() {}.getType());
		return bankAccountListDto;
	}
	
	private Converter<CustomerCreateDto, Customer> getConverter() {
		Converter<CustomerCreateDto, Customer> converter = new Converter<CustomerCreateDto, Customer>() {
		    @Override
		    public Customer convert(MappingContext<CustomerCreateDto, Customer> context) {
		    	CustomerCreateDto customerCreateDto =  CustomerCreateDto.class.cast(context.getSource());
		    	//MoneyAbstraction balance = new Money(bankAccountCreateDto.getBalance(), bankAccountCreateDto.getCurrency());
		    	Customer customer = new Customer();
		        customer.setCustomerID(customerCreateDto.getCustomerID());
		        customer.setEmail(customerCreateDto.getEmail());
		        customer.setMoneySpent(customerCreateDto.getMoneySpent());
		        customer.setName(customerCreateDto.getName());
		        customer.setStatus(customerCreateDto.getStatus());
		        customer.setStatusExpirationDate(customerCreateDto.getStatusExpirationDate());		        
		    	return customer;
		    }
		};
		return converter;
	}
	
	public CustomerCreateDto toDto(Customer customer) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Customer, CustomerCreateDto> map = new PropertyMap<Customer, CustomerCreateDto>() {
		  protected void configure() {
			map().setCustomerID(source.getCustomerID());
		    map().setEmail(source.getEmail());
		    map().setMoneySpent(source.getMoneySpent());
		    map().setName(source.getName());
		    map().setStatus(source.getStatus());
		    map().setStatusExpirationDate(source.getStatusExpirationDate());
		  
		  }
		};
		modelMapper.addMappings(map);
		CustomerCreateDto customerCreateDto = modelMapper.map(customer, CustomerCreateDto.class);
		return customerCreateDto;
	}
		
}
