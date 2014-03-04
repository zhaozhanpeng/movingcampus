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
				newNameValuePairs[i++ + nameValuePairs.length] = new NameValuePair() {
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
			strUrl=ToolUtils.truncateUrlPage(strUrl, true);
			return CustomHttpURLConnection.postByHttpURLConnection(strUrl,
					newNameValuePairs);
		} else {
			strUrl=ToolUtils.truncateUrlPage(strUrl, true);
			return CustomHttpURLConnection.postByHttpURLConnection(strUrl,
					nameValuePairs);
		}
	}

	public static String getByHttpURLConnection(String strUrl,
			NameValuePair... nameValuePairs) {
		if(strUrl.lastIndexOf("?")<0)
			strUrl+="?";
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
				newNameValuePairs[i++ + nameValuePairs.length] = new NameValuePair() {
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
			strUrl=ToolUtils.truncateUrlPage(strUrl, true);
			return CustomHttpClient.postByHttpClient(context,strUrl,
					newNameValuePairs);
		} else {
			strUrl=ToolUtils.truncateUrlPage(strUrl, true);
			return CustomHttpClient.postByHttpClient(context, strUrl,
					nameValuePairs);
		}
	}

	public static String getByHttpClient(Context context, String strUrl,
			NameValuePair... nameValuePairs) throws Exception {

		if(strUrl.lastIndexOf("?")<0)
			strUrl+="?";
		if (nameValuePairs != null)
			for (NameValuePair nameValuePair : nameValuePairs) {
				strUrl += "&" + nameValuePair.getName() + "="
						+ nameValuePair.getValue();
			}
		return CustomHttpClient
				.getByHttpClient(context, strUrl, nameValuePairs);
	}

}
