package kr.co.jboard2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.TermsDTO;

public class TermsDAO extends DBHelper {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public TermsDTO selectTerms() {
		TermsDTO dto = null;
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			if (rs.next()) {
				dto = new TermsDTO();
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
		} catch (Exception e) {
			logger.error("selectTerms error : " + e.getMessage());
		}
		return dto;
	}
}