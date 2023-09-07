package kr.co.farmstory2.controller.market;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.OrderDTO;
import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.OrderService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/market/order.do")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = -9147289505350596743L;
	private ProductService pService = ProductService.INSTANCE;
	private OrderService oService = OrderService.INSTACE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pNo = req.getParameter("pNo");
		String count = req.getParameter("count");
		ProductDTO product = pService.selectProduct(pNo);
		req.setAttribute("product", product);
		req.setAttribute("count", count);
		req.getRequestDispatcher("/market/order.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String orderProduct = req.getParameter("orderProduct");
		String orderCount = req.getParameter("orderCount");
		String orderDelivery = req.getParameter("orderDelivery");
		String orderPrice = req.getParameter("orderPrice");
		String orderTotal = req.getParameter("orderTotal");
		String orderUser = req.getParameter("orderUser");
		String receiver = req.getParameter("receiver");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String orderEtc = req.getParameter("orderEtc");
		OrderDTO order = new OrderDTO();
		order.setOrderProduct(orderProduct);
		order.setOrderCount(orderCount);
		order.setOrderDelivery(orderDelivery);
		order.setOrderPrice(orderPrice);
		order.setOrderTotal(orderTotal);
		order.setOrderUser(orderUser);
		order.setReceiver(receiver);
		order.setHp(hp);
		order.setZip(zip);
		order.setAddr1(addr1);
		order.setAddr2(addr2);
		order.setOrderEtc(orderEtc);
		oService.insertOrder(order);
		resp.sendRedirect("/Farmstory2/market/list.do");
	}
}