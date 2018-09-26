package theater.Customer.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import theater.Customer.application.dto.CustomerCreateDto;
import theater.Customer.application.dto.CustomerInListDto;
import theater.common.application.ApiResponseHandler;
import java.util.List;
import theater.Customer.application.service.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

        @Autowired
	CustomerService customerService;	

	@Autowired
	ApiResponseHandler apiResponseHandler;
	
        @RequestMapping(
	    method = RequestMethod.GET,
	    path = "/all"
	)
	public ResponseEntity<Object> getAll() throws Exception {
            try {
                List<CustomerInListDto> listado = customerService.getAll();
                return new ResponseEntity<Object>(listado, HttpStatus.OK);
            } catch(IllegalArgumentException ex) {
                    ex.printStackTrace();
                    return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
	@Transactional(rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	@RequestMapping(
	    method = RequestMethod.POST,
	    path = "",
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<Object> create(@RequestBody CustomerCreateDto customerCreateDto) throws Exception {
        try {    
            CustomerCreateDto responseCustomerDto = customerService.create(customerCreateDto);
            return new ResponseEntity<Object>(responseCustomerDto, HttpStatus.CREATED);
        } catch(IllegalArgumentException ex) {
        	ex.printStackTrace();
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch(Exception ex) {
            	ex.printStackTrace();
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
	
	
}
