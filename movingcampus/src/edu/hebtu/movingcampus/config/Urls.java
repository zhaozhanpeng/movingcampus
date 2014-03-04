package edu.hebtu.movingcampus.config;

public class Urls {

	// javaee请求路径,文件
	// 184.22.242.106:/var/www/ee/server.war
	// public static final String BASE = "http://10.7.84.6:8080/server/";/
	// public static final String BASE = "http://127.0.0.1:8080/server/";
	public static final String BASE = "http://184.22.242.106/server";
	// use login 获取用户信息接口,name,pswd 有session可免
	public static final String USER_LOGIN = BASE + "/login?name=%s&pswd=%s";
	// 用户请求某种课程详细信息时候使用 url
	public static final String COURSE_INTRO = BASE
			+ "/choosecourseintro?courseId=%s";
	// 用户请求某种选课方式的具体内容使用 url
	public static final String COURSE_CHOOSE = BASE
			+ "/choosecourseitem?courseId=%s";
	// 用户请求某种选课方式的具体内容使用 url
	public static final String COURSE_BRUNCH = BASE
			+ "/choosecoursebrunch?courseId=%s";
	// 用户请求某种选课方式的具体内容使用 url
	public static final String BOOK_INTRO = BASE
			+ "/choosecoursebook?bookOrderId=%s";
	// 信息中心,get
	// 新闻请求格式 /news?type=1&from=10&size=20
	public static final String NEWS_LIST = BASE + "/" + Constants.TAGS.NEWS
			+ "?type=%s&from=%s&size=%s";
	// searchURL,type=0为所有类型 1-...所对应类型
	public static final String NEWS_SEARCH = BASE + "/" + Constants.TAGS.NEWS
			+ "?type=%s&keyword=%s";
	public static final String NEWS_MORE = BASE + "/" + Constants.TAGS.NEWS
			+ "?type=" + Constants.NEWS_MORE + "&id=%s";// +screen params;
	// 新闻标题图片
	public static final String NEWS_HEAD = BASE + "/" + Constants.TAGS.NEWS
			+ "?type=" + Constants.NEWS_HEADPIC + "&id=%s";// +screen params;

	// post
	// 学习资源,
	// 考试成绩
	public static final String EXAM_SCORE_CONSULT = BASE + "/"
			+ Constants.TAGS.EXAM + "?type=score&xn=%s&xq=%s&coursetype=%s";
	// 考试信息
	public static final String EXAM_SCHEDULE = BASE + "/" + Constants.TAGS.EXAM
			+ "?type=infor";
	// xiqu校区 jz:哪栋建筑 zc:周次1-18?默认本周 xq:星期1-7 jc:节次1-2..
	public static final String FREEROOM_SEARCH = BASE + "/"
			+ Constants.TAGS.ROOM
			+ "?school=%s&building=%s&week=%s&weekday=%s&classes=%s";
	// domain :课表种类,班级，个人,
	public static final String COURSE_TABLE = BASE + "/"
			+ Constants.TAGS.COURSE + "?xn=%s&xq=%s&domain=%s";
	// 申请占用教室？
	// 查全校课程?

	// 掌上图书馆days天内到期的书的列表
	public static final String BOOKS_SEARCH = BASE + "/"
			+ Constants.TAGS.IIBRARY + "?days=%s";

	// 校已有图书馆网站,jsp路径
	public static final String LIB_URL = "http:///202.206.108.20:8080/sms/opac/search/showSearch.action?xc=6";
	public static final String LIB_POST_LOGIN = "http://mc.m.5read.com/user/login/showLogin.jspx";
	public static final String LIB_POST_REGIST = "http://mc.m.5read.com/irdUser/login/opac/opacLogin.jspx";

	// 我的一卡通, action=lookup|lock|unlock->Constance
	public static final String CARD_STATUS = BASE + "/" + Constants.TAGS.CARD
			+ "?action=%s";
	
	//domain=personcourse,type=updatetime;...-->time
	//domain=message---->取出自己对应的信息,type=0全部
	public static final String QUERY_VERSION = BASE+ "/"+ Constants.TAGS.DATA_VERSION + "?domain=%s&type=%s";

	//query message after the time
	public static final String QUERY_MESSAGE = BASE+ "/"+ Constants.TAGS.MESSAGE + "size=%s";
	
	//post 铁通流量
	public static final String QUERY_TIETONG_FLOW  =  BASE+"/"+Constants.TAGS.COMMON + "?domain=tietong"+"logName=%s&password=%s&years=%s&months=%s";
}
