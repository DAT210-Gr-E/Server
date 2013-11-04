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
	String user = "mortenno";
	String host = "ssh.ux.uis.no";
	JSch jsch = new JSch();
	Session session; 


	public StartKommunikasjonMedDatabase()
	{

		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session = jsch.getSession(user,host,22);
			session.setPassword("78rjmxkb");
			session.setConfig(config);
			UserInfo info = new SSHUserInfo();
			session.setUserInfo(info);
			session.connect();
			session.setPortForwardingL("localhost",3005,host,3306);
			
			System.out.println("SSH done");
			
		} catch (JSchException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Statement setning;
		ResultSet resultat;
		String sporring;
		String title;
		

    	Properties prop = new Properties();
    	prop.setProperty("User", "mortenno");
    	prop.setProperty("Password", "eu5pkp4r");
    	prop.setProperty("localSocketAddress", "3005");

		System.out.println(session.isConnected());
		try {Connection kobling = DriverManager.getConnection("jdbc:mysql://mysql.ux.uis.no/dbmortenno", prop);{
			sporring = "select * from TagTable";
			setning = kobling.createStatement();
			resultat = setning.executeQuery(sporring);
			while(resultat.next()){
				title = resultat.getString(1);
				System.out.println(title);
			}

		}
		} catch (SQLException e) {
			session.disconnect();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.disconnect();
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
