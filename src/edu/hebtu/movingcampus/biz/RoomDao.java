package edu.hebtu.movingcampus.biz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import android.app.Activity;
import edu.hebtu.movingcampus.biz.base.BaseDao;
import edu.hebtu.movingcampus.config.Constants;
import edu.hebtu.movingcampus.config.Urls;
import edu.hebtu.movingcampus.entity.ClassRoom;
import edu.hebtu.movingcampus.utils.CustomHttpClient;
import edu.hebtu.movingcampus.utils.RequestCacheUtil;
import edu.hebtu.movingcampus.utils.Utility;

public class RoomDao extends BaseDao {
	public RoomDao(Activity activity) {
		super(activity);
	}

	/*
	 * return true success false otherwise
	 */

	public List<ClassRoom> getFreeRoomMsg(boolean useCache, String school,
			String building, String week, String weekday, String classes) {
		ArrayList<ClassRoom> balanceBean;
		try {
			String result;
			result = RequestCacheUtil.getRequestContentByPost(
					mActivity,
					String.format(Urls.FREEROOM_SEARCH, school, building, week,
							weekday, classes)
							+ Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			balanceBean = mObjectMapper.readValue(result,
					new TypeReference<ArrayList<ClassRoom>>() {
					});
			if (balanceBean == null) {
				return null;
			}
			return balanceBean;

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
