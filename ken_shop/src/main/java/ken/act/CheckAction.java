package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;

public class CheckAction extends Action {

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession( false );

		if( session == null ) {
			return "/irregular_error.jsp";
		}

		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
		if( cart == null || cart.size() == 0 ) {
			return "/error.jsp";
		}

		return "/cart.jsp";
	}

}
