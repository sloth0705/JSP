package kr.co.farmstory2.service;

import kr.co.farmstory2.dao.OrderDAO;
import kr.co.farmstory2.dto.OrderDTO;

public enum OrderService {
	INSTACE;

	private OrderDAO dao = OrderDAO.getInstance();

	public void insertOrder(OrderDTO dto) {
		dao.insertOrder(dto);
	}
}