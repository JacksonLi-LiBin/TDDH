package com.tddh.dao.impl;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;

import com.tddh.dao.OrderDao;
import com.tddh.db.utils.DBConnectionUtils;
import com.tddh.utils.PropertiesUtils;

public class OrderDaoImpl implements OrderDao {
	private QueryRunner queryRunner = new QueryRunner();

	@Override
	public boolean addNewOrder(int orderId, int userId, int userAddressId, int productId, int orderCounts,
			String orderCreateTime, String orderPayTime, int orderSubmitState, int orderState, int orderSellerId) {
		Connection conn = null;
		try {
			conn = DBConnectionUtils.getConnection();
			conn.setAutoCommit(false);
			int ret = -1;
			ret = queryRunner.update(conn, PropertiesUtils.readProperties("sql", "add_new_order"), orderId, userId,
					userAddressId, productId, orderCounts, orderCreateTime, orderPayTime, orderSubmitState, orderState,
					orderSellerId);
			if (ret > 0) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				return false;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
		return false;
	}
}
