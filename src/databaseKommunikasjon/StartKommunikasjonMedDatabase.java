package databaseKommunikasjon;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StartKommunikasjonMedDatabase
{
	

	public StartKommunikasjonMedDatabase()
	{
		DatabaseMetoder db = new DatabaseMetoder();
		db.RestartDataBaseLinks();
	}
	
}
