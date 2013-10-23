package databaseKommunikasjon;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

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
			session.connect();
			session.setPortForwardingL(3307,host,3306);
		} catch (JSchException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Statement setning;
		ResultSet resultat;
		String sporring;
		String title;
		try {Connection kobling = DriverManager.getConnection("jdbc:mysql://mysql.ux.uis.no/dbmortenno", "mortenno", "eu5pkp4r"); {
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
