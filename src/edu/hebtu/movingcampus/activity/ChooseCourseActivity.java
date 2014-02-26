package edu.hebtu.movingcampus.activity;

import edu.hebtu.movingcampus.AppInfo;
import edu.hebtu.movingcampus.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ChooseCourseActivity extends Activity {
	private Intent intent;// 跳转到其他界面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_class);
		initView();
		bindButton();// 绑定控件
	}

	/**
	 * @aim 初始化组件
	 */
	private void initView() {
		TextView studyNum = (TextView) findViewById(R.id.tvscore_stuID);
		studyNum.setText(AppInfo.getUser().getCid());
		TextView userName = (TextView) findViewById(R.id.tvscore_StuName);
		userName.setText(AppInfo.getUser().getUserName());
		TextView studyYear = (TextView) findViewById(R.id.tvpage_xn);
		studyYear.setText(AppInfo.getStudyYear());
		TextView term = (TextView) findViewById(R.id.tvpage_xq);
		term.setText(AppInfo.getTerm());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_class, menu);
		return true;
	}

	/**
	 * @aim 绑定按钮控件
	 */
	public void bindButton() {
		// 选跨专业课的按钮
		findViewById(R.id.choose_course_anotermajor_layout).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 跳转到选选修课的界面
						toChooseCourseActivity("跨专业课", "another", 1);
					}
				});
		// 选选修课课的按钮
		findViewById(R.id.choose_course_elective_layout).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 跳转到选选修课的界面
						toChooseCourseActivity("选修课程", "elective", 2);
					}
				});
		// 选快速选课的按钮
		findViewById(R.id.choose_course_fast_layout).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 跳转到快速选课的界面
						toChooseCourseActivity("快速选课", "fast", 3);
					}
				});
		// 选本专业课的按钮
		findViewById(R.id.choose_course_native_layout).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 跳转到选本专业课的界面
						toChooseCourseActivity("选本专业课程", "native", 4);
					}
				});
		// 选特殊课的按钮
		findViewById(R.id.choose_course_special_layout).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 跳转到选择特殊课程的界面
						toChooseCourseActivity("选特殊课程", "special", 5);
					}
				});
		// 选体育课的按钮
		findViewById(R.id.choose_course_te_layout).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 跳转到选体育课的界面
						toChooseCourseActivity("选体育课", "PE", 6);
					}
				});
		// 返回键的按钮
		findViewById(R.id.choosecourse_btn_back).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 关闭本activity
						ChooseCourseActivity.this.finish();
					}
				});

	}

	/**
	 * @param extra
	 *            下一个activity的标题
	 * @param type
	 *            下一个activity是那个课程的选课类型是选体育课还是别的
	 */
	public void toChooseCourseActivity(String extra, String type, int i) {
		this.intent = new Intent(ChooseCourseActivity.this,
				ChooseCourseBrunchActivity.class);
		this.intent.putExtra("title", extra);
		this.intent.putExtra("type", type);
		ChooseCourseActivity.this.startActivityForResult(this.intent, i);
	}

}
