package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;

public class For_OrderAction extends Action {

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		//セッションチェック
		HttpSession session = request.getSession(false);
		if (session == null) return "/irregular_error.jsp";

		//セッションオブジェクトよりカート情報の取得
		@SuppressWarnings("unchecked")
		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");

		//カート情報が取得できないか、カートに商品がない場合
		if (cart == null || cart.size() == 0) {
			return "/error.jsp";
		}
		//カート情報が取得でき、カートに商品が1件以上存在する場合
		else {
			return "/order.jsp";
		}
	}
}