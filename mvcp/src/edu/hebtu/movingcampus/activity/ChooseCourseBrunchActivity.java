package edu.hebtu.movingcampus.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.biz.ChooseCourseDao;
import edu.hebtu.movingcampus.entity.CourseEntity;

public class ChooseCourseBrunchActivity extends Activity {
	// 课程类的数组
	private ArrayList<CourseEntity> CourseList = new ArrayList<CourseEntity>();
	private TextView Title;// 本activity的标题
	private ListView CourseItem;// 选课的listview
	private MyAdapter adapter;// 适配器
	private Intent intent;// 跳转到其他界面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_course_brunch);
		bindButton();
		initData();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_course_brunch, menu);
		return true;
	}

	/**
	 * @return 本界面选课的标题
	 */
	public String getCourseTitle() {
		Bundle bundle = getIntent().getExtras();
		String title = bundle.getString("title");
		return title;
	}

	/**
	 * @return 本界面选课的类型
	 */
	public String getCourseType() {
		Bundle bundle = getIntent().getExtras();
		String type = bundle.getString("type");
		return type;
	}

	/**
	 * @aim 初始化视图
	 */
	public void initView() {
		this.Title = (TextView) findViewById(R.id.choosecourse_choosetype_tv);
		// 设置标题
		this.Title.setText(getCourseTitle());
		this.CourseItem = (ListView) findViewById(R.id.choosecourse_courseitem_listview);
		this.adapter = new MyAdapter();
		// 绑定适配器
		this.CourseItem.setAdapter(adapter);
		// 绑定监听器
		this.CourseItem
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// 获得课程名字
						CourseEntity course = CourseList.get(arg2);
						String name = course.getCourseName();
						String courseId = course.getCourseId();
						// 跳转界面
						toChooseCourseActionActivity(name, courseId);
					}
				});
	}

	/**
	 * @aim 绑定按钮控件
	 */
	public void bindButton() {
		// 返回键的按钮
		findViewById(R.id.btn_back).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 关闭本activity
						ChooseCourseBrunchActivity.this.finish();
					}
				});
	}

	/**
	 * @aim 初始化listview的数据源
	 */
	public void initData() {
		CourseList.clear();
		ArrayList<CourseEntity> res = (ArrayList<CourseEntity>) new ChooseCourseDao(
				ChooseCourseBrunchActivity.this).getCourseMsg(false,
				getCourseType().trim());
		Log.i("从服务器请求数据", "从服务器传回数据");
		if (res != null) {
			Log.i("从服务器传回数据", "从服务器传回数据");
			for (CourseEntity c : res) {
				CourseEntity course = new CourseEntity();
				course.setCourseCredit(c.getCourseCredit());
				course.setCourseGroup(c.getCourseGroup());
				course.setCourseId(c.getCourseId());
				course.setCourseIntroduce(c.getCourseIntroduce());
				course.setCourseLeftNum(c.getCourseLeftNum());
				course.setCourseName(c.getCourseName());
				course.setCoursePro(c.getCoursePro());
				course.setHourPerWeek(c.getHourPerWeek());
				course.setSelectedOrNot(c.getSelectedOrNot());
				course.setTimeOfExam(c.getTimeOfExam());
				CourseList.add(course);
			}
		} else
		// TODO{
		{
			Toast.makeText(getApplicationContext(), "接口获取错误", Toast.LENGTH_LONG)
					.show();
			for (int i = 0; i < 3; i++) {
				CourseEntity course = new CourseEntity();
				course.setCourseCredit("3.0");
				course.setCourseGroup("文科");
				course.setCourseId("222323233");
				course.setCourseIntroduce("介绍");
				course.setCourseLeftNum("122");
				course.setCourseName("语文");
				course.setCoursePro("必修");
				course.setHourPerWeek("4.0");
				course.setSelectedOrNot("否");
				course.setTimeOfExam("2011年");
				CourseList.add(course);
			}
		}
	}

	/**
	 * @param extra
	 *            要传给下一个activity的数据
	 */
	public void toChooseCourseActionActivity(String name, String courseId) {
		this.intent = new Intent(ChooseCourseBrunchActivity.this,
				ChooseCourseActionActivity.class);
		this.intent.putExtra("title", name);
		this.intent.putExtra("courseId", courseId);
		ChooseCourseBrunchActivity.this.startActivityForResult(this.intent, 9);
	}

	/**
	 * @author leijie
	 * @aim 自定义适配器
	 */
	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return CourseList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return CourseList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.choosecourse_courseitem_item, null);
			TextView name = (TextView) convertView
					.findViewById(R.id.choosecourse_courseitem_book);
			TextView type = (TextView) convertView
					.findViewById(R.id.choosecourse_courseitem_type);
			TextView credit = (TextView) convertView
					.findViewById(R.id.choosecourse_courseitem_credit);
			TextView select = (TextView) convertView
					.findViewById(R.id.choosecourse_courseitem_select);
			TextView left = (TextView) convertView
					.findViewById(R.id.choosecourse_courseitem_left);
			CourseEntity course = CourseList.get(position);
			name.setText(course.getCourseName());
			type.setText(course.getCoursePro());
			credit.setText(course.getCourseCredit());
			select.setText(course.getSelectedOrNot());
			left.setText(course.getCourseLeftNum());
			return convertView;
		}

	}
}
