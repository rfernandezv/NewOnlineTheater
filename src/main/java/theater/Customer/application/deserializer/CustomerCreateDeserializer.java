package theater.Customer.application.deserializer;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import theater.Customer.application.dto.CustomerCreateDto;

public class CustomerCreateDeserializer extends JsonDeserializer<CustomerCreateDto> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CustomerCreateDto deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		CustomerCreateDto customerCreateDto = null;
		String json = jsonParser.readValueAsTree().toString();
		JsonNode node = new ObjectMapper().readTree(json);
                modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		  .addValueReader(new JsonNodeValueReader());
		try {
		  customerCreateDto = modelMapper.map(node, CustomerCreateDto.class);
		} catch(Exception ex) {
			customerCreateDto = new CustomerCreateDto();
		}
		return customerCreateDto;
	}
}
