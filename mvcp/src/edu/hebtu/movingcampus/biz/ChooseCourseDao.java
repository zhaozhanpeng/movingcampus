/**
 * 
 */
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
import edu.hebtu.movingcampus.entity.ChooseCourseEntity;
import edu.hebtu.movingcampus.entity.ClassRoom;
import edu.hebtu.movingcampus.entity.CourseEntity;
import edu.hebtu.movingcampus.entity.CourseIntroEntity;
import edu.hebtu.movingcampus.entity.TextBookInfo;
import edu.hebtu.movingcampus.utils.RequestCacheUtil;
import edu.hebtu.movingcampus.utils.Utility;

/**
 * @author leijie
 *@aim 用来连接选课系统
 */
public class ChooseCourseDao extends BaseDao {

	public ChooseCourseDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChooseCourseDao(Activity activity) {
		super(activity);
		System.out.println("进入ChooseCourseDao");
		// TODO Auto-generated constructor stub
	}
	/*
	 * @aim 从服务器获取课程的详细情况
	 */
	public List<CourseEntity> getCourseMsg(boolean useCache,String courseId){
		ArrayList<CourseEntity> balanceBean;
		try {
			String result;
			result = RequestCacheUtil.getRequestContentByPost(
					mActivity,
					String.format(Urls.COURSE_INTRO, courseId)
							+ Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			balanceBean = mObjectMapper.readValue(result,
					new TypeReference<ArrayList<CourseEntity>>() {
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
	/*
	 * @aim 从服务器获取课程的详细情况
	 */
	public List<ChooseCourseEntity> getChooseCourseMsg(boolean useCache,String courseId){
		ArrayList<ChooseCourseEntity> balanceBean;
		try {
			String result;
			result = RequestCacheUtil.getRequestContentByPost(
					mActivity,
					String.format(Urls.COURSE_CHOOSE, courseId)
							+ Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			balanceBean = mObjectMapper.readValue(result,
					new TypeReference<ArrayList<ChooseCourseEntity>>() {
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
	/*
	 * @aim 从服务器获取教科书的详细情况
	 */
	public TextBookInfo getBookMsg(boolean useCache,String bookOrderId){
		TextBookInfo balanceBean;
		System.out.println("进入获取教科书信息的方法");
		try {
			String result;
			result = RequestCacheUtil.getRequestContentByPost(
					mActivity,
					String.format(Urls.BOOK_INTRO, bookOrderId)
							+ Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_content, useCache);
			balanceBean = mObjectMapper.readValue(result,
					new TypeReference<TextBookInfo>() {
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
	/*
	 * @aim 从服务器获取课程的详细情况
	 */
	public CourseIntroEntity getCourseIntroMsg(boolean useCache,String courseId){
		CourseIntroEntity balanceBean;
		try {
			String result;
			result = RequestCacheUtil.getRequestContentByPost(
					mActivity,
					String.format(Urls.COURSE_BRUNCH, courseId)
							+ Utility.getScreenParams(mActivity),
					Constants.WebSourceType.Json,
					Constants.DBContentType.Content_list, useCache);
			balanceBean = mObjectMapper.readValue(result,
					new TypeReference<CourseIntroEntity>() {
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
