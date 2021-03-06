package edu.hebtu.movingcampus.activity.wrapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import edu.hebtu.movingcampus.config.Constants;
import edu.hebtu.movingcampus.entity.User;
import edu.hebtu.movingcampus.enums.NewsType;
import edu.hebtu.movingcampus.subject.base.ListOfNews;
import edu.hebtu.movingcampus.subject.base.OneofNews;
import edu.hebtu.movingcampus.subject.base.Subject;
import edu.hebtu.movingcampus.subjects.CourseListSubject;
import edu.hebtu.movingcampus.subjects.LocalMessageSubject;
import edu.hebtu.movingcampus.subjects.NewsSubject;
import edu.hebtu.movingcampus.utils.LogUtil;

/**
 * @author hippo
 * @version 1.0
 * @created 14-Nov-2013 9:13:32 AM
 */
public class IPreference implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static IPreference instance;
	private static final String serilizeFile = Constants.PREFER_FILE + ".db";
	private static SharedPreferences pre;
	private User profile;

	private HashMap<String, Subject> subjects = new HashMap<String, Subject>();

	/**
	 * Double checked locking not work on java1.4 and earlier!
	 */
	// 单例模式
	private IPreference(Context context) {
		// TODO,settings,for debug
		pre = context.getSharedPreferences(Constants.PREFER_FILE, 0);
		SharedPreferences.Editor editor = pre.edit();
		// 本地信息

		// 默认为0
		if (pre.getInt(Constants.LIB_DAYS, 0) == 0) {
			editor.putInt(Constants.LIB_DAYS, 10);
			editor.putInt(Constants.BALANCE_LOWEAST, 10);
		}
		editor.commit();

		//TODO
		LocalMessageSubject lm=new LocalMessageSubject();
		subjects.put(lm.getTag(),lm);
		// 本地信息,第一个，默认显示
		CourseListSubject course=new CourseListSubject();
		subjects.put(course.getTag(),course);

		for (int i = 1; i < NewsType.values().length; i++) {
			if (pre.getBoolean("news_" + i, true)) {
				NewsSubject s = new NewsSubject(NewsType.values()[i]);
				subjects.put(s.getTag(), s); // 每次接受20条|默认,学校新闻...
			}
		}
	}

	public Subject getSubjectByTag(String tag) {
		return subjects.get(tag);
	}

	/**
	 * Double checked locking not work on java1.4 or earlier!
	 */
	public static IPreference getInstance(Context context) {
		if (instance == null) {
			try {
				instance = load(context);
				pre = context.getSharedPreferences(Constants.PREFER_FILE, 0);
			} catch (OptionalDataException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (instance == null) {
			synchronized (IPreference.class) {
				if (instance == null)
					instance = new IPreference(context);
			}
		}
		return instance;
	}

	/**
	 * IPreference 的序列化流
	 * 
	 * @param filename
	 * @throws IOException
	 * @parm context
	 */
	public static void save(Context context) throws IOException {
		FileOutputStream fos = context.openFileOutput(serilizeFile,
				Context.MODE_PRIVATE);
		ObjectOutputStream oo = new ObjectOutputStream(fos);
		oo.writeObject(IPreference.getInstance(context));
		oo.flush();
		fos.close();
		oo.close();
		LogUtil.d("board", "saved in" + serilizeFile);
	}

	/**
	 * IPreference的反序列化,
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws OptionalDataException
	 * @parm context
	 */
	public static IPreference load(Context context)
			throws OptionalDataException, ClassNotFoundException, IOException {

		IPreference obj = null;
		FileInputStream fis = context.openFileInput(serilizeFile);
		ObjectInputStream oi = new ObjectInputStream(fis);
		obj = (IPreference) oi.readObject();
		oi.close();
		fis.close();
		LogUtil.d("board", "deserialize: " + obj.getClass().getName());
		return obj;
	}

	/**
	 * 得到相应主题的新闻列表,id范围0是本地推送的新闻,1-...是主题新闻
	 * 
	 * @return 新闻列表项
	 */
	public List<ListOfNews> getListOfNewsSubject() {
		List<ListOfNews> list = new ArrayList<ListOfNews>();
		for (int i = 1; i < NewsType.values().length; i++) {
			if (pre.getBoolean("news_" + i, true)) {
				NewsSubject s = new NewsSubject(NewsType.values()[i]);
				list.add((ListOfNews) subjects.get(s.getTag())); // 每次接受20条|默认,学校新闻...
			}
		}
		return list;
	}

	/**
	 * 得到相应主题的新闻列表,id范围0是本地推送的新闻,1-...是主题新闻
	 * 
	 * @param id
	 * @return
	 */
	public Subject getListOfNewsSubjectByID(int id) {
		return subjects.get("subject." + id);
	}

	/**
	 * 移除相应主题的新闻
	 * 
	 * @param id
	 */
	public void removeListOfNewsSubjectById(int id) {
		subjects.remove(((Subject) getListOfNewsSubjectByID(id)).getTag());
	}

	public void addListOfNewsSubject(NewsSubject newsSubject) {
		subjects.put(newsSubject.getTag(), newsSubject);
	}

	public User getProfile() {
		return profile;
	}

	public void setProfile(User profile) {
		this.profile = profile;
	}

}