package edu.hebtu.movingcampus.activity;

import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import edu.hebtu.movingcampus.AppInfo;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.activity.base.BaseActivity;
import edu.hebtu.movingcampus.activity.wrapper.IPreference;
import edu.hebtu.movingcampus.adapter.InfoListAdapter;
import edu.hebtu.movingcampus.adapter.MessageListAdapter;
import edu.hebtu.movingcampus.biz.CourseDao;
import edu.hebtu.movingcampus.config.Constants;
import edu.hebtu.movingcampus.entity.NewsShort;
import edu.hebtu.movingcampus.subjects.CourseListSubject;
import edu.hebtu.movingcampus.subjects.LocalMessageSubject;
import edu.hebtu.movingcampus.subjects.MessageListSubject;
import edu.hebtu.movingcampus.view.GetCourse;

public class LocalNewsActivity extends BaseActivity {
	private LocalMessageSubject subject = null;
	private MessageListAdapter  adapter= null;
	private List<edu.hebtu.movingcampus.entity.Message>  initlist = null;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		ListView list = (ListView) findViewById(R.id.lv_message);
		
		subject = (LocalMessageSubject) IPreference.getInstance(getBaseContext())
				.getListOfNewsSubjectByID(0);

		//todo
		//mAdapter = new NewsListAdapter(ac, R.layout.news_item, listview, mlist);
		this.handler=new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				switch(msg.what){
				case 0:
					//notify dataset changed
					break;

				case 1:
					//faile
					//failed view . visiable
					//click to refresh
					break;
					
				}
				return false;
			}
		});

		//initlist = subject.dump(LocalNewsActivity.this);
		if(initlist==null||initlist.size()==0){
			getData();
			this.mProgressDialog = ProgressDialog.show(
					this, "", "正在初始化更新，请稍后...");
			this.mProgressDialog.onStart();
			this.mProgressDialog.show();
			this.mProgressDialog.setCancelable(true);
		}
		adapter = new MessageListAdapter(this, R.layout.news_item,
					list, initlist);
		list.setAdapter(adapter);

	}
	private void getData(){
		new Thread(new Runnable() {
			@Override
			public void run() {
		//		initlist=subject.dump(context)
				if(initlist!=null){
					 ((LocalMessageSubject)IPreference.getInstance(LocalNewsActivity.this)
							.getSubjectByTag(new MessageListSubject().getTag())).setMessageeList(initlist);
					 handler.sendEmptyMessage(0);
				}else {
					//TODO
					 handler.sendEmptyMessage(1);
				}
			}
		}).start();
	}

	@Override
	protected void bindButton() {
		// TODO Auto-generated method stub

	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

}
