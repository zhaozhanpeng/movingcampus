package edu.hebtu.movingcampus.activity.base;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.utils.LogUtil;

public abstract class BaseAsyncTaskActivity extends Activity {

	protected static String TAG = "BaseActivity";

	protected AlertDialog alertDialog;
	protected ProgressDialog processBar;
	protected AsyncTask asyncTask;

	/******************************** 【Activity LifeCycle For Debug】 *******************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LogUtil.d(TAG, this.getClass().getSimpleName()
				+ " onCreate() invoked!!");
		TAG=this.getClass().getName();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		MobclickAgent.onError(this);
	}

	@Override
	protected void onStart() {
		LogUtil.d(TAG, this.getClass().getSimpleName() + " onStart() invoked!!");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		LogUtil.d(TAG, this.getClass().getSimpleName()
				+ " onRestart() invoked!!");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		LogUtil.d(TAG, this.getClass().getSimpleName()
				+ " onResume() invoked!!");
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		LogUtil.d(TAG, this.getClass().getSimpleName() + " onPause() invoked!!");
		super.onPause();
		try {
			MobclickAgent.onPause(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onStop() {
		LogUtil.d(TAG, this.getClass().getSimpleName() + " onStop() invoked!!");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		LogUtil.d(TAG, this.getClass().getSimpleName()
				+ " onDestroy() invoked!!");
		super.onDestroy();

		if (asyncTask != null && asyncTask.isCancelled() == false) {
			asyncTask.cancel(false);
			asyncTask = null;
		}
		if (alertDialog != null) {
			alertDialog.dismiss();
			alertDialog = null;
		}
	}

	public void recommandToYourFriend(String url, String shareTitle) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, shareTitle + "   " + url);

		Intent itn = Intent.createChooser(intent, "分享");
		startActivity(itn);
	}

	/******************************** 【Activity LifeCycle For Debug】 *******************************************/

	protected void showShortToast(int pResId) {
		showShortToast(getString(pResId));
	}

	protected void showLongToast(String pMsg) {
		Toast.makeText(this, pMsg, Toast.LENGTH_LONG).show();
	}

	protected void showShortToast(String pMsg) {
		Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
	}

	protected boolean hasExtra(String pExtraKey) {
		if (getIntent() != null) {
			return getIntent().hasExtra(pExtraKey);
		}
		return false;
	}

	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * 通过反射来设置对话框是否要关闭，在表单校验时很管用， 因为在用户填写出错时点确定时默认Dialog会消失， 所以达不到校验的效果
	 * 而mShowing字段就是用来控制是否要消失的，而它在Dialog中是私有变量， 所有只有通过反射去解决此问题
	 * 
	 * @param pDialog
	 * @param pIsClose
	 */
	public void setAlertDialogIsClose(DialogInterface pDialog, Boolean pIsClose) {
		try {
			Field field = pDialog.getClass().getSuperclass()
					.getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(pDialog, pIsClose);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected AlertDialog showAlertDialog(String TitleID, String Message) {
		alertDialog = new AlertDialog.Builder(this).setTitle(TitleID)
				.setMessage(Message).show();
		return alertDialog;
	}

	protected AlertDialog showAlertDialog(int pTitelResID, String pMessage,
			DialogInterface.OnClickListener pOkClickListener) {
		String title = getResources().getString(pTitelResID);
		return showAlertDialog(title, pMessage, pOkClickListener, null, null);
	}

	protected AlertDialog showAlertDialog(String pTitle, String pMessage,
			DialogInterface.OnClickListener pOkClickListener,
			DialogInterface.OnClickListener pCancelClickListener,
			DialogInterface.OnDismissListener pDismissListener) {
		alertDialog = new AlertDialog.Builder(this)
				.setTitle(pTitle)
				.setMessage(pMessage)
				.setPositiveButton(android.R.string.ok, pOkClickListener)
				.setNegativeButton(android.R.string.cancel,
						pCancelClickListener).show();
		if (pDismissListener != null) {
			alertDialog.setOnDismissListener(pDismissListener);
		}
		return alertDialog;
	}

	protected AlertDialog showAlertDialog(String pTitle, String pMessage,
			String pPositiveButtonLabel, String pNegativeButtonLabel,
			DialogInterface.OnClickListener pOkClickListener,
			DialogInterface.OnClickListener pCancelClickListener,
			DialogInterface.OnDismissListener pDismissListener) {
		alertDialog = new AlertDialog.Builder(this).setTitle(pTitle)
				.setMessage(pMessage)
				.setPositiveButton(pPositiveButtonLabel, pOkClickListener)
				.setNegativeButton(pNegativeButtonLabel, pCancelClickListener)
				.show();
		if (pDismissListener != null) {
			alertDialog.setOnDismissListener(pDismissListener);
		}
		return alertDialog;
	}

	protected ProgressDialog showProgressDialog(int pTitelResID,
			String pMessage,
			DialogInterface.OnCancelListener pCancelClickListener) {
		String title = getResources().getString(pTitelResID);
		return showProgressDialog(title, pMessage, pCancelClickListener);
	}

	protected ProgressDialog showProgressDialog(String pTitle, String pMessage,
			DialogInterface.OnCancelListener pCancelClickListener) {
		alertDialog = ProgressDialog.show(this, pTitle, pMessage, true, true);
		alertDialog.setOnCancelListener(pCancelClickListener);
		return (ProgressDialog) alertDialog;
	}

	protected void hideKeyboard(View view) {
		InputMethodManager imm = (InputMethodManager) this
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	protected void handleOutmemoryError() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(BaseAsyncTaskActivity.this, "内存空间不足！", Toast.LENGTH_SHORT)
						.show();
				// finish();
			}
		});
	}

	private int network_err_count = 0;

	protected void handleNetworkError() {
		network_err_count++;
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (network_err_count < 3) {
					Toast.makeText(BaseAsyncTaskActivity.this, "网速好像不怎么给力啊！",
							Toast.LENGTH_SHORT).show();
				} else if (network_err_count < 5) {
					Toast.makeText(BaseAsyncTaskActivity.this, "网速真的不给力！",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(BaseAsyncTaskActivity.this, "唉，今天的网络怎么这么差劲！",
							Toast.LENGTH_SHORT).show();
				}
				// finish();
			}
		});
	}

	protected void handleMalformError() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(BaseAsyncTaskActivity.this, "数据格式错误！", Toast.LENGTH_SHORT)
						.show();
				// finish();
			}
		});
	}

	protected void handleFatalError() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(BaseAsyncTaskActivity.this, "发生了一点意外，程序终止！",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}

	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}

	public void defaultFinish() {
		super.finish();
	}

	/**
	 * show processgressDialog 
	 * @param tip  remind string
	 * @param cancleAble  wether progressdialog is cancelAble
	 */
	protected void previewProcess(String tip,boolean cancleAble){
		this.processBar = ProgressDialog.show(
				this, "", tip);
		this.processBar.onStart();
		this.processBar.show();
		this.processBar.setCancelable(cancleAble);
	}

	protected abstract void bindButton();

}
