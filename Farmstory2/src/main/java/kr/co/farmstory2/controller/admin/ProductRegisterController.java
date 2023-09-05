package kr.co.farmstory2.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productRegister.do")
public class ProductRegisterController extends HttpServlet {
	private static final long serialVersionUID = 4988836274699503222L;
	private ProductService pService = ProductService.INSTANCE;
	private ArticleService aService = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/admin/productRegister.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MultipartRequest mr = aService.uploadFile(req);
		String type = mr.getParameter("type");
		String pName = mr.getParameter("pName");
		String price = mr.getParameter("price");
		String delivery = mr.getParameter("delivery");
		String stock = mr.getParameter("stock");
		String seller = mr.getParameter("seller");
		String etc = mr.getParameter("etc");
		String thumb1 = mr.getOriginalFileName("thumb1");
		String thumb2 = mr.getOriginalFileName("thumb2");
		String thumb3 = mr.getOriginalFileName("thumb3");
		
		String newName1 = aService.renameToFile(req, thumb1);
		String newName2 = aService.renameToFile(req, thumb2);
		String newName3 = aService.renameToFile(req, thumb3);

		ProductDTO dto = new ProductDTO();
		dto.setType(type);
		dto.setpName(pName);
		dto.setPrice(price);
		dto.setDelivery(delivery);
		dto.setStock(stock);
		dto.setSeller(seller);
		dto.setEtc(etc);
		dto.setThumb1(newName1);
		dto.setThumb2(newName2);
		dto.setThumb3(newName3);
		pService.insertProduct(dto);


		resp.sendRedirect("/Farmstory2/admin/productList.do");
	}
}