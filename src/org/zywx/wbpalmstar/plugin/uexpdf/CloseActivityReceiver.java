package org.zywx.wbpalmstar.plugin.uexpdf;

import org.zywx.wbpalmstar.plugin.uexpdf.util.MyLog;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 关闭Activity本地广播接收器
 * 
 * @author waka
 * @version createTime:2016年4月28日 上午9:05:10
 */
public class CloseActivityReceiver extends BroadcastReceiver {

	private Activity mActivity;

	/**
	 * 构造方法，须传入Activity
	 * 
	 * @param activity
	 */
	public CloseActivityReceiver(Activity activity) {
		mActivity = activity;
	}

	/**
	 * onReceive
	 */
	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();
		MyLog.getLog().i("action = " + action);

		// 关闭Activity广播
		if (action.equals(Constant.ACTION_LOCAL_BROADCAST_CLOSE_ACTIVITY)) {

			MyLog.getLog().i("关闭Activity广播");
			mActivity.finish();
		}
	}

	public Context getContext() {

		return mActivity;
	}

}
