package com.tddh.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tddh.dao.impl.UserDaoImpl;
import com.tddh.model.UserModel;

/**
 * handle user request
 * 
 * @author jackson
 *
 */
@Path("user")
public class UserRestful {
	private UserDaoImpl udi = new UserDaoImpl();

	@Path("/isUserProxy")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String isUserProxy(@QueryParam("userId") int userId) {
		boolean flag = udi.isUserProxy(userId);
		return flag ? "true" : "false";
	}

	@Path("/loadUserBaseInformation")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel loadUserBaseInformation(@QueryParam("userId") int userId) {
		UserModel userModel = udi.getUserBaseInformation(userId);
		return userModel;
	}

	@Path("/loadUserPurchaseAmount")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadUserPurchaseDeductAmount(@QueryParam("userId") int userId) {
		String purchaseAmount = udi.getUserPurchaseAmount(userId);
		return purchaseAmount;
	}

	@Path("/loadUserDeductAmount")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadUserDeductAmount(@QueryParam("userId") int userId) {
		String deductAmount = udi.getUserDeductAmount(userId);
		return deductAmount;
	}
}
