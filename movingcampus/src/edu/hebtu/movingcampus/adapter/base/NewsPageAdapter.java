package edu.hebtu.movingcampus.adapter.base;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import edu.hebtu.movingcampus.activity.wrapper.IPreference;
import edu.hebtu.movingcampus.subject.base.ListOfNews;
import edu.hebtu.movingcampus.view.NewsFragment;

public class NewsPageAdapter extends FragmentPagerAdapter {

	private NewsFragment[] fragments;
	private Activity mActivity;
	private Context context;
	private List<ListOfNews> list;

	public NewsPageAdapter(FragmentActivity activity) {
		super(activity.getSupportFragmentManager());
		this.mActivity = activity;
		this.context = activity;
		this.list = IPreference.getInstance(context).getListOfNewsSubject();

		this.fragments = new NewsFragment[list.size()];
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return list.get(position).getDesc();
	}

	@Override
	public Fragment getItem(int arg0) {
		NewsFragment fragment = fragments[arg0];
		if (fragment == null) {
			fragment = NewsFragment.getInstance(
					list.get(arg0).getId() - 1 + "", mActivity);
			return fragments[arg0] = fragment;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		list = IPreference.getInstance(context).getListOfNewsSubject();
		fragments = new NewsFragment[list.size()];
		return list.size();
	}

	@Override
	public int getItemPosition(Object object) {
		for (int i = 0; i < fragments.length; i++) {
			if (fragments[i] == null)
				getItem(i);
			if (fragments[i].equals(object))
				return i;
		}
		return POSITION_NONE;
	}

	//

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}

	public List<NewsFragment> getFragments() {
		getCount();
		return Arrays.asList(fragments);
	}
}
