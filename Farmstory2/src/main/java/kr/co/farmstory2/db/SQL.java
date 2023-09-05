package kr.co.farmstory2.db;

public class SQL {
	public static final String SELECT_TERMS = "SELECT * "
											+ "FROM `Terms`";
	public static final String INSERT_USER = "INSERT INTO `User` "
											+ "SET `uid` = ?, "
											+ "`pass` = SHA2(?, 256), "
											+ "`name` = ?, "
											+ "`nick` = ?, "
											+ "`email` = ?, "
											+ "`hp` = ?, "
											+ "`zip` = ?, "
											+ "`addr1` = ?, "
											+ "`addr2` = ?, "
											+ "`regip` = ?, "
											+ "`regDate` = now()";
	public static final String SELECT_USER = "SELECT * "
											+ "FROM `User` "
											+ "WHERE `uid` = ? AND "
											+ "`pass` = SHA2(?, 256)";
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` "
												+ "SET `cate` = ?, "
												+ "`title` = ?, "
												+ "`content` = ?, "
												+ "`file` = ?, "
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
	public static final String SELECT_PRODUCTS_ALL = "SELECT * "
													+ "FROM `Product` "
													+ "WHERE `stock` > 0 "
													+ "ORDER BY `rdate` DESC "
													+ "LIMIT ?, 10";
	public static final String SELECT_PRODUCTS_TYPE = "SELECT * "
													+ "FROM `Product` "
													+ "WHERE `stock` > 0 AND "
													+ "`type` = ? "
													+ "ORDER BY `rdate` DESC "
													+ "LIMIT ?, 10";
	public static final String SELECT_COUNT_PRODUCTS_ALL = "SELECT COUNT(*) "
															+ "FROM `Product` "
															+ "WHERE `stock` > 0";
	public static final String SELECT_COUNT_PRODUCTS_TYPE = "SELECT COUNT(*) "
															+ "FROM `Product` "
															+ "WHERE `stock` > 0 AND "
															+ "`type` = ?";
	public static final String SELECT_PRODUCT = "SELECT * "
												+ "FROM `Product` "
												+ "WHERE `pNo` = ?";
	public static final String INSERT_ORDER = "INSERT INTO `Order` "
											+ "SET `orderProduct` = ?, "
											+ "`orderCount` = ?, "
											+ "`orderDelivery` = ?, "
											+ "`orderPrice` = ?, "
											+ "`orderTotal` = ?, "
											+ "`receiver` = ?, "
											+ "`hp` = ?, "
											+ "`zip` = ?, "
											+ "`addr1` = ?, "
											+ "`addr2` = ?, "
											+ "`orderEtc` = ?, "
											+ "`orderUser` = ?, "
											+ "`orderDate` = NOW()";
	public static final String SELECT_ORDERS = "SELECT a.*, "
												+ "b.`name`, "
												+ "c.`thumb1`, "
												+ "c.`pName` "
												+ "FROM `Order` AS a "
												+ "JOIN `User` AS b "
												+ "ON a.`orderUser` = b.`uid` "
												+ "JOIN `Product` AS c "
												+ "ON a.`orderProduct` = c.`pNo` "
												+ "ORDER BY a.`orderDate` DESC "
												+ "LIMIT ?, 10";
	public static final String SELECT_COUNT_ORDERS_TOTAL = "SELECT COUNT(*) FROM `Order`";
	public static final String DELETE_ORDER = "DELETE FROM `Order` WHERE `OrderNo` = ?";
	public static final String CHECK_UID = "SELECT COUNT(*) FROM `User` WHERE `uid` = ?";
	public static final String CHECK_NICK = "SELECT COUNT(*) FROM `User` WHERE `nick` = ?";
	public static final String CHECK_HP = "SELECT COUNT(*) FROM `User` WHERE `hp` = ?";
	public static final String DOWNLOAD_FILE = "UPDATE `File` SET `download` = `download` + 1 WHERE `fNo` = ?";
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`";
	public static final String INSERT_FILE = "INSERT INTO `File` "
											+ "SET `aNo` = ?, "
											+ "`oriName` = ?, "
											+ "`newName` = ?, "
											+ "`rdate` = NOW()";
	public static final String SELECT_FILE = "SELECT * FROM `File` WHERE `aNo` = ?";
	public static final String SELECT_FILE_BY_FNO = "SELECT * FROM `File` WHERE `fNo` = ?";
	public static final String PLUS_COMMENT = "UPDATE `Article` SET `comment` = `comment` + 1 WHERE `no` = ?";
	public static final String MINUS_COMMENT = "UPDATE `Article` SET `comment` = `comment` - 1 WHERE `no` = ?";
	public static final String SELECT_COUNT_PRODUCTS_TOTAL = "SELECT COUNT(*) FROM `Product`";
	public static final String SELECT_PRODUCTS = "SELECT * "
												+ "FROM `Product` "
												+ "WHERE `stock` > 0 "
												+ "ORDER BY `rdate` DESC "
												+ "LIMIT ?, 10";
}