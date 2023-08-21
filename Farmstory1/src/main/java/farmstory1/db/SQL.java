package farmstory1.db;

public class SQL {
	public static final String SELECT_TERMS = "SELECT * "
											+ "FROM `Terms`";
	public static final String INSERT_USER = "INSERT INTO `User` "
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
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` "
												+ "SET `cate` = ?, "
												+ "`title` = ?, "
												+ "`content` = ?, "
												+ "`writer` = ?, "
												+ "`regip` = ?, "
												+ "`rdate` = NOW()";
	public static final String INSERT_COMMENT = "INSERT INTO `Article` "
												+ "SET `parent` = ?, "
												+ "`content` = ?, "
												+ "`writer` = ?, "
												+ "`regip` = ?, "
												+ "`rdate` = NOW()";
	public static final String SELECT_ARTICLES = "SELECT a.*, "
												+ "b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b on a.`writer` = b.`uid` "
												+ "WHERE `parent` = 0 AND "
												+ "`cate` = ? "
												+ "ORDER BY a.`no` DESC "
												+ "LIMIT ?, 10";
	public static final String SELECT_ARTICLES_LIMIT = "SELECT a.*, "
												+ "b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b on a.`writer` = b.`uid` "
												+ "WHERE `parent` = 0 AND "
												+ "`cate` = ? "
												+ "ORDER BY a.`rdate` DESC "
												+ "LIMIT ?";
	public static final String SELECT_ARTICLE = "SELECT a.*, "
												+ "b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b on a.`writer` = b.`uid` "
												+ "WHERE `no` = ?";
	public static final String SELECT_COMMENTS =  "SELECT a.*, "
												+ "b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b on a.`writer` = b.`uid` "
												+ "WHERE a.`parent` = ?";
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) "
													+ "FROM `Article` "
													+ "WHERE `parent` = 0 AND "
													+ "`cate` = ?";
	public static final String UPDATE_ARTICLE = "UPDATE `Article` "
												+ "SET `title` = ?, "
												+ "`content` = ? "
												+ "WHERE `no` = ?";
	public static final String UPDATE_ARTICLE_FOR_COMMENT = "UPDATE `Article` "
															+ "SET `comment` = `comment` + 1 "
															+ "WHERE `no` = ?";
	public static final String DELETE_ARTICLE = "DELETE FROM `Article` "
												+ "WHERE `no` = ? "
												+ "OR `parent` = ?";
	public static final String DELETE_COMMENT = "DELETE FROM `Article` "
												+ "WHERE `no` = ?";
	public static final String DELETE_ARTICLE_FOR_COMMENT = "UPDATE `Article` "
															+ "SET `comment` = `comment` - 1 "
															+ "WHERE `no` = ?";
	public static final String UPDATE_COMMENT = "UPDATE `Article` "
												+ "SET `content` = ? "
												+ "WHERE `no` = ?";
	public static final String INSERT_PRODUCT = "INSERT INTO `Product` SET "
												+ "`type` = ?, "
												+ "`pName` = ?, "
												+ "`price` = ?, "
												+ "`delivery` = ?, "
												+ "`stock` = ?, "
												+ "`thumb1` = ?, "
												+ "`thumb2` = ?, "
												+ "`thumb3` = ?, "
												+ "`seller` = ?, "
												+ "`etc` = ?, "
												+ "`rdate` = NOW()";
}