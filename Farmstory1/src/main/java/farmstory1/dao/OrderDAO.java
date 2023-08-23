package farmstory1.dao;

import java.util.ArrayList;
import java.util.List;

import farmstory1.db.DBHelper;
import farmstory1.db.SQL;
import farmstory1.dto.OrderDTO;

public class OrderDAO extends DBHelper {
	public void insertOrder(OrderDTO dto) {
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_ORDER);
			psmt.setInt(1, dto.getOrderProduct());
			psmt.setInt(2, dto.getOrderCount());
			psmt.setInt(3, dto.getOrderDelivery());
			psmt.setInt(4, dto.getOrderPrice());
			psmt.setInt(5, dto.getOrderTotal());
			psmt.setString(6, dto.getReceiver());
			psmt.setString(7, dto.getHp());
			psmt.setString(8, dto.getZip());
			psmt.setString(9, dto.getAddr1());
			psmt.setString(10, dto.getAddr2());
			psmt.setString(11, dto.getOrderEtc());
			psmt.setString(12, dto.getOrderUser());
			psmt.executeUpdate();
			close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public OrderDTO selectOrder(int orderNo) {
		OrderDTO dto = null;
		return dto;
	}

	public List<OrderDTO> selectOrders() {
		List<OrderDTO> orders = new ArrayList<>();
		return orders;
	}

	public void updateOrder(OrderDTO dto) {

	}

	public void deleteOrder(int orderNo) {

	}
}