package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.baidu.aip.ocr.AipOcr;
import com.thoughtworks.xstream.XStream;

import entify.AbstractButton;
import entify.AccessToken;
import entify.Button;
import entify.ClickButton;
import entify.ImageMessage;
import entify.MusicMessage;
import entify.NewsMessage;
import entify.PhotoOrAlbumButton;
import entify.SubButton;
import entify.TextMessage;
import entify.VideoMessage;
import entify.ViewButton;
import entify.VoiceMessage;
import net.sf.json.JSONObject;
import service.wxService;

public class TestWx {
	
	//设置APPID/AK/SK
	public static final String APP_ID = "18118406";
    public static final String API_KEY = "Z8NbiHUFEhdGchdamv8MtI98";
    public static final String SECRET_KEY = "F3sAgcyQOqPQslPIYjEf6dNgCFqo4H25";
    
    //https://api.weixin.qq.com/cgi-bin/media/get?access_token=28_LP1NDTwsxm84G-RCjOeoone2Lt9fRQ7orG8Ke-mdlweiLU_Vqo8b7nm7dJxXiMQAhH9PkM4y7P1-lp5Xx9MIqM7nmJmWI9KEvMtCMkV33p10GEbcDrJdNCxH5zH_q9Qv7WNfSNpe_O5cZLZfWEFcAGAJRS&media_id=v1lGVj9RSQBEedV1Fak93pi-Vmn6HZjafn06eb6V6zTNHon2eXCMpHSi6zAy5wmI
    //v1lGVj9RSQBEedV1Fak93pi-Vmn6HZjafn06eb6V6zTNHon2eXCMpHSi6zAy5wmI
    //28_LP1NDTwsxm84G-RCjOeoone2Lt9fRQ7orG8Ke-mdlweiLU_Vqo8b7nm7dJxXiMQAhH9PkM4y7P1-lp5Xx9MIqM7nmJmWI9KEvMtCMkV33p10GEbcDrJdNCxH5zH_q9Qv7WNfSNpe_O5cZLZfWEFcAGAJRS

    @Test
    public void sss() {
    	LocalDateTime s =LocalDateTime.now();
    	System.out.println(s.format(DateTimeFormatter.ISO_DATE));
    }
    
    
    @Test
    public void testUserInfo() {
    	String user = "oeGVqwqHwQr05vt0K42KCyc0DxY4";
    	String result = wxService.getUserInfo(user);
    	System.out.println(result);
    }
    
	@Test
	public void testQrCode() {
		String ticket = wxService.getQrCodeTicket();
		System.out.println(ticket);
	}
    
    
	@Test
	public void TestJSapiTicket() {
		String ticket = wxService.getJsApiTicket();
		System.out.println(ticket);
	}
	
    @Test
    public void test() {
    	System.out.println(wxService.getAccessToken());
    }
    @Test
    public void testUpload() {
    	String file = "C:\\Users\\江sir\\Pictures\\6.jpg";
    	String result = wxService.upload(file, "image");
    	System.out.println(result);
    }    
    
    @Test
	public void testpic() {
		// 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000); 

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
       // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
 
        // 调用接口
        String path = "C:\\Users\\江sir\\Pictures\\javaweb.png";
        org.json.JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
	}

	@Test
	public void testButton() {
		//创建菜单
		Button btn = new Button();
		//第一个一级菜单
		btn.getButton().add(new ClickButton("一级跳转", "1"));
		//第二个一级菜单
		btn.getButton().add(new ViewButton("一级跳转", "http://www.baidu.com"));
		//创建第三个一级菜单
		SubButton sb = new SubButton("有子菜单");
		//第三个一级菜单加入子菜单
		sb.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
		sb.getSub_button().add(new ClickButton("点击", "32"));
		sb.getSub_button().add(new ViewButton("网易新闻", "http://news.163.com"));
		//加入第三个一级菜单
		btn.getButton().add(sb);
		//转为JSON
		JSONObject jsonObject = JSONObject.fromObject(btn);
		System.out.println(jsonObject.toString());
	}
	
	@Test
	public void testToken() {
		System.out.println(wxService.getAccessToken());
		System.out.println(wxService.getAccessToken());   
	}
	
	@Test
	public void testMsg() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ToUserName", "to");
		map.put("FromUserName", "from");
		map.put("MsgType", "type");
		TextMessage tm = new TextMessage(map, "不错");
		
		XStream stream = new XStream();
		//设置需要处理@XStreamAlias("xml")的类
		stream.processAnnotations(TextMessage.class);
		stream.processAnnotations(ImageMessage.class);
		stream.processAnnotations(VoiceMessage.class);
		stream.processAnnotations(NewsMessage.class);
		stream.processAnnotations(VideoMessage.class);
		stream.processAnnotations(MusicMessage.class);

		String xml = stream.toXML(tm);
		
	}
}
