package org.jack.test.login;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class OsChinaNetLogin {
	private HttpCompoentsUtil httpUtil = new HttpCompoentsUtil();
	/**
	 * 初始化url
	 */
	private String initCookieURL = "https://www.oschina.net/action/user/captcha";

	private String loginURL = "https://www.oschina.net/action/user/hash_login";

	/**
	 * @param args
	 * @throws
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		OsChinaNetLogin lession = new OsChinaNetLogin();

		lession.initCookie();
		lession.printCookies();
		lession.login();
		// 查看结果
		lession.doQuery();
	}
	public void printCookies(){
		httpUtil.printCookies();
	}

	public void initCookie() {
		System.out.println("---初始化Cookie---");
		httpUtil.get(initCookieURL, "UTF-8");
		httpUtil.printCookies();
	}

	public void login() {
		System.out.println("---登录---");
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", "zj_ren8@163.com");
		//密码有js加密过，大家可以先用一个错误的账号 输入正确的密码 找到加密后的 其他方式自行研究
		params.put("pwd", "askfish");
		params.put("verifyCode", "");
		params.put("save_login", "1");
		String result = httpUtil.post(loginURL, params);
		System.out.println(result);
		httpUtil.printCookies();
	}

	public void doQuery() {
		System.out.println("---查看当前登录信息---");
		String html = httpUtil.get("http://my.oschina.net/");
		Document doc = Jsoup.parse(html);
		System.out.println(doc.body().toString());
		String mySpace = doc.getElementById("OSC_Userbar").text();
		System.out.println(mySpace);
	}

}
