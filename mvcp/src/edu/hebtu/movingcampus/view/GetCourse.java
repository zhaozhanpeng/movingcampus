package edu.hebtu.movingcampus.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import edu.hebtu.movingcampus.AppInfo;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.activity.MainActivity;
import edu.hebtu.movingcampus.activity.ShowCourse;
import edu.hebtu.movingcampus.activity.wrapper.IPreference;
import edu.hebtu.movingcampus.biz.CourseDao;
import edu.hebtu.movingcampus.config.Constants;
import edu.hebtu.movingcampus.entity.Course;
import edu.hebtu.movingcampus.subjects.CourseListSubject;
import edu.hebtu.movingcampus.utils.LogUtil;

/**
 * @author
 */
@SuppressLint("ShowToast")
public class GetCourse {
	private TextView tv1_2, tv1_3, tv2_2, tv2_3, tv3_2, tv3_3, tv4_2, tv4_3,
			tv5_2, tv5_3;
	private String[] course = new String[5];
	private String[] add = new String[5];
	private Activity context;
	private ArrayList<ArrayList<Course>> courseList;
	private Handler handler;
	private CourseListSubject subject;

	public GetCourse(Activity context) {
		this.context = context;
		this.handler=new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				switch(msg.what){
				case 0:
					ShowCourse.adapter.setListViews(getListview());
					break;

				case 1:
					break;
					
				}
				return false;
			}
		});

		subject = (CourseListSubject)IPreference.getInstance(context)
				.getSubjectByTag(new CourseListSubject().getTag());
		if(subject!=null)
			courseList= subject.getCourseList();

		if(courseList==null||courseList.size()==0)
			getData();
	}

	public void getData(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				courseList = new CourseDao(GetCourse.this.context).mapperJson(AppInfo.getStudyYear()
						.substring(0, 4), AppInfo.getTerm(),
						Constants.COURSE_DOMAIN.STUDENT);
				if(courseList!=null){
					 ((CourseListSubject)IPreference.getInstance(GetCourse.this.context)
							.getSubjectByTag(new CourseListSubject().getTag())).setCourseList(courseList);
					 handler.sendEmptyMessage(0);
				}else {
					//TODO
					 handler.sendEmptyMessage(1);
				}
			}
		}).start();
	}

	public List<View> getListview(){
		List<View> listviews =new ArrayList<View>();
		for(int i=1;i<8;i++)
			listviews.add(getScheduleView(i));
		return listviews;
	}

	private View getScheduleView(int week) {
		if (courseList == null) {
			Toast.makeText(context, "错误!", Toast.LENGTH_SHORT);
			return new View(context);
		}

		View view = View.inflate(context, R.layout.showcourse_page, null);
		LayoutInflater mInflater = LayoutInflater.from(context);
		View myView = mInflater.inflate(R.layout.showcourse_page, null);
		ArrayList<Course> courses = courseList.get(week - 1);

		// tv0 = (TextView) myView.findViewById(R.id.show_tv0);

		tv1_2 = (TextView) myView.findViewById(R.id.show_tv1_2);
		tv1_3 = (TextView) myView.findViewById(R.id.show_tv1_3);

		tv2_2 = (TextView) myView.findViewById(R.id.show_tv2_2);
		tv2_3 = (TextView) myView.findViewById(R.id.show_tv2_3);

		tv3_2 = (TextView) myView.findViewById(R.id.show_tv3_2);
		tv3_3 = (TextView) myView.findViewById(R.id.show_tv3_3);

		tv4_2 = (TextView) myView.findViewById(R.id.show_tv4_2);
		tv4_3 = (TextView) myView.findViewById(R.id.show_tv4_3);

		tv5_2 = (TextView) myView.findViewById(R.id.show_tv5_2);
		tv5_3 = (TextView) myView.findViewById(R.id.show_tv5_3);

		LogUtil.i("GetSchedule", week + "");

		for (int i = 0; i < courses.size(); i++) {
			Course c = courses.get(i);
			if (c.getStatus()) {
				course[i] = c.getKcm() + " " + c.getNum() + c.getNum();
				add[i] = c.getUnit() + " " + c.getRoomid();
			} else {
				course[i] = "";
				add[i] = "";
			}
		}

		tv1_2.setText(course[0]);
		tv1_3.setText(add[0]);

		tv2_2.setText(course[1]);
		tv2_3.setText(add[1]);

		tv3_2.setText(course[2]);
		tv3_3.setText(add[2]);

		tv4_2.setText(course[3]);
		tv4_3.setText(add[3]);

		tv5_2.setText(course[4]);
		tv5_3.setText(add[4]);

		return myView;
	}

}
