package ken.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ken.bean.Item;
import ken.bean.User;

public class OrderDAO {
	 private Connection connection;
	 private PreparedStatement p_statement_selectOrderID;
	 private PreparedStatement p_statement_selectUserID;
	 private PreparedStatement p_statement_insertUser;
	 private PreparedStatement p_statement_insertOrder;

	 public OrderDAO( ) throws ClassNotFoundException, SQLException{
		 /*
			MySQLへ接続
		  */
		 Class.forName("com.mysql.jdbc.Driver");
		 String url = "jdbc:mysql://localhost:3306/latte_station?useSSL=false";
		 String user = "root";
		 String password = "root";
		 connection = DriverManager.getConnection(url, user, password);

		 /*
			PrepareStatementの利用。
			最初に枠となるSQLを設定する。
		  */
		 // ?(INパラメータ)のところは、後から設定できる。
		 String sql1 = "INSERT INTO latte_station.user (user_id,user_name,user_name_kana,post,address,phone,mail) VALUES (?,?,?,?,?,?,?)";
		 String sql2 = "INSERT INTO latte_station.orderItem (order_id,user_id,item_id) VALUES (?,?,?)";
		 String sql3 = "SELECT MAX(order_id) as max_o FROM latte_station.orderitem";
		 String sql4 = "SELECT MAX(user_id) as max_u FROM latte_station.user";

		 //ユーザ情報をUser表に登録するためのSQL
		 p_statement_insertUser = connection.prepareStatement(sql1);
		 //orderItem表に、商品情報を登録するためのSQL
		 p_statement_insertOrder = connection.prepareStatement(sql2);
		 //新規OrderID発行のため、現在の一番大きいOrderIDを取得
		 p_statement_selectOrderID = connection.prepareStatement(sql3);
		 //新規ユーザID発行のため、現在の一番大きいユーザIDを取得
		 p_statement_selectUserID = connection.prepareStatement(sql4);
	 }

	 /**
		オーダー確定メソッド
		@param user 購入したユーザ情報を持つUserオブジェクト
		@param item 購入したItemオブジェクトを複数格納したArrayListを受け取る
		@return OrderIDを発行して返す
	  */
	 public int orderRegistration(User user, ArrayList<Item> item) throws Exception {
		 ResultSet rs_order = null;
		 ResultSet rs_user = null;

		 int orderID=0;

		 try {
			 /*
				OrderIDの発行をする
			*/
			 rs_order = p_statement_selectOrderID.executeQuery();
			 if (rs_order.next()) {
				 //一番最後のOrderIDを取得
				 orderID = rs_order.getInt("max_o");
				//1加算して、次のOrderIDを作成
				 orderID++;
			 } else {
				 throw new Exception("オーダーに失敗しました");
			 }
			 /*
				ユーザIDを発行をする
			  */
			 rs_user = p_statement_selectUserID.executeQuery();
			 int userID=0;
			 if (rs_user.next()) {
				 //一番最後のOrderIDを取得
				 userID = rs_user.getInt("max_u");
				 //1加算して、次のOrderIDを作成
				 userID++;
			 } else {
				 throw new Exception("オーダーに失敗しました");
			 }
			 /*
				User情報を登録
			  */
			 // ?(INパラメータ)に、Userオブジェクトの値を設定
			 p_statement_insertUser.setInt(1,userID);   //user_id
			 p_statement_insertUser.setString(2,user.getUserName());   //userName
			 p_statement_insertUser.setString(3,user.getUserName_kana());   //userName_kana
			 p_statement_insertUser.setString(4,user.getPostCode());   //postCode
			 p_statement_insertUser.setString(5,user.getAddress());   //address
			 p_statement_insertUser.setString(6,user.getPhoneNumber());   //phoneNumber
			 p_statement_insertUser.setString(7,user.getMailAddress());   //mailAddress

			 //ユーザ情報をUser表に登録
			 p_statement_insertUser.executeUpdate();

       /*
       購入Item情報をorderItem表に登録
     */
			 for (int i=0; i<item.size(); i++) {
				 // ?(INパラメータ)に、Itemオブジェクトの値を設定
				 //オーダーIDに関しては、1回の発注は同じ番号で登録。
				 p_statement_insertOrder.setInt(1,orderID);    //orderID
				 p_statement_insertOrder.setInt(2,userID);    //userID
				 p_statement_insertOrder.setInt(3, ((Item)item.get(i)).getItemID());    //itemID

				 /*
					Itemオブジェクトの情報をorderItem表にインサート！
				  */
				 p_statement_insertOrder.executeUpdate();
			 }
		 } finally {
			 //切断処理
			 if (rs_order != null) rs_order.close();
			 if (rs_user != null) rs_user.close();
			 if (p_statement_insertUser != null) p_statement_insertUser.close();
			 if (p_statement_insertOrder != null) p_statement_insertOrder.close();
			 if (p_statement_selectOrderID != null) p_statement_selectOrderID.close();
			 if (connection != null) connection.close();
		 }
		 return orderID;
	 }
}