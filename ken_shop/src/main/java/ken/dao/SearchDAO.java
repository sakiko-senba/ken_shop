package ken.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ken.bean.Item;

public class SearchDAO {
	private Connection connection;
	private PreparedStatement p_statement_selectItems_no_key;
	private PreparedStatement p_statement_selectItems;

	public SearchDAO() throws ClassNotFoundException, SQLException {
		Class.forName( "com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/latte_station?useSSL=false";
		String usr = "root";
		String psw = "root";
		connection = DriverManager.getConnection( url, usr, psw );
		String s1 = "SELECT * FROM  latte_station.item WHERE genre_id=?";
		String s2 = "SELECT * FROM  latte_station.item WHERE genre_id=?"
				+ "and (item_name LIKE ? OR artist LIKE ?)";
		p_statement_selectItems_no_key = connection.prepareStatement(s1);
		p_statement_selectItems = connection.prepareStatement(s2);
	}

	public ArrayList<Item> search_table( String key, String genre ) throws SQLException {
		ResultSet rs_items = null;

		if( !key.isEmpty() ) {
			p_statement_selectItems.setString(1, genre);
			p_statement_selectItems.setString(2, "%" + key + "%");
			p_statement_selectItems.setString(3, "%" + key + "%");
			rs_items = p_statement_selectItems.executeQuery();
		} else {
			p_statement_selectItems_no_key.setString(1, genre);
			rs_items = p_statement_selectItems_no_key.executeQuery();
		}

		ArrayList<Item> list = new ArrayList<>();

		while( rs_items.next() ) {
			Item item = new Item();

			item.setItemID(rs_items.getInt("item_id"));
			item.setItemName(rs_items.getString("item_name"));
			item.setItemArtist(rs_items.getString("artist"));
			item.setItemPrice(rs_items.getInt("price"));
			item.setItemImage(rs_items.getString("item_img"));
			list.add(item);
		}

		if( rs_items!= null ) {
			rs_items.close();
		}

		if( connection != null ) {
			connection.close();
		}

		return list;
	}
}
