package edu.hebtu.movingcampus.activity.wrapper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.activity.CardTransferActivity;
import edu.hebtu.movingcampus.activity.MainActivity;
import edu.hebtu.movingcampus.activity.base.PageWraper;
import edu.hebtu.movingcampus.biz.CardDao;
import edu.hebtu.movingcampus.biz.UserDao;
import edu.hebtu.movingcampus.biz.base.BaseDao;
import edu.hebtu.movingcampus.config.Constants;
import edu.hebtu.movingcampus.entity.CardEntity;
import edu.hebtu.movingcampus.entity.User;
import edu.hebtu.movingcampus.subjects.LocalMessageSubject;
import edu.hebtu.movingcampus.subjects.NetworkChangeReceiver;
import edu.hebtu.movingcampus.subjects.NetworkChangeReceiver.NetworkchangeListener;
import edu.hebtu.movingcampus.utils.LogUtil;
import edu.hebtu.movingcampus.utils.NetWorkHelper;

public class AllInOneCardActivity implements PageWraper, NetworkchangeListener {

    private CardEntity bean;
	private CardDao dao;
	private Activity mainActivity = MainActivity.instance;
	private final View contentView;
	private AsyncTask<BaseDao, Integer, Boolean[]> asyncTask;

	public AllInOneCardActivity(View view) {
		this.contentView = view;
		contentView.findViewById(R.id.btn_lockunlock).setBackgroundResource(
				R.drawable.unlock);
		this.dao = new CardDao(mainActivity);

		bean = ((LocalMessageSubject)
				IPreference.getInstance(mainActivity).getListOfNewsSubjectByID(0)).getCardEntity();

		if(bean!=null){
			((TextView) (contentView.findViewById(R.id.tv_balance_left)))
					.setText(bean.getLastPay() + "元");
			if (bean.getStatus() == false)
				contentView.findViewById(R.id.btn_lockunlock)
						.setBackgroundResource(R.drawable.lock);
			else
				contentView.findViewById(R.id.btn_lockunlock)
						.setBackgroundResource(R.drawable.unlock);
		}

		getData();
		bindButton();
	}


	private void getData(){
		if (bean == null&&NetWorkHelper.isNetworkAvailable(mainActivity))
			asyncTask = new Cardtask(null).execute(dao);
	}

	private void bindButton() {
		contentView.findViewById(R.id.Linear_above_toHome).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.instance.getSlidingMenu().toggle();
			}

		});
		// 点击刷新
		contentView.findViewById(R.id.rl_catdleft).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						asyncTask = new Cardtask(null).execute(dao);
					}
				});
		contentView.findViewById(R.id.ly_connection_failed).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mainActivity.startActivity(new Intent(
								"android.settings.WIRELESS_SETTINGS"));
					}
				});
		contentView.findViewById(R.id.btn_balance_transfer).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mainActivity.startActivity(new Intent(mainActivity,
								CardTransferActivity.class));
					}
				});
		contentView.findViewById(R.id.rl_lockunlock).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (bean != null)
							if (bean.getStatus() == true)
								asyncTask = new Cardtask(Constants.ACTION_LOCK)
										.execute(dao);
							else
								asyncTask = new Cardtask(Constants.ACTION_UNLOCK)
										.execute(dao);
					}
				});
	}

	@Override
	public void onResume() {
		onDataEnabled();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

	}

	private class Cardtask extends AsyncTask<BaseDao, Integer, Boolean[]> {
		private String action = null;

		public Cardtask(String action) {
			this.action = action;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if (bean != null) {
				LogUtil.d("object:", bean + "status:" + bean.getStatus());
				((TextView) (contentView.findViewById(R.id.tv_balance_left)))
						.setText(bean.getLastPay() + "元");
				if (bean.getStatus() == false)
					contentView.findViewById(R.id.btn_lockunlock)
							.setBackgroundResource(R.drawable.lock);
				else
					contentView.findViewById(R.id.btn_lockunlock)
							.setBackgroundResource(R.drawable.unlock);

			} else {
				// do nothing TODO
				// ((TextView) (contentView.findViewById(R.id.tv_balance_left)))
				// .setText("***" + "元");
			}
		}

		@Override
		protected Boolean[] doInBackground(BaseDao... params) {
			if(!MainActivity.loginOnLine){
				User  user=IPreference.getInstance(mainActivity).getProfile();
				IPreference.getInstance(mainActivity).setProfile(new UserDao(mainActivity).mapperJson(user.getUserName(), user.getPassword()));
				MainActivity.loginOnLine=true;
			}
			Boolean[] res = new Boolean[2];
			if (action != null && bean != null)
				res[0] = bean.getStatus();
			else
				res[0] = null;
			if (action != null)
				((CardDao) params[0]).mapperJson(action);
			bean = ((CardDao) params[0]).mapperJson(false);
			if (bean != null)
				res[1] = bean.getStatus();
			else
				res[1] = null;
			return res;
		}

		@Override
		protected void onPostExecute(Boolean[] result) {
			// 如果为获取到信息或修改了一卡通状态,但状态未变化
			if (bean == null || result[0] == null || result[1] == null
					|| action != null && result[0] == result[1]) {
				try {
					if (!NetWorkHelper.isMobileDataEnable(mainActivity)
							&& !NetWorkHelper.isWifiDataEnable(mainActivity)) {
						Toast.makeText(mainActivity, "请检查您的网络连接",
								Toast.LENGTH_SHORT).show();
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (action != null && result[0] != result[1]) {
				Toast.makeText(mainActivity, "操作成功", Toast.LENGTH_SHORT)
						.show();
			}else if(bean!=null){
				Toast.makeText(mainActivity, "更新一卡通余额成功!",
						Toast.LENGTH_SHORT).show();
			}

			if (bean != null) {
				((LocalMessageSubject)IPreference.
						getInstance(mainActivity).getListOfNewsSubjectByID(0))
						.setCardEntity(bean,mainActivity);
				LogUtil.d("object:", bean + "status:" + bean.getStatus());
				((TextView) (contentView.findViewById(R.id.tv_balance_left)))
						.setText(bean.getLastPay() + "元");
				if (bean.getStatus() == false)
					contentView.findViewById(R.id.btn_lockunlock)
							.setBackgroundResource(R.drawable.lock);
				else
					contentView.findViewById(R.id.btn_lockunlock)
							.setBackgroundResource(R.drawable.unlock);
				
				((LocalMessageSubject)IPreference.getInstance(mainActivity).getListOfNewsSubjectByID(0)).setCardEntity(bean, mainActivity);

			} else {
				Toast.makeText(mainActivity, "更新失败!",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onPause() {
		NetworkChangeReceiver.registNetWorkListener(this);
	}

	@Override
	public void onDataEnabled() {
		getData();
	}

	@Override
	public void onDataDisabled() {
		// TODO Auto-generated method stub

	};
}
