package databaseKommunikasjon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.Picture;

public class DatabaseMetoder {


	Statement setning;
	ResultSet resultat;
	boolean okay;
	String sporring;
	String title;

	public void RestartDataBaseLinks()
	{
		Connection kobling;
		try {
			kobling = DriverManager.getConnection("jdbc:mysql://168.61.96.122:7000/test", "user", "root" );

			{
				sporring = "drop table links;";
				setning = kobling.createStatement();
				okay = setning.execute(sporring);
			}
			System.out.println("Table Droppet!");
			{
				sporring = "create table links (	URL varchar(255) primary key);";
				setning = kobling.createStatement();
				okay = setning.execute(sporring);
			}
			System.out.println("Table remade");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		}
	}
	public void addURLs(List<Picture> pictures){
		try {
			Connection kobling = DriverManager.getConnection("jdbc:mysql://168.61.96.122:7000/test", "user", "root" );
			for (int i = 0; i < pictures.size(); i++)
			{


				{
					sporring = "INSERT INTO links (URL) VALUES('" + pictures.get(i).standardURL + "')";
					setning = kobling.createStatement();
					okay = setning.execute(sporring);
				}
				System.out.println("Bilder lagt inn!");

			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addTags(String[] tags){
		try {
			Connection kobling = DriverManager.getConnection("jdbc:mysql://168.61.96.122:7000/test", "user", "root" );
			for (int i = 0; i < tags.length; i++)
			{


				{
					sporring = "INSERT INTO tags (Tag) VALUES('" + tags[i] + "')";
					setning = kobling.createStatement();
					okay = setning.execute(sporring);
				}
				System.out.println("Tags lagt in!");

			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> getTagsFromDatabase() {
		ArrayList<String> Tags = new ArrayList<String>();
		// TODO Auto-generated method stub
		try {
			Connection kobling = DriverManager.getConnection("jdbc:mysql://168.61.96.122:7000/test", "user", "root" );
			{

				sporring = "select * from tags";
				setning = kobling.createStatement();
				resultat = setning.executeQuery(sporring);
				while(resultat.next())
				{
					title = resultat.getString(1);
					System.out.println(title);
					Tags.add(title);

				}


			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Tags;
	}

	public ArrayList<String> getURLsFromDatabase() {
		ArrayList<String> URLs = new ArrayList<String>();
		// TODO Auto-generated method stub
		try {
			Connection kobling = DriverManager.getConnection("jdbc:mysql://168.61.96.122:7000/test", "user", "root" );
			{

				sporring = "select * from links";
				setning = kobling.createStatement();
				resultat = setning.executeQuery(sporring);
				while(resultat.next())
				{
					title = resultat.getString(1);
					System.out.println(title);
					URLs.add(title);

				}


			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return URLs;
	}
}
