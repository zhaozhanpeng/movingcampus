package edu.hebtu.movingcampus.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import edu.hebtu.movingcampus.config.Constants;

public class TimeUtil {
	private static SharedPreferences mPreferences;
	private Long mUpdateDateTime;
	private String key;

	public TimeUtil(Activity ac, String key) {
		mPreferences = ac.getSharedPreferences(Constants.PREFER_FILE,
				Context.MODE_PRIVATE);
		this.mUpdateDateTime = 0L;
		this.key = key;
	}

	/** 获取channel的刷新时间 **/
	public long getUpdateTime() {
		if (mUpdateDateTime == 0)
			return mUpdateDateTime = mPreferences.getLong(Constants.PREFER_FILE
					+ "_" + key, 0);
		return mUpdateDateTime;
	}

	/** 设置channel的刷新时间 **/
	private void setUpdateDateTime(long nowTime) {
		if (mPreferences.edit()
				.putLong(Constants.PREFER_FILE + "_" + key, nowTime).commit()) {
			mUpdateDateTime = nowTime;
		}
	}

	public void updatePreferenceTime() {
		setUpdateDateTime(System.currentTimeMillis());
	}

	public boolean shouldUpdate(Long during) {
		if (System.currentTimeMillis() - getUpdateTime() > during)
			return true;
		return false;
	}
}
