package ken.act;

import javax.servlet.http.HttpServletRequest;

public class TopAction extends Action {

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		return "/top.jsp";
	}

}
