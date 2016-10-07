package utils;

import com.google.gson.Gson;

public class GsonUtil {
	public static String getJsonString(Object obj){
		Gson g=new Gson();
		return g.toJson(obj);
	}
}
