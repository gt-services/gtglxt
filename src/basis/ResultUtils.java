package basis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ResultUtils {
	public static void toJson(HttpServletResponse response, Object data)throws IOException {
			Gson gson = new Gson();
		    String result = gson.toJson(data);
		    response.setContentType("text/html; charset=utf-8");
		    response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存        
		    PrintWriter out = response.getWriter();
	        out.print(result);  
	        out.flush();
	        out.close();
	 }
	
	// 将Json数据解析成相应的映射对象
	public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}
	// 将Json数组解析成相应的映射对象列表
	public static <T> List<T> parseJsonArrayWithGson(String jsonData,
	     Class<T> type) {
	     Gson gson = new Gson();
	     List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>() {
	     }.getType());
	      return result;
	 }

}
