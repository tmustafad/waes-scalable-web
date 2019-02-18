package com.rsouza01.waesscalableweb;

public class WaesScalableWebApplicationConstants {

	public static final String JSON_DIFF_REQUEST 		= "{\"base64Content\":\"%s\"}";
	
	public static final String JSON_STRING_1 = "{ \"name\":\"John\", \"age\":30, \"car\":null}";

	public static final String JSON_STRING_2 = "{ \"name\":\"John\", \"age\":30, \"cars\": { \"car1\":\"Ford\", \"car2\":\"BMW\", \"car3\":\"Fiat\" } }";
	public static final String JSON_STRING_3 = "{ \"name\":\"Ana\", \"age\":40, \"cars\": { \"car1\":\"Ford\", \"car2\":\"BMW\" } }";
	
	public static final String JSON_STRING_4_1 = "{ \"name\":\"John\", \"age\":30, \"cars\": { \"car1\":\"Ford\", \"car2\":\"BMW\", \"car3\":\"Fiat\" } }";
	public static final String JSON_STRING_4_2 = "{ \"name\":\"John\", \"age\":35, \"cars\": { \"car1\":\"Ford\", \"car2\":\"BMW\", \"car3\":\"Fiat\" } }";

	public static final String JSON_INVALID = "{ \"name\":, \"age\":35, \"\":  \"car1\":\"Ford\", \"car2\":\"BMW\", \"ca";
}

