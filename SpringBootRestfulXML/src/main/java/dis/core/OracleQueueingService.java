package dis.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dis.model.UserData;

public class OracleQueueingService {
	private final static String Driver = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private final static String USER = "HR";
	private final static String PASSWORD = "HR";
	
	public List<UserData> getUserData(final int personNumber) throws Exception {
		List<UserData> userDataList = new ArrayList<>();
		Class.forName(Driver);
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String query = "SELECT * FROM SNOW WHERE PERSON_NUMBER = ?";

			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, personNumber);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next())
				{
					userDataList.add(new UserData(rs.getInt("PERSON_NUMBER"), rs.getString("BL_STATUS")));
				}
			} catch (Exception e) {
				throw e;
			}
		}catch (Exception e) {
			throw e;
		}
		return userDataList;
	}
}
