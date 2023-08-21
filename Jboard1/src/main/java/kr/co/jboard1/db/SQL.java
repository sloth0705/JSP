package kr.co.jboard1.db;

public class SQL {
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
												+ "FROM `Article` "
												+ "WHERE `no` = ?";
	public static final String SELECT_ARTICLES = "SELECT a.*, "
												+ "b.nick "
												+ "FROM `Article` AS a "
												+ "JOIN `User` AS b on a.`writer` = b.`uid` "
												+ "WHERE `parent` = 0 "
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
	public static final String UPDATE_ARTICLE_FOR_COMMENT = "UPDATE `Article` "
															+ "SET `comment` = `comment` + 1 "
															+ "WHERE `no` = ?";
	public static final String UPDATE_COMMENT = "UPDATE `Article` "
												+ "SET `content` = ? "
												+ "WHERE `no` = ?";
	public static final String DELETE_COMMENT = "DELETE FROM `Article` "
												+ "WHERE `no` = ?";
}