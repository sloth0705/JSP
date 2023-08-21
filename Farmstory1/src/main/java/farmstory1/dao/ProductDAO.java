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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ProductDTO selectProduct(int pNo) {
		ProductDTO dto = null;
		return dto;
	}

	public List<ProductDTO> selectProducts() {
		List<ProductDTO> products = new ArrayList<>();

		return products;
	}

	public void updateProduct(ProductDTO dto) {

	}

	public void deleteProduct(int pNo) {

	}
}