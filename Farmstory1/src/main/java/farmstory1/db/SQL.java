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
	public static final String SELECT_USER = "SELECT * FROM `User` WHERE `uid` = ? AND `pass` = SHA2(?, 256)";
}