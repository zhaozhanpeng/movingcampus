package edu.hebtu.movingcampus.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.activity.NewsDetailsActivity;
import edu.hebtu.movingcampus.adapter.base.AdapterBase;
import edu.hebtu.movingcampus.adapter.base.ItemListAdapter;
import edu.hebtu.movingcampus.entity.NewsShort;
import edu.hebtu.movingcampus.utils.ImageUtil;
import edu.hebtu.movingcampus.utils.ImageUtil.ImageCallback;
import edu.hebtu.movingcampus.utils.LogUtil;

//主页新闻+本地通知数据展示
public class InfoListAdapter extends ItemListAdapter<NewsShort> {
	/**
	 * initial
	 * 
	 * @param list
	 *            :news resource
	 * @param context
	 *            :app context
	 * @param resourceId
	 *            :item xml view
	 */
	public InfoListAdapter(final Context context, int resourceId,
			ListView list, List<NewsShort> mlist) {
		super(context,resourceId,list,mlist);
	}

	// position MK
	@Override
	public long getItemId(int position) {
		// return ((News)getItem(position)).getId();
		return mList.get(position).getID();
	}

	@Override
	protected View getNextView(final int position, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		// 构造一个布局文件加载器
		if (convertView == null) {
			LayoutInflater inflator = ((Activity) context).getLayoutInflater();
			convertView = inflator.inflate(rowlayout, null);
			final ViewHolder viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		final NewsShort news = (NewsShort) getItem(position);

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, NewsDetailsActivity.class);
				i.putExtra("id", news.getID()+"");
				context.startActivity(i);
			}
		});

		// show
		holder.title.setText(news.getTitle());
		holder.source.setText("教务处");
		holder.content.setText(news.getContent());
		if (news.getDate() != null)
			holder.time.setText(news.getDate().toLocaleString());
		//holder.icon.setImageResource(news.getIcon());
		String img_url = news.getThumbnail_url();
		if (img_url == null || img_url.equals("")) {
			// TODO default image icon for test
			holder.icon.setVisibility(View.VISIBLE);
			ImageUtil
					.setThumbnailView(
							"http://t1.baidu.com/it/u=3329574332,1288957616&fm=90&gp=0.jpg"
							,
							holder.icon, context, callback1, true);
		} else {
			holder.icon.setVisibility(View.VISIBLE);
			ImageUtil
					.setThumbnailView(
							"http://t1.baidu.com/it/u=3329574332,1288957616&fm=90&gp=0.jpg"
							,
							holder.icon, context, callback1, true);
		}

		// return 加载数据后的iew对象
		return convertView;
	}

	static class ViewHolder {
		public ViewHolder(View convertView) {
			this.title = (TextView) convertView.findViewById(R.id.news_title);
			this.time = (TextView) convertView
					.findViewById(R.id.news_pub_date_time);
			this.source = (TextView) convertView.findViewById(R.id.news_source);
			this.content = (TextView) convertView
					.findViewById(R.id.news_summary);
			this.icon = (ImageView) convertView.findViewById(R.id.news_img);
		}

		public TextView title;
		public TextView time;
		public TextView content;
		public TextView source;
		public ImageView icon;
	}

	@Override
	protected void onReachBottom() {
		// if(fragment!=null)
		// fragment.onLoadMore();
	}
}
