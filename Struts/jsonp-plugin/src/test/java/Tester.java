import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import junit.framework.TestCase;


public class Tester extends TestCase {

	public void testString() throws Exception, JsonMappingException, IOException{
		
		user u = new user();
		u.setName("jkack");
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		mapper.writeValue(strWriter, (Object)u);
		String jsonStr = strWriter.toString();
		System.out.println(jsonStr);
	}
	
	
}

class user{

	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
