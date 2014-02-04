package edu.hebtu.movingcampus.activity;

import java.util.ArrayList;

import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.R.layout;
import edu.hebtu.movingcampus.R.menu;
import edu.hebtu.movingcampus.biz.ChooseCourseDao;
import edu.hebtu.movingcampus.biz.RoomDao;
import edu.hebtu.movingcampus.entity.ChooseCourseEntity;
import edu.hebtu.movingcampus.entity.ClassRoom;
import edu.hebtu.movingcampus.entity.CourseEntity;
import edu.hebtu.movingcampus.entity.CourseIntroEntity;
import edu.hebtu.movingcampus.entity.TextBookInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseCourseActionActivity extends Activity {
	//课程选择类的数组
	private ArrayList<ChooseCourseEntity> ChooseCourseList = new ArrayList<ChooseCourseEntity>();
	private TextView Title;//本activity的标题
	private ListView CourseIntroItem;//选课的listview
	private MyAdapter adapter;//适配器
	private boolean isBooked;//定义要不要购买教材
	
	private int courseIntroSpread = 0;//是否展开课程介绍
	private int courseBookSpread = 1;//是否展开课程介绍
	private int bookIntroSpread = 0;//是否展开课程介绍
	
	private View courseIntroLayout;//课程介绍的layout
	
	private ImageView courseIntroImg;//课程介绍的layout后面的图片
	private ImageView courseBookImg;//课程选订的layout后面的图片
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_course_action);
		initData();
		initView();
		bindButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_course_action, menu);
		return true;
	}
	/**
	 * @return 本界面选课的标题
	 */
	public String getCourseTitle(){
		Bundle bundle = getIntent().getExtras();
		String title = bundle.getString("title");
		System.out.print("本界面的标题是"+title);
		return title;
	}
	/**
	 * @return 本界面课程介绍部分的内容
	 */
	public void getCourseIntro(){
		CourseIntroEntity course = new CourseIntroEntity();
		course = (CourseIntroEntity) new ChooseCourseDao(
				ChooseCourseActionActivity.this).getCourseIntroMsg(false,getCourseId().trim());
		Log.i("从服务器请求数据", "从服务器传回数据");
		if (course != null) {
			Log.i("从服务器传回数据", "从服务器传回数据");
			//初始化介绍课程的内容
			TextView courseId_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_courseid_tv);
			courseId_tv.setText(course.getCourseId());
			TextView courseCredit_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_coursecredit_tv);
			courseCredit_tv.setText(course.getCourseCredit());
			TextView courseGroup_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_belongto_tv);
			courseGroup_tv.setText(course.getCourseBelongTo());
			TextView coursePro_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_coursepro_tv);
			coursePro_tv.setText(course.getCoursePro());
			TextView courseAllTime_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_totaltime_tv);
			courseAllTime_tv.setText(course.getAllStudyTime());
			TextView courseWeekTime_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_weektime_tv);
			courseWeekTime_tv.setText(course.getHourPerWeek());
			TextView courseChinese_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_chinesename_tv);
			courseChinese_tv.setText(course.getChineseName());
			TextView courseEnglish_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_englishname_tv);
			courseEnglish_tv.setText(course.getEnglishName());
			TextView courseType_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_coursetype_tv);
			courseType_tv.setText(course.getType());
			TextView courseCollege_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_college_tv);
			courseCollege_tv.setText(course.getCollege());
			TextView courseSuitable_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_suiltablepeople_tv);
			courseSuitable_tv.setText(course.getSuiltabelPeople());
			TextView coursePer_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_perpose_tv);
			coursePer_tv.setText(course.getAimAndFunc());
			TextView courseIntro_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_courseintro_tv);
			courseIntro_tv.setText(course.getIntro());
			TextView courseBook_tv = (TextView)courseIntroLayout.findViewById(R.id.choosecourse_coursepro_coursebook_tv);
			courseBook_tv.setText(course.getBooks());
		} else
		// TODO{
		{
			Toast.makeText(getApplicationContext(), "接口获取错误", Toast.LENGTH_LONG)
			.show();
			
		}
		System.out.print("本界面的介绍部分的内容"+course);
	}
	/**
	 * @return 本界面选课的课程id
	 */
	public String getCourseId(){
		Bundle bundle = getIntent().getExtras();
		String courseId = bundle.getString("courseId");
		return courseId;
	}
	/**
	 * @return 教科书的信息
	 * @aim 得到对话框里面的图书信息
	 */
	public TextBookInfo initDialogData(String bookOderId){
		TextBookInfo textBook = new TextBookInfo();
		textBook = (TextBookInfo)new ChooseCourseDao(
				ChooseCourseActionActivity.this).getBookMsg(false,bookOderId.trim());
		
		Log.i("从服务器请求数据", "从服务器传回数据");
		if(textBook!=null){
			return textBook;
		}else{
			Toast.makeText(getApplicationContext(), "接口获取错误", Toast.LENGTH_LONG)
			.show();
			TextBookInfo textBookl = new TextBookInfo();
			textBookl.setAuthor("张雷杰");
			textBookl.setBookId("8337383");
			textBookl.setBookOrderId("1232335");
			textBookl.setGoodBook("是");
			textBookl.setName("《啦啦啦》");
			textBookl.setPrice("123元");
			textBookl.setPrinting("第二版");
			textBookl.setPublishedTime("2011年11月12日");
			textBookl.setPublishHouse("雷杰出版社");
		return textBookl;
		}
	}
	/**
	 * @aim 初始化对话框
	 */
	public void initDialog(TextBookInfo textBookInfo){
		TextBookInfo textBook = new TextBookInfo();
		textBook = textBookInfo;
		final Dialog dialog = new Dialog(ChooseCourseActionActivity.this);
		dialog.setCancelable(false);
		dialog.setTitle("课程预订");
		dialog.setContentView(R.layout.choosecourse_dialog);
		dialog.show();
		//教材介绍的layout
		final LinearLayout bookIntroLayout = (LinearLayout) dialog.findViewById(R.id.choosecourse_dialog_bookintro_layout);
		System.out.print(bookIntroLayout);
		//教材介绍的layout后面的图片
		final ImageView bookIntroImg = (ImageView) dialog.findViewById(R.id.choosecourse_dialog_bookintro_img);
		//初始化个组件
		TextView bookName = (TextView) dialog.findViewById(R.id.choosecourse_dialog_bookname_tv);
		TextView authour = (TextView) dialog.findViewById(R.id.choosecourse_dialog_bookauthor_tv);
		TextView publishHouse = (TextView) dialog.findViewById(R.id.choosecourse_dialog_bookhouse_tv);
		TextView bookid = (TextView) dialog.findViewById(R.id.choosecourse_dialog_bookid_tv);
		TextView textbookid = (TextView) dialog.findViewById(R.id.choosecourse_dialog_textbookid_tv);
		TextView version = (TextView) dialog.findViewById(R.id.choosecourse_dialog_bookversion_tv);
		TextView time = (TextView) dialog.findViewById(R.id.choosecourse_dialog_booktime_tv);
		TextView price = (TextView) dialog.findViewById(R.id.choosecourse_dialog_bookprice_tv);
		TextView isGood = (TextView) dialog.findViewById(R.id.choosecourse_dialog_bookisgood_tv);
		//给各个组件添加显示的内容
		bookName.setText(textBook.getName());
		authour.setText(textBook.getAuthor());
		publishHouse.setText(textBook.getPublishHouse());
		bookid.setText(textBook.getBookId());
		textbookid.setText(textBook.getBookOrderId());
		version.setText(textBook.getPrinting());
		time.setText(textBook.getPublishedTime());
		price.setText(textBook.getPrice());
		isGood.setText(textBook.getGoodBook());
		//添加点击事件
		LinearLayout booktitle = (LinearLayout) dialog.findViewById(R.id.choosecourse_dialog_bookintro_title_layout);
		booktitle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bookIntroSpread++;
				if(bookIntroSpread%2==0){
					bookIntroLayout.setVisibility(View.GONE);
					bookIntroImg.setImageResource(R.drawable.mm_submenu_normal_down);
				}else{
					bookIntroLayout.setVisibility(View.VISIBLE);
					bookIntroImg.setImageResource(R.drawable.mm_submenu_normal_up);
				}
			}
		});
		//定义要不要购买教材
		isBooked = false;
		//获得预定教材的checkbox
		CheckBox bookBook = (CheckBox) dialog.findViewById(R.id.choosecourse_dialog_bookbook_check);
		bookBook.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					isBooked = true;
				}else{
					isBooked = false;
				}
			}
		});
		//定义对话框的确定键
		Button positive = (Button) dialog.findViewById(R.id.choosecourse_dialog_positive);
		//定义对话框的取消键
		Button nagtive = (Button) dialog.findViewById(R.id.choosecourse_dialog_nagtive);
		//绑定监听器
		positive.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 发送预定请求
				dialog.dismiss();
			}
		});
		nagtive.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//取消预定
				dialog.dismiss();
			}
			
		});
	}
	/**
	 * @aim 绑定按钮控件
	 */
	public void bindButton() {
		// 返回键的按钮
		findViewById(R.id.choosecourse_courseaction_btn_back).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 关闭本activity
						ChooseCourseActionActivity.this.finish();
					}
				});
		// 课程介绍的点击操作
		findViewById(R.id.choose_course_coursepro_layout).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				courseIntroSpread++;//课程介绍的计数加一
				
				if(courseIntroSpread%2==1){
					courseIntroImg.setImageResource(R.drawable.mm_submenu_normal_up);
					courseIntroLayout.setVisibility(View.VISIBLE);
				}else{
					courseIntroImg.setImageResource(R.drawable.mm_submenu_normal_down);
					courseIntroLayout.setVisibility(View.GONE);
				}
			}
		});
		// 课程选订的点击操作
		findViewById(R.id.choose_course_courseselect_layout).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				courseBookSpread++;
				if(courseBookSpread%2==1){
					courseBookImg.setImageResource(R.drawable.mm_submenu_normal_up);
					CourseIntroItem.setVisibility(View.VISIBLE);
				}else{
					courseBookImg.setImageResource(R.drawable.mm_submenu_normal_down);
					CourseIntroItem.setVisibility(View.GONE);
				}
			}
		});
		
	}
	/**
	 * @aim 初始化视图
	 */
	public void initView(){
		//初始化各个需要点击的layout
		
		courseIntroLayout = findViewById(R.id.choose_course_courseprointro_layout);
		getCourseIntro();
		//初始化layout里面的图片
		courseIntroImg = (ImageView) findViewById(R.id.choose_course_coursepro_img);
		courseBookImg = (ImageView) findViewById(R.id.choose_course_courseselect_img);
		
		this.Title = (TextView) findViewById(R.id.choosecourse_chooseanction_tv);
		//设置标题
		this.Title.setText(getCourseTitle());
		this.CourseIntroItem = (ListView) findViewById(R.id.choosecourse_bookcourse_listview);
		this.adapter = new MyAdapter();
		//绑定适配器
		this.CourseIntroItem.setAdapter(adapter);
		//绑定监听器
		this.CourseIntroItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String bookOderId = ChooseCourseList.get(arg2).getBookOrderId();
				System.out.println("这是得到的教科书征订号"+bookOderId);
				TextBookInfo textBook = initDialogData(bookOderId);
				initDialog(textBook);
			}
		});
	}
	/**
	 * @aim 初始化listview的数据源
	 */
	public void initData(){
		ChooseCourseList.clear();
		ArrayList<ChooseCourseEntity> res = (ArrayList<ChooseCourseEntity>) new ChooseCourseDao(
				ChooseCourseActionActivity.this).getChooseCourseMsg(false, getCourseId().trim());
		Log.i("从服务器请求数据", "从服务器传回数据");
		if (res != null) {
			Log.i("从服务器传回数据", "从服务器传回数据");
			for (ChooseCourseEntity c : res) {
				ChooseCourseEntity chooseCourse = new ChooseCourseEntity();
				chooseCourse.setCampus(c.getCampus());
				chooseCourse.setAllChoosedNum(c.getAllChoosedNum());
				chooseCourse.setCapacity(c.getCapacity());
				chooseCourse.setCollege(c.getCollege());
				chooseCourse.setHourPerWeek(c.getHourPerWeek());
				chooseCourse.setMethosOfTeach(c.getMethosOfTeach());
				chooseCourse.setNativeChoosedNum(c.getNativeChoosedNum());
				chooseCourse.setRemark(c.getRemark());
				chooseCourse.setRoomOfCourse(c.getRoomOfCourse());
				chooseCourse.setShortTerm(c.getShortTerm());
				chooseCourse.setTeacher(c.getTeacher());
				chooseCourse.setTextBook(c.getTextBook());
				chooseCourse.setTimeOfExam(c.getTimeOfExam());
				chooseCourse.setTypeOfExam(c.getTypeOfExam());
				chooseCourse.setBookOrderId(c.getBookOrderId());
				ChooseCourseList.add(chooseCourse);
			}
		} else
		// TODO{
		{
			Toast.makeText(getApplicationContext(), "接口获取错误", Toast.LENGTH_LONG)
					.show();
			for(int i=0;i<3;i++){

				ChooseCourseEntity chooseCourse = new ChooseCourseEntity();
				chooseCourse.setCampus("新校区");
				chooseCourse.setAllChoosedNum("1000");
				chooseCourse.setCapacity("1000");
				chooseCourse.setCollege("软件学院");
				chooseCourse.setHourPerWeek("4.0");
				chooseCourse.setMethosOfTeach("口述");
				chooseCourse.setNativeChoosedNum("100");
				chooseCourse.setRemark("好学");
				chooseCourse.setRoomOfCourse("软件学院A-206");
				chooseCourse.setShortTerm("是");
				chooseCourse.setTeacher("张雷杰");
				chooseCourse.setTextBook("《人渣创奇》");
				chooseCourse.setTimeOfExam("未知");
				chooseCourse.setTypeOfExam("考试");
				chooseCourse.setBookOrderId("3333333");
				ChooseCourseList.add(chooseCourse);
			}

		}
		
	}
	/**
	 * @author leijie
	 *@aim 自定义适配器
	 */
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return ChooseCourseList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return ChooseCourseList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.choosecourse_bookcourse_item, null);
			//教室姓名
			TextView teacher = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_teacher_tv);
			//周学时
			TextView timePerWeek = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_weektime_tv);
			//考核
			TextView text = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_type_tv);
			//校区
			TextView campus = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_campus_tv);
			//上课时间
			TextView time = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_time_tv);
			//上课地点
			TextView place = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_place_tv);
			//上课备注
			TextView remark = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_remark_tv);
			//上课授课方式
			TextView way = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_method_tv);
			//是否是短学期
			TextView isshort = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_shortterm_tv);
			//教材名称
			TextView book = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_textbook_tv);
			//本专业已选人数
			TextView nativnum = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_nativenum_tv);
			//所有已选人数
			TextView allnum = (TextView) convertView.findViewById(R.id.choosecourse_courseselect_allnum_tv);
			
			ChooseCourseEntity chooseCourse = ChooseCourseList.get(position);
			
			//设置授课教师
			teacher.setText(chooseCourse.getTeacher());
			//周学时
			timePerWeek.setText(chooseCourse.getHourPerWeek());
			//设置考核方式
			text.setText(chooseCourse.getTypeOfExam());
			//设置校区
			campus.setText(chooseCourse.getCampus());
			//设置上课时间
			time.setText(chooseCourse.getTimeOfExam());
			//设置授课地点
			place.setText(chooseCourse.getRoomOfCourse());
			//设置备注
			remark.setText(chooseCourse.getRemark());
			//设置授课方式
			way.setText(chooseCourse.getMethosOfTeach());
			//设置是否是短期
			isshort.setText(chooseCourse.getShortTerm());
			//设置教材
			book.setText(chooseCourse.getTextBook());
			//设置本专业已选人数
			nativnum.setText(chooseCourse.getNativeChoosedNum());
			//设置所有已选人数
			allnum.setText(chooseCourse.getAllChoosedNum());
			return convertView;
		}
		
	}
}
