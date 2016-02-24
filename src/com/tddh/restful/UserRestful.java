package com.tddh.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tddh.dao.impl.UserDaoImpl;

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
}
