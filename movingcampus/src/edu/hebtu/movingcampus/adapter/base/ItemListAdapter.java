package edu.hebtu.movingcampus.adapter.base;

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
import edu.hebtu.movingcampus.entity.NewsShort;
import edu.hebtu.movingcampus.utils.ImageUtil;
import edu.hebtu.movingcampus.utils.ImageUtil.ImageCallback;
import edu.hebtu.movingcampus.utils.LogUtil;

//主页新闻+本地通知数据展示
public abstract class ItemListAdapter<T> extends AdapterBase<T> {
	protected Context context;
	protected int rowlayout;
	protected ListView list;
	protected edu.hebtu.movingcampus.utils.ImageUtil.ImageCallback callback1 = new ImageCallback() {

		@Override
		public void loadImage(Bitmap bitmap, String imagePath) {
			try {
				ImageView img = (ImageView) list.findViewWithTag(imagePath);
				img.setImageBitmap(bitmap);
			} catch (NullPointerException ex) {
				LogUtil.e("error", "ImageView = null");
			}
		}
	};

	// [end]

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
	public ItemListAdapter(final Context context, int resourceId,
			ListView list, List<T> mlist) {
		super(mlist);
		this.list = list;
		this.context = context;
		this.rowlayout = resourceId;
	}

	@Override
	protected abstract View getNextView(int position, View convertView, ViewGroup parent) ;

	@Override
	protected abstract void onReachBottom() ;
}
