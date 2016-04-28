package org.zywx.wbpalmstar.plugin.uexpdf;

import java.io.File;

import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.plugin.uexpdf.util.MyLog;
import org.zywx.wbpalmstar.plugin.uexpdf.util.FileUtil;

import com.artifex.mupdfdemo.MuPDFActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;

/**
 * uexPDFReader
 * 
 * 基于MuPDF的PDF阅读器插件
 * 
 * @author waka
 * @version createTime:2016年4月20日 上午11:11:16
 */
public class EUExPDFReader extends EUExBase {

	public EUExPDFReader(Context arg0, EBrowserView arg1) {
		super(arg0, arg1);
	}

	/**
	 * 打开PDFReader
	 * 
	 * @param params
	 */
	public void openPDFReader(String[] params) {

		MyLog.getLog().i("start");

		if (params.length < 1) {
			MyLog.getLog().i("params.length < 1");
			return;
		}

		if (params[0].isEmpty()) {
			MyLog.getLog().i("params[0].isEmpty()");
			return;
		}

		String path = params[0];
		String absPath = FileUtil.getAbsPath(path, mBrwView);
		String fileName = FileUtil.makeFile(mContext, absPath);
		if (fileName == null) {
			MyLog.getLog().i("fileName == null");
			return;
		}

		Uri uri = Uri.fromFile(new File(fileName));
		MyLog.getLog().i("uri = " + uri.toString());

		Intent intent = new Intent(mContext, MuPDFActivity.class);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(uri);
		startActivityForResult(intent, Constant.REQUEST_CODE_PDF_PREVIEW_ACTIVITY);
	}

	/**
	 * 关闭
	 * 
	 * @param params
	 */
	public void close(String[] params) {

		MyLog.getLog().i("start");

		// 发送关闭Activity本地广播
		LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
		Intent intent = new Intent(Constant.ACTION_LOCAL_BROADCAST_CLOSE_ACTIVITY);
		localBroadcastManager.sendBroadcast(intent);

		MyLog.getLog().i("end");
	}

	@Override
	protected boolean clean() {
		return false;
	}

}
