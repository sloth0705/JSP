package kr.co.jboard2.db;

public class SQL {
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
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) "
												+ "FROM `User` "
												+ "WHERE `uid` = ?";
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(*) "
													+ "FROM `User` "
													+ "WHERE `nick` = ?";
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) "
												+ "FROM `User` "
												+ "WHERE `hp` = ?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) "
													+ "FROM `User` "
													+ "WHERE `email` = ?";
	public static final String INSERT_ARTICLE = "INSERT INTO `Article` "
												+ "SET `title` = ?, "
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
	public static final String SELECT_ARTICLE = "SELECT * "
												+ "FROM `Article` AS a "
												+ "LEFT OUTER JOIN `File` AS b on a.`no` = b.`ano` "
												+ "WHERE a.`no` = ?";
	public static final String SELECT_ARTICLES = "SELECT a.*, "
												+ "b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b on a.`writer` = b.`uid` "
												+ "WHERE `parent` = 0 "
												+ "ORDER BY a.`no` DESC "
												+ "LIMIT ?, 10";
	public static final String SELECT_ARTICLES_FOR_SEARCH = "SELECT a.*, "
															+ "b.nick "
															+ "FROM `Article` AS a "
															+ "JOIN `User` AS b on a.`writer` = b.`uid` "
															+ "WHERE `parent` = 0 AND "
															+ "`title` LIKE ? "
															+ "ORDER BY a.`no` DESC "
															+ "LIMIT ?, 10";
	public static final String SELECT_COMMENTS =  "SELECT a.*, "
												+ "b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b on a.`writer` = b.`uid` "
												+ "WHERE a.`parent` = ?";
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) "
													+ "FROM `Article` "
													+ "WHERE `parent` = 0";
	public static final String SELECT_COUNT_TOTAL_FOR_SEARCH = "SELECT COUNT(*) "
																+ "FROM `Article` "
																+ "WHERE `parent` = 0 AND "
																+ "`title` LIKE ?";
	public static final String UPDATE_ARTICLE_FOR_COMMENT = "UPDATE `Article` "
															+ "SET `comment` = `comment` + 1 "
															+ "WHERE `no` = ?";
	public static final String UPDATE_COMMENT = "UPDATE `Article` "
												+ "SET `content` = ? "
												+ "WHERE `no` = ?";
	public static final String DELETE_COMMENT = "DELETE FROM `Article` "
												+ "WHERE `no` = ?";
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	public static final String CHECK_UID = "SELECT COUNT(*) FROM `User` WHERE `uid` = ?";
	public static final String CHECK_NICK = "SELECT COUNT(*) FROM `User` WHERE `nick` = ?";
	public static final String CHECK_HP = "SELECT COUNT(*) FROM `User` WHERE `hp` = ?";
	public static final String FIND_USER_COUNT_BY_NAME_AND_EMAIL = "SELECT COUNT(*) FROM `User` WHERE `name` = ? AND `email` = ?";
	public static final String FIND_USER_BY_NAME_AND_EMAIL = "SELECT * FROM `User` WHERE `name` = ? AND `email` = ?";
	public static final String INSERT_FILE = "INSERT INTO `File` "
											+ "SET `ano` = ?, "
											+ "`oriName` = ?, "
											+ "`newName` = ?, "
											+ "`rdate` = NOW()";
	public static final String SELECT_MAX_NO = "SELECT MAX(`no`) FROM `Article`";
	public static final String SELECT_FILE = "SELECT * FROM `File` WHERE `fno` = ?";
	public static final String DOWNLOAD_FILE = "UPDATE `File` SET `download` = `download` + 1 WHERE `fno` = ?";
	public static final String DELETE_FILE = "DELETE FROM `File` WHERE `ano` = ?";
	public static final String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no` = ?";
}