package databaseKommunikasjon;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class StartKommunikasjonMedDatabase
{
	

	public StartKommunikasjonMedDatabase()
	{

		
		Statement setning;
		ResultSet resultat;
		String sporring;
		String title;
		

		try {
			Connection kobling = DriverManager.getConnection("jdbc:mysql://168.61.96.122:7000/test", "user", "root" );
		{
			sporring = "select * from example_autoincrement";
			setning = kobling.createStatement();
			resultat = setning.executeQuery(sporring);
			while(resultat.next())
			{
				title = resultat.getString(1);
				System.out.println(title);
			}

		}
		
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
class SSHUserInfo implements UserInfo
{

	@Override
	public String getPassphrase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return "78rjmxkb";
	}

	@Override
	public boolean promptPassphrase(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptPassword(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean promptYesNo(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showMessage(String arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
