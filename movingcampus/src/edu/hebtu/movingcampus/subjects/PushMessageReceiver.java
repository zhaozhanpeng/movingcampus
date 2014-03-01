﻿package edu.hebtu.movingcampus.subjects;

import java.util.Date;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.baidu.android.pushservice.PushConstants;

import edu.hebtu.movingcampus.activity.LocalNewsActivity;
import edu.hebtu.movingcampus.activity.MainActivity;
import edu.hebtu.movingcampus.activity.Show_Score;
import edu.hebtu.movingcampus.activity.wrapper.IPreference;
import edu.hebtu.movingcampus.entity.MMessage;
import edu.hebtu.movingcampus.utils.LogUtil;

public class PushMessageReceiver extends BroadcastReceiver {

	//baidupush,card,libraryy
	/** TAG to Log */
	public static final String TAG = PushMessageReceiver.class.getSimpleName();

	AlertDialog.Builder builder;

	/**
	 * @param context
	 *            Context
	 * @param intent
	 *            接收的intent
	 */
	@Override
	public void onReceive(final Context context, Intent intent) {

		LogUtil.d(TAG, ">>> Receive intent: \r\n" + intent);
		// 获取消息的自定义内容
		if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
			// 获取消息内容
			String message = intent.getExtras().getString(
					PushConstants.EXTRA_PUSH_MESSAGE_STRING);

			// 消息的用户自定义内容读取方式
			LogUtil.i(TAG, "onMessage: " + message);

			// 自定义内容的json串
			String customData = intent
					.getStringExtra(PushConstants.EXTRA_EXTRA);

			LogUtil.d(TAG, "EXTRA_EXTRA = " + customData);

		} else if (intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
			// 处理绑定等方法的返回数据
			// PushManager.startWork()的返回值通过PushConstants.METHOD_BIND得到

			// 获取方法
			final String method = intent
					.getStringExtra(PushConstants.EXTRA_METHOD);
			// 方法返回错误码。若绑定返回错误（非0），则应用将不能正常接收消息。
			// 绑定失败的原因有多种，如网络原因，或access token过期。
			// 请不要在出错时进行简单的startWork调用，这有可能导致死循环。
			// 可以通过限制重试次数，或者在其他时机重新调用来解决。
			int errorCode = intent.getIntExtra(PushConstants.EXTRA_ERROR_CODE,
					PushConstants.ERROR_SUCCESS);
			String content = "",
			title = "",time="";
			if (intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT) != null) {
				// 返回内容
				content = new String(
						intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT));

			}
			// 可选。通知用户点击事件处理
		} else if (intent.getAction().equals(
				PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)) {
			LogUtil.d(TAG, "intent=" + intent.toUri(0));

			// 自定义内容的json串
			String customData = intent
					.getStringExtra(PushConstants.EXTRA_EXTRA);

			LogUtil.d(TAG, "EXTRA_EXTRA = " + customData);
			// if (customData == null || "".equals(customData)) {
			// return;
			// }
			String title = intent
					.getStringExtra(PushConstants.EXTRA_NOTIFICATION_TITLE);

			Intent aIntent = new Intent();
			aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// 默认
			aIntent.setClass(context, LocalNewsActivity.class);

			aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_TITLE, title);
			String content = intent
					.getStringExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT);
			

			String time = new String(
					intent.getByteArrayExtra(PushConstants.EXTRA_TIMESTAMP));
			
			MMessage m=new MMessage();
			m.setContent(content);
			m.setTitle(title);
			m.setTime(new Date());

			((LocalMessageSubject)IPreference.getInstance(context).getSubjectByTag
					(new LocalMessageSubject().getTag())).addMessageeList(m);

			// 向消息详细页发送内容
			aIntent.putExtra(PushConstants.EXTRA_NOTIFICATION_CONTENT, content);
			context.startActivity(aIntent);
		}
	}

}
