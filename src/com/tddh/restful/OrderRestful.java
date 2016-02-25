package com.tddh.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tddh.dao.impl.OrderDaoImpl;

/**
 * handle order request
 * 
 * @author jackson
 *
 */
@Path("order")
public class OrderRestful {
	private OrderDaoImpl odi = new OrderDaoImpl();

	/**
	 * get order type 0 my order 1 my proxy order
	 * 
	 * @param orderType
	 * @param userId
	 * @return
	 */
	@Path("/loadOrders")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadOrders(@QueryParam("order_type") int orderType, @QueryParam("user_id") int userId) {
		String userOrder = odi.getOrderDetail(orderType, userId);
		return userOrder;
	}
}
