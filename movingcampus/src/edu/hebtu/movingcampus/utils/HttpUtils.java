package edu.hebtu.movingcampus.utils;

import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.util.Log;

public class HttpUtils {
	// 网络连接部分
	public static String postByHttpURLConnection(String strUrl,
			NameValuePair... nameValuePairs) {
		if (nameValuePairs != null) {
			Set<Entry<String, String>> set = ToolUtils.URLRequest(strUrl)
					.entrySet();
			NameValuePair[] newNameValuePairs = new NameValuePair[set.size()
					+ nameValuePairs.length];
			System.arraycopy(nameValuePairs, 0, newNameValuePairs, 0,
					nameValuePairs.length);
			int i = 0;
			for (final Entry<String, String> e : set) {
				newNameValuePairs[++i + nameValuePairs.length] = new NameValuePair() {
					@Override
					public String getValue() {
						return e.getValue();
					}

					@Override
					public String getName() {
						return e.getKey();
					}
				};
			}
			return CustomHttpURLConnection.postByHttpURLConnection(strUrl,
					newNameValuePairs);
		} else {
			return CustomHttpURLConnection.postByHttpURLConnection(strUrl,
					nameValuePairs);
		}
	}

	public static String getByHttpURLConnection(String strUrl,
			NameValuePair... nameValuePairs) {
		if (nameValuePairs != null)
			for (NameValuePair nameValuePair : nameValuePairs) {
				strUrl += "&" + nameValuePair.getName() + "="
						+ nameValuePair.getValue();
			}
		return CustomHttpURLConnection.getByHttpUrlConnection(strUrl,
				nameValuePairs);
	}

	public static String postByHttpClient(Context context, String strUrl,
			NameValuePair... nameValuePairs) {
		if (nameValuePairs != null) {
			Set<Entry<String, String>> set = ToolUtils.URLRequest(strUrl)
					.entrySet();
			NameValuePair[] newNameValuePairs = new NameValuePair[set.size()
					+ nameValuePairs.length];
			System.arraycopy(nameValuePairs, 0, newNameValuePairs, 0,
					nameValuePairs.length);
			int i = 0;
			for (final Entry<String, String> e : set) {
				newNameValuePairs[++i + nameValuePairs.length] = new NameValuePair() {
					@Override
					public String getValue() {
						return e.getValue();
					}

					@Override
					public String getName() {
						return e.getKey();
					}
				};
			}
			return CustomHttpURLConnection.postByHttpURLConnection(strUrl,
					newNameValuePairs);
		} else {
			return CustomHttpClient.postByHttpClient(context, strUrl,
					nameValuePairs);
		}
	}

	public static String getByHttpClient(Context context, String strUrl,
			NameValuePair... nameValuePairs) throws Exception {
		if (nameValuePairs != null)
			for (NameValuePair nameValuePair : nameValuePairs) {
				strUrl += "&" + nameValuePair.getName() + "="
						+ nameValuePair.getValue();
			}
		return CustomHttpClient
				.getByHttpClient(context, strUrl, nameValuePairs);
	}

	// ------------------------------------------------------------------------------------------
	// 网络连接判断
	// 判断是否有网络
	// public static boolean isNetworkAvailable(Context context) {
	// return NetWorkHelper.isNetworkAvailable(context);
	// }

	// 判断mobile网络是否可用
	public static boolean isMobileDataEnable(Context context) {
		String TAG = "httpUtils.isMobileDataEnable()";
		try {
			return NetWorkHelper.isMobileDataEnable(context);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 判断wifi网络是否可用
	public static boolean isWifiDataEnable(Context context) {
		String TAG = "httpUtils.isWifiDataEnable()";
		try {
			return NetWorkHelper.isWifiDataEnable(context);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// 设置Mobile网络开关
	public static void setMobileDataEnabled(Context context, boolean enabled) {
		String TAG = "httpUtils.setMobileDataEnabled";
		try {
			NetWorkHelper.setMobileDataEnabled(context, enabled);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
	}

	// 判断是否为漫游
	public static boolean isNetworkRoaming(Context context) {
		return NetWorkHelper.isNetworkRoaming(context);
	}
}
