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
import edu.hebtu.movingcampus.entity.MMessage;
import edu.hebtu.movingcampus.entity.NewsShort;
import edu.hebtu.movingcampus.utils.ImageUtil;
import edu.hebtu.movingcampus.utils.ImageUtil.ImageCallback;
import edu.hebtu.movingcampus.utils.LogUtil;

//主页新闻+本地通知数据展示
public class MessageListAdapter extends ItemListAdapter<MMessage> {
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
	public MessageListAdapter(final Context context, int resourceId,
			ListView list, List<MMessage> mlist) {
		super(context,resourceId,list,mlist);
	}

	// position MK
	@Override
	public long getItemId(int position) {
		// return ((News)getItem(position)).getId();
		return position;
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
		final MMessage news = (MMessage) getItem(position);

//		convertView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent i = new Intent(context, NewsDetailsActivity.class);
//				i.putExtra("id", MMessage.getID()+"");
//				context.startActivity(i);
//			}
//		});

		// show
		holder.title.setText(news.getTitle());
		holder.content.setText(news.getContent());
		if (news.getTime() != null)
			holder.time.setText(news.getTime().toLocaleString());
		// return 加载数据后的iew对象
		return convertView;
	}

	static class ViewHolder {
		public ViewHolder(View convertView) {
			this.title = (TextView) convertView.findViewById(R.id.tv_message_person);
			this.time = (TextView) convertView
					.findViewById(R.id.tv_message_day);
			this.content = (TextView) convertView
					.findViewById(R.id.tv_message_content);
		}

		public TextView title;
		public TextView time;
		public TextView content;
	}

	@Override
	protected void onReachBottom() {
		// if(fragment!=null)
		// fragment.onLoadMore();
	}
}
