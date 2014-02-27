package edu.hebtu.movingcampus.biz.base;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.type.TypeReference;

import android.annotation.SuppressLint;
import android.app.Activity;
import edu.hebtu.movingcampus.utils.LogUtil;

@SuppressLint("NewApi")
public abstract class BaseDao {
	protected Activity mActivity;
	protected static final ObjectMapper mObjectMapper = new ObjectMapper() {
		@Override
		public Object readValue(String content, TypeReference ref) {
			try {

				LogUtil.w("basedao", content.toString());
				return super.readValue(content, ref);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	};

	static {
		mObjectMapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		mObjectMapper.setDateFormat(new SimpleDateFormat("MM/dd HH:mm"));
	}

	public BaseDao() {
	};

	public static ObjectMapper getObjectMapper() {
		return mObjectMapper;
	}

	public BaseDao(Activity activity) {
		mActivity = activity;
	}
}
