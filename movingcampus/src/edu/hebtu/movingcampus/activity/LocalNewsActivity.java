package edu.hebtu.movingcampus.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.activity.base.BaseActivity;
import edu.hebtu.movingcampus.activity.wrapper.IPreference;
import edu.hebtu.movingcampus.adapter.MessageListAdapter;
import edu.hebtu.movingcampus.biz.MessageDao;
import edu.hebtu.movingcampus.entity.MMessage;
import edu.hebtu.movingcampus.subjects.LocalMessageSubject;
import edu.hebtu.movingcampus.utils.NetWorkHelper;

public class LocalNewsActivity extends BaseActivity {
	private LocalMessageSubject subject = null;
	private MessageDao dao;
	private MessageListAdapter  adapter= null;
	private List<edu.hebtu.movingcampus.entity.MMessage>  messagelist = null;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		dao=new MessageDao(this);
		ListView list = (ListView) findViewById(R.id.lv_message);
		
		subject = (LocalMessageSubject) IPreference.getInstance(getBaseContext())
				.getListOfNewsSubjectByID(0);

		messagelist = ((LocalMessageSubject)IPreference.getInstance(LocalNewsActivity.this)
			.getSubjectByTag(new LocalMessageSubject().getTag())).getMessageList();

		this.handler=new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				switch(msg.what){
				case 0:
					adapter.notifyDataSetChanged();
					LocalNewsActivity.this.mProgressDialog.dismiss();
					break;

				case 1:
					Toast.makeText(LocalNewsActivity.this, "获取信息失败", Toast.LENGTH_LONG).show();
					break;
					
				}
				return false;
			}
		});

		if(messagelist==null||messagelist.size()==0){
			if(MainActivity.loginOnLine&&NetWorkHelper.isNetworkAvailable(this)){
				getData();
				this.mProgressDialog = ProgressDialog.show(
						this, "", "正在初始化更新，请稍后...");
				this.mProgressDialog.onStart();
				this.mProgressDialog.show();
				this.mProgressDialog.setCancelable(true);
			}
		}
		adapter = new MessageListAdapter(this, R.layout.message_item,
					list, messagelist);
		list.setAdapter(adapter);

		bindButton();
	}

	private void getData(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				messagelist=dao.mapperJson(false,null);
				//TODO
				if(messagelist==null){
					messagelist=new ArrayList<MMessage>();
					MMessage msg=new MMessage();
					msg.setContent("aaaaaaaaaaaalfkdajfassssffffffffffffffffffff");
					msg.setReaded(false);
					msg.setTime(null);
					msg.setTitle("aa");
					messagelist.add(msg);
				}
				if(messagelist!=null){
					 ((LocalMessageSubject)IPreference.getInstance(LocalNewsActivity.this)
							.getSubjectByTag(new LocalMessageSubject().getTag())).addMessageeList(messagelist);
					 handler.sendEmptyMessage(0);
				}else {
					 handler.sendEmptyMessage(1);
				}
			}
		}).start();
	}

	@Override
	protected void bindButton() {
		findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LocalNewsActivity.this.finish();
			}
		});
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

}
