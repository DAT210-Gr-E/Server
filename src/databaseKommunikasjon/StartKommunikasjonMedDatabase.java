package databaseKommunikasjon;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StartKommunikasjonMedDatabase 
{
	public StartKommunikasjonMedDatabase()
	{

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

		}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
