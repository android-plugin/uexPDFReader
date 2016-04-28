package com.artifex.mupdfdemo;

import org.zywx.wbpalmstar.plugin.uexpdf.CloseActivityReceiver;
import org.zywx.wbpalmstar.plugin.uexpdf.util.LocalBroadcastUtil;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class OutlineActivity extends ListActivity {

	OutlineItem mItems[];

	// TODO
	// 关闭Activity本地广播接收器
	private CloseActivityReceiver mCloseActivityReceiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mItems = OutlineActivityData.get().items;
		setListAdapter(new OutlineAdapter(getLayoutInflater(), mItems));
		// Restore the position within the list from last viewing
		getListView().setSelection(OutlineActivityData.get().position);
		getListView().setDividerHeight(0);
		setResult(-1);

		// TODO
		// 注册关闭Activity本地广播
		mCloseActivityReceiver = new CloseActivityReceiver(this);
		if (mCloseActivityReceiver != null) {
			LocalBroadcastUtil.registerLocalBroadcastCloseActivity(mCloseActivityReceiver);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		OutlineActivityData.get().position = getListView().getFirstVisiblePosition();
		setResult(mItems[position].page);
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// TODO
		// 注销关闭Activity本地广播
		if (mCloseActivityReceiver != null) {
			LocalBroadcastUtil.unregisterLocalBroadcastCloseActivity(mCloseActivityReceiver);
		}
	}
}
