package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import com.baidu.aip.ocr.AipOcr;
import com.thoughtworks.xstream.XStream;

import entify.AccessToken;
import entify.Article;
import entify.BaseMessage;
import entify.ImageMessage;
import entify.JsApiTicket;
import entify.MusicMessage;
import entify.NewsMessage;
import entify.TextMessage;
import entify.VideoMessage;
import entify.VoiceMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.Utils;

public class wxService {

	//配置你申请的KEY
	public static final String APPKEY = "cb98a23af9bec4941fd9d2a459f44b90";
	public static final String TOKEN = "jiang";
	//微信公众号
	public static final String APPID = "wx7bede3a3d797d979";
	public static final String APPSECRET = "85462b787edd0ce6b9a59bdfe9c9399d";
	public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//设置百度AI的APPID/AK/SK
	public static final String APP_ID = "18118406";
	public static final String API_KEY = "Z8NbiHUFEhdGchdamv8MtI98";
	public static final String SECRET_KEY = "F3sAgcyQOqPQslPIYjEf6dNgCFqo4H25";
	//用于存储对象
	private static AccessToken at;
	private static JsApiTicket jst;
	
	/**
	 * 获取jsapi_ticket对象
	 * @return
	 */
	private static void getjsTicket() {
		String at = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+at+"&type=jsapi";
		String ticketStr = Utils.get(url);	
		System.out.println(ticketStr);
		JSONObject jsonObject = JSONObject.fromObject(ticketStr);
		String ticket = jsonObject.getString("ticket");
		String expireIn = jsonObject.getString("expires_in");
		//创建jsapiTicket对象，并存起来
		jst = new JsApiTicket(ticket, expireIn);
	}
	
	/**
	 * 向外暴露的获取jsapiticket的方法
	 * @return
	 */
	public static String getJsApiTicket() {
		if(jst==null || jst.isExpired()) {
			getjsTicket();
		}
		return jst.getTicket();
	}
	
	/**
	 * 获取token对象
	 */
	private static void getToken() {
		String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String tokenStr = Utils.get(url);
		JSONObject jsonObject = JSONObject.fromObject(tokenStr);
		String token = jsonObject.getString("access_token");
		String expireIn = jsonObject.getString("expires_in");
		//创建token对象，并存起来
		at = new AccessToken(token, expireIn);
	}

	/**
	 * 向外暴露的获取token的方法
	 * @return
	 */
	public static String getAccessToken() {
		if(at==null || at.isExpired()) {
			getToken();
		}
		return at.getAccessToken();
	}
	
	/**
	 * 接入验证
	 * 
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	public static boolean check(String timestamp, String nonce, String signature) {

		// 1）将token、timestamp、nonce三个参数进行字典序排序
		String[] array = new String[] { TOKEN, timestamp, nonce };
		Arrays.sort(array);
		// 2）将三个参数字符串拼接成一个字符串进行sha1加密
		String str = array[0] + array[1] + array[2];
		String msg = sha1(str);
		System.out.println(msg);
		System.out.println(signature);
		// 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		return msg.equals(signature);
	}

	private static String sha1(String str) {
		// 获取一个加密对象
		try {
			MessageDigest md = MessageDigest.getInstance("sha1");
			// 加密
			byte[] digest = md.digest(str.getBytes());
			char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
					'f', };
			StringBuilder sb = new StringBuilder();
			// 处理加密结果
			for (byte b : digest) {
				// 将高4位转换为16进制字符
				sb.append(chars[(b >> 4) & 15]);
				// 将低四位转换为16进制字符
				sb.append(chars[b & 15]);
			}
			return sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	/**
	 * 解析XML数据包
	 * 
	 * @param is
	 * @return
	 */
	public static Map<String, String> parseRequest(InputStream is) {
		Map<String, String> map = new HashMap<>();
		SAXReader reader = new SAXReader();
		try {
			// 获取输入流，获取文档对象
			Document document = reader.read(is);
			// 根据文档对象获取根节点
			Element root = document.getRootElement();
			// 获取根节点的所有的子节点
			java.util.List<Element> elements = root.elements();
			for (Element e : elements) {
				map.put(e.getName(), e.getStringValue());
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 用于处理所有的消息和事件的回复
	 * 
	 * @param requestMap
	 * @return 返回的是XML数据包
	 */
	public static String getResponse(Map<String, String> requestMap) {
		BaseMessage  msg = null;
		String msgType = requestMap.get("MsgType");
		switch (msgType) {
		//处理文本 消息 
		case "text":
			msg = dealTextMessage(requestMap);
			break;
		case "image":
			msg = dealImage(requestMap);
			break;
		case "voice":

			break;
		case "video":

			break;
		case "music":

			break;
		case "news":

			break;
		case "event":
			msg = dealEvent(requestMap);
			break;
		default:
			break;
		}
		//把消息对象转换为xml数据包
		if(msg!=null) {
			return beanToXml(msg);
		}
		return null;		
	}
	
	/**
	 * 进行图片识别
	 * @param requestMap
	 * @return
	 */
	private static BaseMessage dealImage(Map<String, String> requestMap) {
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000); 
        // 调用接口
        String path = requestMap.get("PicUrl");
        //本地图片
        //org.json.JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        //进行网络图片的识别
        org.json.JSONObject res = client.generalUrl(path, new HashMap<String, String>());;
        String json = res.toString();
        //转为jsonObject
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("words_result");
        Iterator<JSONObject> iterator = jsonArray.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
			JSONObject next = iterator.next();
			sb.append(next.getString("words"));
		}
        return new TextMessage(requestMap, sb.toString());
	}

	/**
	 * 用来处理事件推送
	 * @param requestMap
	 * @return
	 */
	private static BaseMessage dealEvent(Map<String, String> requestMap) {
		String event = requestMap.get("Event");
		switch(event) {
		case "scancode_push":
			return dealScancodePush(requestMap);
		case "CLICK":
			return dealClick(requestMap);
		case "VIEW":
			return dealView(requestMap);
		}
		return null;
	}

	private static BaseMessage dealScancodePush(Map<String, String> requestMap) {
		System.out.println(requestMap);
		String info = requestMap.get("ScanCodeInfo");
		System.out.println(info);
		return null;
	}

	//专门用来处理view类型的菜单
	private static BaseMessage dealView(Map<String, String> requestMap) {
		
		return null;
	}

	//专门用来处理click类型的菜单
	private static BaseMessage dealClick(Map<String, String> requestMap) {
		String key = requestMap.get("EventKey");
		switch(key) {
		//点击一菜单
		case "1":
			//处理点击了第一个一级菜单
			String url= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7bede3a3d797d979&redirect_uri=http://jiang.nat300.top/weixin/Redirect&response_type=code&scope=snsapi_userinfo#wechat_redirect";
			TextMessage tm = new TextMessage(requestMap, "点击此处登录，<a href=\""+url+"\">进入商品管理</a>。");
			return tm;
		case "32":
			//处理点击了第三个二级菜单
			return new TextMessage(requestMap, "你点击了第三个二级菜单");
		}
		return null;
	}

	/**
	 * 把消息对象转换为xml数据包
	 * @param msg
	 * @return
	 */
	private static String beanToXml(BaseMessage msg) {
		XStream stream = new XStream();
		//设置需要处理@XStreamAlias("xml")的类
		stream.processAnnotations(TextMessage.class);
		stream.processAnnotations(ImageMessage.class);
		stream.processAnnotations(VoiceMessage.class);
		stream.processAnnotations(NewsMessage.class);
		stream.processAnnotations(VideoMessage.class);
		stream.processAnnotations(MusicMessage.class);

		String xml = stream.toXML(msg);
		return xml;
	}

	/**
	 * 处理文本消息 
	 * @param requestMap
	 * @return
	 */
	private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
		//用户发来的消息
		String msg = requestMap.get("Content");
		if (msg.equals("图文")) {
			List<Article> articles = new ArrayList<Article>();
			articles.add(new Article("这是图文消息的标题", "这是图文消息的详细介绍", "http://mmbiz.qpic.cn/mmbiz_jpg/dI0tY2qxvKmP1benwk5bfiar6X50CNVGFxP5OcfibuEaNKxGkguqwSviaS1Obdtp4UVZvD04GFUplCdv8eC9eGvrg/0", "http://www.baidu.com"));
			NewsMessage nm = new NewsMessage(requestMap, articles);
			return nm;
		}
		if (msg.equals("登录")) {
			String url= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7bede3a3d797d979&redirect_uri=http://jiang.nat300.top/weixin/GetUserInfo&response_type=code&scope=snsapi_userinfo#wechat_redirect";
			TextMessage tm = new TextMessage(requestMap, "点击<a href=\""+url+"\">这里</a>登录");
			return tm;
		}
		TextMessage tm = new TextMessage(requestMap, msg);
		return tm;
	}
	
	/**
	 * 上传临时素材
	 * @param path	上传文件的路径
	 * @param type	上传文件的类型
	 */
	public static String upload(String path,String type) {
		File file = new File(path);
		String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+getAccessToken()+"&type="+type;
		try {
			URL urlObj = new URL(url);
			//强转为安全连接
			HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
			//设置连接的属性
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			//设置请求头的信息
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("Charset", "utf-8");
			//数据的边界
			String boundary = "----------" + System.currentTimeMillis();
			connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
			//获取输出流
			OutputStream out = new DataOutputStream(connection.getOutputStream());
			//获取文件的输入流
			DataInputStream is= new DataInputStream(new FileInputStream(file));
			//第一部分，头部信息
			//准备信息
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(boundary);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
	                + file.getName() + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			out.write(sb.toString().getBytes("utf-8"));
			System.out.println(sb.toString());
			//第二部分，文件信息
			byte[] b= new byte[1024];
			int len;
			while ((len=is.read(b))!=-1) {
				out.write(b,0,len);
			}
			is.close();
			//第三部分，尾部信息
			String foot = "\r\n--" + boundary + "--\r\n";
			out.write(foot.getBytes("utf-8"));
			out.flush();
			out.close();
			//读取数据
			InputStream is2 = connection.getInputStream();
			StringBuilder resp = new StringBuilder();
			while ((len=is2.read(b))!=-1) {
				resp.append(new String(b,0,len));
			}
			
			return resp.toString();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 获取带参数的二维码的ticket
	 * @return
	 */
	public static String getQrCodeTicket() {
		String at = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+at;
		//生成临时字符串二维码
		String data = "{\"expire_seconds\": 6000, \r\n" + 
				"\"action_name\": \"QR_STR_SCENE\",\r\n" + 
				"\"action_info\": \r\n" + 
				"	{\"scene\": \r\n" + 
				"		{\"scene_str\": \"jiang\"}}}";
		String result = Utils.post(url, data);
		String ticket = JSONObject.fromObject(result).getString("ticket");
		
		return ticket;
	}	
	
	/**
	 * 获取用户基本信息
	 * @return
	 */
	public static String getUserInfo(String openid) {
		String  url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+getAccessToken()+"&openid="+openid+"&lang=zh_CN";
		String result = Utils.get(url);
		return result;
	}
	
}

	
	/**
	 *
	 * 调用聊天机器人
	 * @param msg 发送的消息
	 * @return
	 */
//	private static String chat(String msg) {
//				String result = null;
//				String url ="http://op.juhe.cn/iRobot/index";//请求接口地址
//				Map params = new HashMap();//请求参数
//					params.put("key",APPKEY);//您申请到的本接口专用apid
//					params.put("info", msg);//要发送给机器人 的字符，不超过30个
//					params.put("dtype", "");//返回的数据格式，json或xml，默认是json
//					params.put("loc", "");//地点 
//					params.put("userid", "");//1-32位 此userid针对自己的每一个用户，用于上下文的关联
//				try {
//					result=util.net(url, params, "GET");
//	
//					//解析json
//					 JSONObject object = JSONObject.fromObject(result);
//					 if (object.getInt("err_code")==0) {
//							return null;
//					}
//					 //取出返回的消息内容
//					 String resp = JSONObject.getJSONObject("result").getString("text");
//					 return resp;
//						
//					
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				return null;
//		}
//	}
	
	

