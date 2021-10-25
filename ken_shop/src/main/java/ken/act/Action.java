package ken.act;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
	//抽象メソッド
	abstract public String execute(HttpServletRequest request) throws Exception;
}