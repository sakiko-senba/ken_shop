package ken;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ken.act.Action;

/**
 * Servlet implementation class Control
 */
@WebServlet("/kenshop")
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config = null;
	private ServletContext context = null;

	@Override
	public void init() throws ServletException {
		this.config = getServletConfig();
		this.context = config.getServletContext();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actName = ""; //リクエストパラメータ"act"の値
		String actionClassName = ""; //Actionクラスの名前
		Action action = null; //Actionクラスのオブジェクト
		String forwardJSP = ""; //フォワード先のJSP名
		RequestDispatcher rd = null; //ディスパッチャオブジェクト

		request.setCharacterEncoding("UTF-8");

		try {
			String realPath = this.context.getRealPath("/");
			String path = realPath + "/WEB-INF/action.properties";

			FileInputStream stream = new FileInputStream(path);
			Properties props = new Properties();
			props.load(stream);

			actName = request.getParameter("act");

			if (actName != null) {
				actionClassName = props.getProperty(actName);
			} else {
				actionClassName = props.getProperty("top");
			}
			/*debag control */
			System.out.println("debug control act = " + actName);

			Class<?> actionClass = Class.forName(actionClassName);
			action = (Action) actionClass.newInstance();

			forwardJSP = action.execute(request);

			if (forwardJSP != null && !forwardJSP.equals("")) {
				rd = context.getRequestDispatcher(forwardJSP);
			} else {
				rd = context.getRequestDispatcher("/top.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rd = context.getRequestDispatcher("/irregular_error.jsp");
		}
		rd.forward(request, response);
	}
}