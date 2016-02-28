package com.tddh.restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tddh.dao.impl.OrderDaoImpl;
import com.tddh.model.PurchaseUserProductModel;
import com.tddh.model.UserSubordinateRecommendOrderModel;

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

	/**
	 * get user subordinate or recommend product proxy orders
	 * 
	 * @param reqType
	 *            0 subordinate 1 recommend
	 * @param userId
	 * @param productId
	 * @return
	 */
	@Path("/loadUserSROrders")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserSubordinateRecommendOrderModel> loadUserSROrders(@QueryParam("reqType") int reqType,
			@QueryParam("userId") int userId, @QueryParam("productId") int productId) {
		List<UserSubordinateRecommendOrderModel> models = odi.getMySubordinateRecommendOrder(reqType, userId,
				productId);
		return models;

	}

	@Path("/purchaseProduct")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String purchaseProduct(PurchaseUserProductModel purchaseUserProductModel) {
		return odi.purchaseOrder(purchaseUserProductModel.getUserId(), purchaseUserProductModel.getPurchaseProducts(),
				purchaseUserProductModel.getAddressId(), purchaseUserProductModel.getPayType()) ? "true" : "false";
	}
}
