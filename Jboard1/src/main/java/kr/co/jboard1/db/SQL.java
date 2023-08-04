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
}