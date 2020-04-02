package population.xmltodb;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLtoDB {
	
	public static void main(String[] args) throws FileNotFoundException, JAXBException, ClassNotFoundException, SQLException {
	
		String JDBCDriver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/login";
	    String userName = "root";
		String passowrd = "root";
		
//		InputStream is = new FileInputStream(new File("D:\\EdUcAtIoN\\temp\\login.xml"));
		JAXBContext context = JAXBContext.newInstance(Login.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Login login = (Login) unmarshaller.unmarshal(new File("D:\\EdUcAtIoN\\temp\\login.xml"));

		// Load Database Driver.
		Class.forName(JDBCDriver);

		// get a connection.
		Connection con = DriverManager.getConnection(url, userName, passowrd);

		// Execute query.
		Statement statement = con.createStatement();
		String query = "insert into login(uname, password) values ('" + login.getUsername() + "', '"
				+ login.getPassword() + "');";
		boolean row = statement.execute(query);
		System.out.println("Operation successfull !!");

	}
}
