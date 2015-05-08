package tw.gov.tainan.tainanwelfare.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import tw.gov.tainan.tainanwelfare.comm.Util;
import android.util.Log;


public class HttpController {
	//private final String BaseUrl = Util.BaseUrl;
	private final int TIME_OUT = Util.TIME_OUT;
	//private static final int BUFFER_SIZE=4096*4096;
	private static HttpURLConnection connection;
	
	public String RequestWebApiGet(String WsPath){
		String msg = "";
		
		try {
			URL url = new URL(WsPath);
			connection = (HttpURLConnection) url.openConnection();
			connection.setUseCaches(false);
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setConnectTimeout(TIME_OUT);
			connection.setReadTimeout(TIME_OUT);
			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String s = null;
			while ((s = bf.readLine()) != null){
				buffer.append(s);
			}
			Log.d("test", "RequestWebApiGet result!");
			Log.d("test", buffer.toString());

			msg = buffer.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg="error";
		}
		
		return msg;
	}
	
	public String RequestWebApiPost(String WsPath,String params){
		// 請求資訊
		Log.d("text","Http Path: " +WsPath);
		try {
			 URL url = new URL(WsPath);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(false);
			connection.setRequestMethod("POST");
			Log.d("text", "RequestMethod: " +connection.getRequestMethod());
			//connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/json; Charset=UTF-8");
			connection.setConnectTimeout(TIME_OUT);
			connection.setReadTimeout(TIME_OUT);
			// 送出請求
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			connection.connect();
			
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			// 開始接收
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// OK
				//char[] data = new char[BUFFER_SIZE];
				BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				StringBuffer buffer = new StringBuffer();
				String s = null;
				while ((s = bf.readLine()) != null){
					buffer.append(s);
				}
				//Log.d("test", "RequestWebApi result!");
				//Log.d("test", buffer.toString());
				//int len = bf.read(data); 
				//String rexml = String.valueOf(data, 0, len);
				return buffer.toString();
			}else if(connection.getResponseCode() == HttpURLConnection.HTTP_SERVER_ERROR){
				BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
				StringBuffer buffer = new StringBuffer();
				String s = null;
				while ((s = bf.readLine()) != null){
					buffer.append(s);
				}
				Log.d("test", "RequestWebApi result!");
				Log.d("test", buffer.toString());
				return buffer.toString();
			}else if(connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED){
				Log.d("test","認證失敗!");
				
				return "認證失敗,{success:false, data:'', message:'認證失敗!'}";
			} else {
				return "{success:false, data:''}";
			}
		}catch (Exception ex){
			ex.printStackTrace();
			Exception kk=ex;
			kk.getMessage();
			return "Exception="+WsPath+";"+ex.toString();
			//return "Exception";
		}
	}
}
