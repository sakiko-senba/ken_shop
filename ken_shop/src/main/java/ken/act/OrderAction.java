package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;
import ken.bean.User;
import ken.dao.OrderDAO;

public class OrderAction extends Action {

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		//リクエストパラメータの取得
		String name = request.getParameter("name");
		String kana_name = request.getParameter("kana_name");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");

		//Bean の作成
		User user = new User();
		user.setUserName(name);
		user.setUserName_kana(kana_name);
		user.setPostCode(postcode);
		user.setAddress(address);
		user.setPhoneNumber(tel);
		user.setMailAddress(mail);

		//セッションチェック
		HttpSession session = request.getSession(false);
		if (session == null) return "/irregular_error.jsp";

		//セッションオブジェクトよりカート情報の取得
		@SuppressWarnings("unchecked")
		ArrayList<Item> items = (ArrayList<Item>)session.getAttribute("cart");

		//注文用のDAOの生成
		OrderDAO odao = new OrderDAO();
		//注文情報の登録
		odao.orderRegistration(user, items);
		//注文情報が登録で来たので、セッションオブジェクトを破棄
		session.invalidate();

		return "/finish.jsp";
	}
}