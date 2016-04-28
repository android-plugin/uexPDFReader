package org.zywx.wbpalmstar.plugin.uexpdf.util;

import org.zywx.wbpalmstar.plugin.uexpdf.CloseActivityReceiver;
import org.zywx.wbpalmstar.plugin.uexpdf.Constant;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * 本地广播工具类
 * 
 * @author waka
 * @version createTime:2016年4月28日 上午9:15:07
 */
public class LocalBroadcastUtil {

	/**
	 * 注册关闭Activity本地广播
	 * 
	 * @param receiver
	 */
	public static void registerLocalBroadcastCloseActivity(CloseActivityReceiver receiver) {

		IntentFilter filter = new IntentFilter();
		filter.addAction(Constant.ACTION_LOCAL_BROADCAST_CLOSE_ACTIVITY);
		LocalBroadcastManager.getInstance(receiver.getContext()).registerReceiver(receiver, filter);
	}

	/**
	 * 注销关闭Activity本地广播
	 * 
	 * @param receiver
	 */
	public static void unregisterLocalBroadcastCloseActivity(CloseActivityReceiver receiver) {

		LocalBroadcastManager.getInstance(receiver.getContext()).unregisterReceiver(receiver);
	}

}
