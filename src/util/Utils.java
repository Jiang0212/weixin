package util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
	
	/**
	 * 向指定的目的地址发送post请求,附带data数据
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url,String data) {
		try {
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			//要发送数据，必须设置为可发送数据的状态
			connection.setDoOutput(true);
			//获取输出流
			OutputStream os = connection.getOutputStream();
			//写出数据
			os.write(data.getBytes());
			os.close();
			//获取输入流
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			StringBuilder sb= new StringBuilder();
			while ((len=is.read(b))!=-1) {
				sb.append(new String(b,0,len));
			}
			is.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 向指定的目的地址发送get请求
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		try {
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len;
			StringBuilder sb= new StringBuilder();
			while ((len=is.read(b))!=-1) {
				sb.append(new String(b,0,len));
			}
			return sb.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
