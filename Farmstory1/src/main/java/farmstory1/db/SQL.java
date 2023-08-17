package farmstory1.db;

public class SQL {
	public static final String SELECT_TERMS = "SELECT * "
											+ "FROM `terms`";
	public static final String INSERT_USER = "INSERT INTO `user` "
											+ "VALUES(?, "
											+ "SHA2(?, 256), "
											+ "?, "
											+ "?, "
											+ "?, "
											+ "?, "
											+ "?, "
											+ "?, "
											+ "?, "
											+ "?, "
											+ "?, "
											+ "now(), "
											+ "?)";
	public static final String SELECT_USER = "SELECT * "
											+ "FROM `User` "
											+ "WHERE `uid` = ? AND "
											+ "`pass` = SHA2(?, 256)";
	public static final String INSERT_ARTICLE = "INSERT INTO `article` "
												+ "SET `cate` = ?, "
												+ "`title` = ?, "
												+ "`content` = ?, "
												+ "`writer` = ?, "
												+ "`regip` = ?, "
												+ "`rdate` = NOW()";
	public static final String SELECT_ARTICLES = "SELECT a.*, "
												+ "b.nick "
												+ "FROM `article` AS a "
												+ "JOIN `user` AS b on a.`writer` = b.`uid` "
												+ "WHERE `parent` = 0 AND "
												+ "`cate` = ? "
												+ "ORDER BY a.`no` DESC "
												+ "LIMIT ?, 10";
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) "
													+ "FROM `article` "
													+ "WHERE `parent` = 0 AND "
													+ "`cate` = ?";	
}