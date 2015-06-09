package org.jack.test.login;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class OsChinaNetLogin {
	private HttpCompoentsUtil httpUtil = new HttpCompoentsUtil();
	/**
	 * ��ʼ��url
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
		// �鿴���
		lession.doQuery();
	}
	public void printCookies(){
		httpUtil.printCookies();
	}

	public void initCookie() {
		System.out.println("---��ʼ��Cookie---");
		httpUtil.get(initCookieURL, "UTF-8");
		httpUtil.printCookies();
	}

	public void login() {
		System.out.println("---��¼---");
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", "zj_ren8@163.com");
		//������js���ܹ�����ҿ�������һ��������˺� ������ȷ������ �ҵ����ܺ�� ������ʽ�����о�
		params.put("pwd", "askfish");
		params.put("verifyCode", "");
		params.put("save_login", "1");
		String result = httpUtil.post(loginURL, params);
		System.out.println(result);
		httpUtil.printCookies();
	}

	public void doQuery() {
		System.out.println("---�鿴��ǰ��¼��Ϣ---");
		String html = httpUtil.get("http://my.oschina.net/");
		Document doc = Jsoup.parse(html);
		System.out.println(doc.body().toString());
		String mySpace = doc.getElementById("OSC_Userbar").text();
		System.out.println(mySpace);
	}

}
