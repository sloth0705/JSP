package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ProductDTO;

public class ProductDAO extends DBHelper {
	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}

	private ProductDAO() {

	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void insertProduct(ProductDTO dto) {
		try {
			psmt = getConnection().prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setInt(1, dto.getType());
			psmt.setString(2, dto.getpName());
			psmt.setInt(3, dto.getPrice());
			psmt.setInt(4, dto.getDelivery());
			psmt.setInt(5, dto.getStock());
			psmt.setString(6, dto.getThumb1());
			psmt.setString(7, dto.getThumb2());
			psmt.setString(8, dto.getThumb3());
			psmt.setString(9, dto.getSeller());
			psmt.setString(10, dto.getEtc());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertProduct error : " + e.getMessage());
		}
	}

	public ProductDTO selectProduct(String pNo) {
		ProductDTO dto = null;
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_PRODUCT);
			psmt.setString(1, pNo);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new ProductDTO();
				dto.setpNo(rs.getInt("pNo"));
				dto.setType(rs.getInt("type"));
				dto.setpName(rs.getString("pName"));
				dto.setPrice(rs.getInt("price"));
				dto.setDelivery(rs.getInt("delivery"));
				dto.setStock(rs.getInt("stock"));
				dto.setSold(rs.getInt("sold"));
				dto.setThumb1(rs.getString("thumb1"));
				dto.setThumb2(rs.getString("thumb2"));
				dto.setThumb3(rs.getString("thumb3"));
				dto.setSeller(rs.getString("seller"));
				dto.setEtc(rs.getString("etc"));
				dto.setRdate(rs.getString("rdate"));
			}
			close();
		} catch (Exception e) {
			logger.error("selectProduct error : " + e.getMessage());
		}
		return dto;
	}

	public List<ProductDTO> selectProducts(int start) {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_PRODUCTS);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setpNo(rs.getInt("pNo"));
				dto.setType(rs.getInt("type"));
				dto.setpName(rs.getString("pName"));
				dto.setPrice(rs.getInt("price"));
				dto.setDelivery(rs.getInt("delivery"));
				dto.setStock(rs.getInt("stock"));
				dto.setSold(rs.getInt("sold"));
				dto.setThumb1(rs.getString("thumb1"));
				dto.setThumb2(rs.getString("thumb2"));
				dto.setThumb3(rs.getString("thumb3"));
				dto.setSeller(rs.getString("seller"));
				dto.setEtc(rs.getString("etc"));
				dto.setRdate(rs.getString("rdate"));
				products.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectsProducts error : " + e.getMessage());
		}
		return products;
	}

	public List<ProductDTO> selectProducts(int start, String cate) {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		try {
			psmt = getConnection().prepareStatement(SQL.SELECT_PRODUCTS_BY_CATE);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setpNo(rs.getInt("pNo"));
				dto.setType(rs.getInt("type"));
				dto.setpName(rs.getString("pName"));
				dto.setPrice(rs.getInt("price"));
				dto.setDelivery(rs.getInt("delivery"));
				dto.setStock(rs.getInt("stock"));
				dto.setSold(rs.getInt("sold"));
				dto.setThumb1(rs.getString("thumb1"));
				dto.setThumb2(rs.getString("thumb2"));
				dto.setThumb3(rs.getString("thumb3"));
				dto.setSeller(rs.getString("seller"));
				dto.setEtc(rs.getString("etc"));
				dto.setRdate(rs.getString("rdate"));
				products.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("selectsProducts error : " + e.getMessage());
		}
		return products;
	}

	public int selectCountTotal(String cate) {
		int result = 0;
		try {
			conn = getConnection();
			if (cate != null && !cate.equals("")) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_TOTAL_BY_CATE);
				psmt.setString(1, cate);
			} else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_PRODUCTS_TOTAL);
			}
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCountTotal error : " + e.getMessage());
		}
		return result;
	}
}