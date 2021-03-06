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
import com.tddh.dao.impl.UserDaoImpl;
import com.tddh.model.ApplyUserProductModel;
import com.tddh.model.UserModel;
import com.tddh.model.UserMoneyModel;
import com.tddh.model.UserProxyProductDetailModel;

/**
 * handle user request
 * 
 * @author jackson
 *
 */
@Path("user")
public class UserRestful {
	private UserDaoImpl udi = new UserDaoImpl();
	private OrderDaoImpl odi = new OrderDaoImpl();

	/**
	 * judge whether user has been proxy
	 * 
	 * @param userId
	 * @return
	 */
	@Path("/isUserProxy")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String isUserProxy(@QueryParam("userId") int userId) {
		boolean flag = udi.isUserProxy(userId);
		return flag ? "true" : "false";
	}

	/**
	 * get user base information
	 * 
	 * @param userId
	 * @return
	 */
	@Path("/loadUserBaseInformation")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserModel loadUserBaseInformation(@QueryParam("userId") int userId) {
		UserModel userModel = udi.getUserBaseInformation(userId);
		return userModel;
	}

	/**
	 * get user all purchase amount
	 * 
	 * @param userId
	 * @return
	 */
	@Path("/loadUserPurchaseAmount")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadUserPurchaseDeductAmount(@QueryParam("userId") int userId) {
		String purchaseAmount = udi.getUserPurchaseAmount(userId);
		return purchaseAmount;
	}

	/**
	 * get user deduct amount
	 * 
	 * @param userId
	 * @return
	 */
	@Path("/loadUserDeductAmount")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String loadUserDeductAmount(@QueryParam("userId") int userId) {
		String deductAmount = udi.getUserDeductAmount(userId);
		return deductAmount;
	}

	/**
	 * get user proxy product information id/name/price
	 * 
	 * @param userId
	 * @return
	 */
	@Path("/loadUserProxyProductDetail")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserProxyProductDetailModel> loadUserProxyProductDetail(@QueryParam("userId") int userId) {
		List<UserProxyProductDetailModel> list = udi.getUserProxyProductDetail(userId);
		return list;
	}

	/**
	 * user apply for product proxy
	 * 
	 * @param applyUserProduct
	 * @return
	 */
	@Path("/applyForProductProxy")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String applyForProductProxy(ApplyUserProductModel applyUserProduct) {
		String apply_response = odi.applyForProductProxy(applyUserProduct.getUserId(), applyUserProduct.getRpId(),
				applyUserProduct.getProxyProduct().getProductId(),
				applyUserProduct.getProxyProduct().getProductCounts(), applyUserProduct.getProxyLevel(),
				applyUserProduct.getPayType());
		return apply_response;
	}

	/**
	 * user apply for upgrading product proxy level
	 * 
	 * @param applyUserProduct
	 * @return
	 */
	@Path("/upgradeProductProxy")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String upgradeProductProxy(ApplyUserProductModel applyUserProduct) {
		String upgradeResponse = odi.upgradeProductProxy(applyUserProduct.getUserId(),
				applyUserProduct.getProxyProduct().getProductId(),
				applyUserProduct.getProxyProduct().getProductCounts(), applyUserProduct.getProxyLevel(),
				applyUserProduct.getPayType());
		return upgradeResponse;
	}

	@Path("/loadUserMoney")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserMoneyModel loadUserMoney(@QueryParam("userId") int userId) {
		UserMoneyModel userMoneyModel = udi.getUserMoney(userId);
		return userMoneyModel;
	}
}
