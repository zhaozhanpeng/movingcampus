package edu.hebtu.movingcampus.biz;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import android.app.Activity;
import edu.hebtu.movingcampus.biz.base.BaseDao;
import edu.hebtu.movingcampus.config.Constants;
import edu.hebtu.movingcampus.config.Urls;
import edu.hebtu.movingcampus.entity.MMessage;
import edu.hebtu.movingcampus.utils.RequestCacheUtil;
import edu.hebtu.movingcampus.utils.Utility;

public class MessageDao extends BaseDao {
	public MessageDao(Activity activity) {
		super(activity);
	}

	/*
	 * 
	 * new list json mapper
	 */
	public ArrayList<MMessage> mapperJson(boolean useCache, String size) {
		// 默认加载10条新闻
		if (size == null)
			size = "10";
		ArrayList<MMessage> newsJson = null;
		try {
			String result;
			result = RequestCacheUtil.getRequestContentByGet(
					mActivity,
					String.format(Urls.QUERY_MESSAGE, size)
							+ Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			newsJson = mObjectMapper.readValue(result,
					new TypeReference<ArrayList<MMessage>>() {
					});
			if (newsJson == null) {
				return null;
			}
			return newsJson;

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
