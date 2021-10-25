package ken.act;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ken.bean.Item;
import ken.dao.SearchDAO;

public class SearchAction extends Action {

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		String key = request.getParameter("keyword");
		String genre = request.getParameter("genre");
		SearchDAO dao = new SearchDAO();
		ArrayList<Item> items = dao.search_table(key, genre);
		HttpSession session = request.getSession(true);
		session.setAttribute("table_items", items);
		if( items == null || items.size() == 0 ) {
			request.setAttribute("no_item",  "");
			request.setAttribute("message", "該当する商品はありません");
		}

		return "/top.jsp";
	}

}
