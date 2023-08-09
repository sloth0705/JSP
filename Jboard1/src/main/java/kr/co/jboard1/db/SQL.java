package kr.co.jboard1.db;

public class SQL {
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
											+ "FROM `user` "
											+ "WHERE `uid` = ? AND "
											+ "`pass` = SHA2(?, 256)";
	public static final String SELECT_COUNT_UID = "SELECT COUNT(*) "
												+ "FROM `user` "
												+ "WHERE `uid` = ?";
	public static final String SELECT_COUNT_NICK = "SELECT COUNT(*) "
													+ "FROM `user` "
													+ "WHERE `nick` = ?";
	public static final String SELECT_COUNT_HP = "SELECT COUNT(*) "
												+ "FROM `user` "
												+ "WHERE `hp` = ?";
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(*) "
													+ "FROM `user` "
													+ "WHERE `email` = ?";
	public static final String INSERT_ARTICLE = "INSERT INTO `article` "
												+ "SET `title` = ?, "
												+ "`content` = ?, "
												+ "`writer` = ?, "
												+ "`regip` = ?, "
												+ "`rdate` = NOW()";
	public static final String INSERT_COMMENT = "INSERT INTO `article` "
												+ "SET `parent` = ?, "
												+ "`content` = ?, "
												+ "`writer` = ?, "
												+ "`regip` = ?, "
												+ "`rdate` = NOW()";
	public static final String SELECT_ARTICLE = "SELECT * "
												+ "FROM `article` "
												+ "WHERE `no` = ?";
	public static final String SELECT_ARTICLES = "SELECT a.*, "
												+ "b.nick "
												+ "FROM `article` AS a "
												+ "JOIN `user` AS b on a.`writer` = b.`uid` "
												+ "WHERE `parent` = 0 "
												+ "ORDER BY a.`no` DESC "
												+ "LIMIT ?, 10";
	public static final String SELECT_COMMENTS =  "SELECT a.*, "
												+ "b.nick "
												+ "FROM `article` AS a "
												+ "JOIN `user` AS b on a.`writer` = b.`uid` "
												+ "WHERE a.`parent` = ?";
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) "
													+ "FROM `article` "
													+ "WHERE `parent` = 0";
	public static final String UPDATE_ARTICLE_FOR_COMMENT = "UPDATE `article` "
															+ "SET `comment` = `comment` + 1 "
															+ "WHERE `no` = ?";
	public static final String DELETE_COMMENT = "DELETE FROM `article` "
												+ "WHERE `no` = ?";
}