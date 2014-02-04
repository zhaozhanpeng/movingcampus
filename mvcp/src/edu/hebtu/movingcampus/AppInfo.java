package edu.hebtu.movingcampus;

import java.io.File;
import java.util.Calendar;

import com.baidu.android.pushservice.apiproxy.PushSettings;
import com.baidu.frontia.FrontiaApplication;

import android.app.Application;
import edu.hebtu.movingcampus.entity.User;
import edu.hebtu.movingcampus.utils.RequestCacheUtil;

/**
 * preferenced data holder extends Application
 * 
 * @author hippo
 * @version 1.0
 * @created 14-Nov-2013 9:13:32 AM
 */
public class AppInfo extends FrontiaApplication{
	// 显示哪一学年
	private static String studyYear = "2013-2014";
	// 显示哪一学期
	private static String term = "2";
	// 会话用户
		private static User user;

		public static File cacheDir;
	
	/**
	 * @return the studyYear
	 */
	public static String getStudyYear() {
		
		return studyYear;
	}

	/**
	 * @param studyYear the studyYear to set
	 */
	public static void setStudyYear(String studyYear) {
		AppInfo.studyYear = studyYear;
	}

	/**
	 * @aim 获得学年，没有参数
	 */
	public static void setStudyYear() {
		Calendar calender = Calendar.getInstance();
		int year = Integer.valueOf(calender.get(Calendar.YEAR));
		int month = Integer.valueOf(calender.get(Calendar.MONTH));
		if(month<8){
			AppInfo.studyYear = (year-1)+"-"+year;
		}else{
			AppInfo.studyYear = year+"-"+(year+1);
		}
	}
	/**
	 * @return the term
	 */
	public static String getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public static void setTerm(String term) {
		AppInfo.term = term;
	}
	
	/**
	 * @aime 获取学期
	 */
	public static void setTerm() {
		Calendar calender = Calendar.getInstance();
		int month = Integer.valueOf(calender.get(Calendar.MONTH));
		if(month<8){
			AppInfo.term = "2";
		}else{
			AppInfo.term = "1";
		}
		
	}


	public static User getUser() {
		return user;
	}


	public static void setUser(User paramUser) {
		user = paramUser;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		cacheDir=new File(getApplicationContext().getCacheDir().getAbsolutePath());

		// if (!CommonUtil.sdCardIsAvailable()) { // sdcard not available
		// cacheDir = new File(Environment.getDataDirectory().getAbsolutePath()
		// + "/data/" + getPackageName()
		// + "/eoecn/cache/imgs");
		// } else {
		// cacheDir = new File(Constants.CachePath.IMAGE_CACHE_PATH);
		// }
		//

		// ImageLoaderConfiguration config = new
		// ImageLoaderConfiguration.Builder(this)
		// .memoryCacheExtraOptions(480, 800) // default = device screen
		// dimensions
		// .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75)
		// .threadPoolSize(3) // default
		// .threadPriority(Thread.NORM_PRIORITY - 1) // default
		// .denyCacheImageMultipleSizesInMemory()
		// .offOutOfMemoryHandling()
		// .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) //
		// default
		// .discCache(new UnlimitedDiscCache(cacheDir)) // default
		// .discCacheSize(50 * 1024 * 1024)
		// .discCacheFileCount(100)
		// .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) //
		// default
		// .imageDownloader(new URLConnectionImageDownloader()) // default
		// .tasksProcessingOrder(QueueProcessingType.FIFO) // default
		// .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) //
		// default
		// .enableLogging()
		// .build();
		// ImageLoader.getInstance().init(config);

	}
}