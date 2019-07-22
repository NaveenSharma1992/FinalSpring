package dis.service.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dis.core.OracleQueueingService;
import dis.model.UserData;

@RestController
public class UserDataResourceService {

	OracleQueueingService queueingService;
	@GetMapping(path="/API")
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_XML)
	public Object exportUserData(@RequestParam(value="gpn") final String personNumber) {
		queueingService = new OracleQueueingService();
		ResponseBuilder responseBuilder;
		List<UserData> UserData = null;
		try{
			UserData = queueingService.getUserData(Integer.parseInt(personNumber));
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(UserData == null || UserData.isEmpty())
			responseBuilder = Response.ok("No data found");
		else
			responseBuilder = Response.ok(UserData);

		return responseBuilder.build().getEntity();	
	}
}
