
package edu.hebtu.movingcampus.activity;

import com.tencent.street.StreetThumbListener;
import com.tencent.street.StreetViewListener;
import com.tencent.street.StreetViewShow;

import com.tencent.street.map.basemap.GeoPoint;
import com.tencent.street.overlay.ItemizedOverlay;

import edu.hebtu.movingcampus.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;

/**
 * 请在StreetViewShow.getInstance().showStreetView()方法输入申请的key，如下初始化所示，否则无法显示街景    
 *
 */
public class StreeViewSoso extends Activity implements StreetViewListener {
    private ViewGroup mView;

    private ImageView mImage;

    private Handler mHandler;
    
    private View mStreetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streetviewdemo);
        mView = (LinearLayout)findViewById(R.id.layout);
        mImage = (ImageView)findViewById(R.id.image);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mImage.setImageBitmap((Bitmap)msg.obj);
            }
        };
        
        GeoPoint center = new GeoPoint((int)(38.000805 * 1E6), (int)(114.520396 * 1E6));
        
//        String key = "please visit http://api.map.soso.com/";
        String key = "YR6BZ-CPQRF-W33JY-J2GAV-2U2K7-Q7B3I";
        
        StreetViewShow.getInstance().showStreetView(this, center, 100, this, -170, 0, key);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        StreetViewShow.getInstance().requestStreetThumb("10041002111120153536407",//"10011505120412110900000",
                new StreetThumbListener() {

                    @Override
                    public void onGetThumbFail() {
                       
                    }

                    @Override
                    public void onGetThumb(Bitmap bitmap) {
                        Message msg = new Message();
                        msg.obj = bitmap;
                        mHandler.sendMessage(msg);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onViewReturn(final View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	mStreetView = v;
                mView.addView(mStreetView);
            }
        });
    }

    @Override
    public void onNetError() {
//        LogUtil.logStreet("onNetError");
    }

    @Override
    public void onDataError() {
//        LogUtil.logStreet("onDataError");
    }

    
//    @Override
//    public ItemizedOverlay getOverlay() {
//        if (overlay == null) {
//            ArrayList<StreetPoiData> pois = new ArrayList<StreetPoiData>();
//            pois.add(new StreetPoiData(39984066, 116307968, getBm(R.drawable.poi_center),
//                    getBm(R.drawable.poi_center_pressed), 0));
//            pois.add(new StreetPoiData(39984166, 11630800, getBm(R.drawable.pin_green),
//                    getBm(R.drawable.pin_green_pressed), 40));
//            pois.add(new StreetPoiData(39984000, 116307968, getBm(R.drawable.pin_yellow),
//                    getBm(R.drawable.pin_yellow_pressed), 80));
//            pois.add(new StreetPoiData(39984066, 116308088, getBm(R.drawable.pin_red),
//                    getBm(R.drawable.pin_red_pressed), 120));
//            overlay = new StreetOverlay(pois);
//            overlay.populate();
//        }
//        return overlay;
//    }

    private Bitmap getBm(int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inScaled = false;

        return BitmapFactory.decodeResource(getResources(), resId, options);
    }

	@Override
	public void onLoaded() {
		runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	mStreetView.setVisibility(View.VISIBLE);
            }
        });
	}

    @Override
    public void onAuthFail() {
//        LogUtil.logStreet("onAuthFail");
    }

	@Override
	public ItemizedOverlay getOverlay() {
		// TODO Auto-generated method stub
		return null;
	}
}
