package farmstory1.dao;

import java.util.ArrayList;
import java.util.List;

import farmstory1.db.DBHelper;
import farmstory1.db.SQL;
import farmstory1.dto.ProductDTO;

public class ProductDAO extends DBHelper {
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return dto;
	}

	public List<ProductDTO> selectProducts(int start, String type) {
		List<ProductDTO> products = new ArrayList<>();
		try {
			if (type.equals("0")) {
				psmt = getConnection().prepareStatement(SQL.SELECT_PRODUCTS_ALL);
				psmt.setInt(1, start);
			} else {
				psmt = getConnection().prepareStatement(SQL.SELECT_PRODUCTS_TYPE);
				psmt.setString(1, type);
				psmt.setInt(2, start);
			}
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
			e.printStackTrace();
		}
		return products;
	}

	public void updateProduct(ProductDTO dto) {

	}

	public void deleteProduct(int pNo) {

	}
	
	public int selectCountProductsTotal(String type) {
		int total = 0;
		try {
			if (type.equals("0")) {
				psmt = getConnection().prepareStatement(SQL.SELECT_COUNT_PRODUCTS_ALL);
			} else {
				psmt = getConnection().prepareStatement(SQL.SELECT_COUNT_PRODUCTS_TYPE);
				psmt.setString(1, type);
			}
			rs = psmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
}