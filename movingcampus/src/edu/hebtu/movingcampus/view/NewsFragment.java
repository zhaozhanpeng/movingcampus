package edu.hebtu.movingcampus.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HandshakeCompletedListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.activity.wrapper.IPreference;
import edu.hebtu.movingcampus.activity.wrapper.InfoCenterActivity;
import edu.hebtu.movingcampus.adapter.InfoListAdapter;
import edu.hebtu.movingcampus.biz.NewsDao;
import edu.hebtu.movingcampus.entity.NewsShort;
import edu.hebtu.movingcampus.subject.base.ListOfNews;
import edu.hebtu.movingcampus.subjects.NetworkChangeReceiver;
import edu.hebtu.movingcampus.utils.NetWorkHelper;
import edu.hebtu.movingcampus.utils.TimeUtil;
import edu.hebtu.movingcampus.widget.XListView;

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class NewsFragment extends BaseListFragment implements Callback{

	public Activity mActivity;
	private Button bn_refresh;
	private String page;
	public boolean loaded = false;
	private InfoListAdapter mAdapter;
	private List<NewsShort> loadMoreEntity;
	private List<NewsShort> mlist;
	private static SimpleDateFormat mDateFormat;
	private TimeUtil timer;

	// private DisplayImageOptions options;
	private Handler mHandler;
	private LinearLayout loadLayout;
	private LinearLayout loadFaillayout;

	@Override
	public void onAttach(Activity ac) {
		super.onAttach(ac);
		try {
			Looper.prepare();
		} catch (Exception e) {
		}
		mHandler = new Handler(this); 
		if (mDateFormat == null) {
			mDateFormat = new SimpleDateFormat("MM月dd日");
		}
		view = ac.getLayoutInflater().inflate(R.layout.newsxlist, null, true);
		bn_refresh = (Button) view.findViewById(R.id.btn_refresh);
		bn_refresh.setOnClickListener(InfoCenterActivity.getInstance());
		loadLayout = (LinearLayout) view.findViewById(R.id.loading_layout_view);
		loadFaillayout = (LinearLayout)view.findViewById(R.id.load_failed_layout_view);
		this.mActivity = ac;
		listview = (XListView) view.findViewById(R.id.list_view);
		mlist = ((ListOfNews)IPreference.getInstance(ac)
				.getListOfNewsSubjectByID(Integer.parseInt(page) + 1)).dump(ac);
		mAdapter = new InfoListAdapter(ac, R.layout.news_item, listview, mlist);
	}

	public static NewsFragment getInstance(String page, Activity activty) {
		NewsFragment nf = new NewsFragment();
		nf.page = page;
		nf.onAttach(activty);
		return nf;
	}

	private void updateTextTime() {
		boolean isAdded = isAdded();
		if (!isAdded) {// avoid java.lang.IllegalStateException: Fragment
						// BaseFragment{44b01260} not attached to Activity
			return;
		}
		if (timer.getUpdateTime() == 0) {// 初始化更新
			listview.setRefreshTime(getResources().getString(
					R.string.listview_header_last_time));
		} else {// 其他
			long diffTimeSecs = (System.currentTimeMillis() - timer
					.getUpdateTime()) / 1000;
			// 1min = 60s ; 1h = 60min
			if (diffTimeSecs < 3600) {// 一小时内，显示分钟
				Resources resources = getResources();
				if (resources != null) {
					listview.setRefreshTime(resources.getString(
							R.string.listview_header_last_time_for_min,
							diffTimeSecs < 60 ? 1 : diffTimeSecs / 60));
				}
			} else {
				long diffTimeHours = diffTimeSecs / 3600;
				if (diffTimeHours < 24) {// 一天内更新，显示小时
					listview.setRefreshTime(getResources().getString(
							R.string.listview_header_last_time_for_hour,
							diffTimeHours));
				} else if (diffTimeHours == 24) {// 一天更新，显示1天
					listview.setRefreshTime(getResources().getString(
							R.string.listview_header_last_time_for_day, 1));
				} else {// 大于24小时显示xx月xx日
					listview.setRefreshTime(getResources()
							.getString(
									R.string.listview_header_last_time_for_date,
									mDateFormat.format(new Date(timer
											.getUpdateTime()))));
				}
			}
		}
		timer.updatePreferenceTime();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		timer = new TimeUtil(mActivity, getClass().getName() + "page");
		updateTextTime();
		listview.setXListViewListener(this);
		listview.setPullRefreshEnable(true);
		listview.setPullLoadEnable(true);
		// construct the RelativeLayout
		mAdapter.appendToList(loadMoreEntity);
		listview.setAdapter(mAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				NewsShort item = (NewsShort) mAdapter.getItem(position);
				startDetailActivity(mActivity, item.getID() + "");
			}
		});
		return view;
	}

	public boolean toBeLoad() {
		return mAdapter.getList() == null || mAdapter.getList().size() == 0;
	}

	/**
	 * 按时间排序
	 */
	@Override
	public void onRefresh() {
		onStopLoad();
		if(NetWorkHelper.isNetworkAvailable(mActivity)){
			mAdapter.getList().clear();
			onLoadMore();
		}else{
			Toast.makeText(mActivity, "请检查您的网络连接!", Toast.LENGTH_SHORT).show();
		}
	}

	public List<NewsShort> onLoad() {
		if (mlist == null || mlist.size() == 0)
			loadMoreEntity = new NewsDao(mActivity).mapperJson(true,
					(Integer.parseInt(page) + 1) + "", (mlist.size() + 1) + "",
					null);
		else
			loadMoreEntity = mlist;
		return loadMoreEntity;
	}

	@Override
	public void onLoadMore() {
		new Thread() {
			@Override
			public void run() {
				loadMoreEntity = new NewsDao(mActivity).mapperJson(true,
						(Integer.parseInt(page) + 1) + "", (mlist.size() + 1)
								+ "", null);
				if (loadMoreEntity != null) {
					mHandler.sendEmptyMessage(0);
				} else
					mHandler.sendEmptyMessage(1);
				super.run();
			}
		}.start();
	}

	public String getPage() {
		return page;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (view != null) {
			ViewGroup parentViewGroup = (ViewGroup) view.getParent();
			if (parentViewGroup != null) {
				parentViewGroup.removeAllViews();
			}
		}
	}

	public InfoListAdapter getAdapter() {
		return mAdapter;
	}

	public void onPreExecute() {
		loadLayout.setVisibility(View.VISIBLE);
		listview.setVisibility(View.INVISIBLE);
		loadFaillayout.setVisibility(View.INVISIBLE);
	}

	public void onPostExecute(List<NewsShort> result) {
		if (result != null) {
			loadLayout.setVisibility(View.INVISIBLE);
			loadFaillayout.setVisibility(View.INVISIBLE);
			listview.setVisibility(View.VISIBLE);
		} else {
			// mBasePageAdapter.addNullFragment();
			listview.setVisibility(View.INVISIBLE);
			bn_refresh.setVisibility(View.VISIBLE);
			loadLayout.setVisibility(View.INVISIBLE);
			loadFaillayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 0:
			updateTextTime();
			mAdapter.appendToList(loadMoreEntity);
			loaded = true;
			break;
		case 1:
			loaded = true;
			listview.getFooterView().updateFooterTextNoMore();
			break;

		default:
			loaded = false;
			break;
		}
		onStopLoad();
		return false;
	}
}
