package edu.hebtu.movingcampus.activity;

import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.activity.base.BaseActivity;
import edu.hebtu.movingcampus.activity.wrapper.IPreference;
import edu.hebtu.movingcampus.adapter.NewsListAdapter;
import edu.hebtu.movingcampus.entity.NewsShort;
import edu.hebtu.movingcampus.subjects.LocalNewsSubject;

public class LocalNewsActivity extends BaseActivity {
	private LocalNewsSubject subject = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_news);

		subject = (LocalNewsSubject) IPreference.getInstance(getBaseContext())
				.getListOfNewsSubjectByID(0);
		ListView list = (ListView) findViewById(R.id.localnews_list);

		List<NewsShort> initlist = subject.dump(LocalNewsActivity.this);
		NewsListAdapter adapter = new NewsListAdapter(this, R.layout.news_item,
				list, initlist);
		list.setAdapter(adapter);

		if (list == null || initlist.size() == 0)
			// init
			;
	}

	@Override
	protected void bindButton() {
		// TODO Auto-generated method stub

	}
}
