package org.zywx.wbpalmstar.plugin.uexpdf;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.RelativeLayout;

import com.artifex.mupdfdemo.MuPDFActivity;

import org.zywx.wbpalmstar.base.BUtility;
import org.zywx.wbpalmstar.engine.DataHelper;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.engine.universalex.EUExCallback;
import org.zywx.wbpalmstar.plugin.uexpdf.util.FileUtil;
import org.zywx.wbpalmstar.plugin.uexpdf.util.MyLog;
import org.zywx.wbpalmstar.plugin.uexpdf.vo.OpenVO;

import java.io.File;
import java.util.ArrayList;

/**
 * uexPDFReader
 * 
 * 基于MuPDF的PDF阅读器插件
 * 
 * @author waka
 * @version createTime:2016年4月20日 上午11:11:16
 */
public class EUExPDFReader extends EUExBase {

    private PDFView mPDFView;

    private int mOpenPDFReaderCallbackId = -1;

    private int mOpenViewCallbackId = -1;

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
		//处理回调
		if (params.length > 1) {
			try {
				mOpenPDFReaderCallbackId = Integer.valueOf(params[1]);
			} catch (Exception ignored) {
			}
		} else {
			mOpenPDFReaderCallbackId = -1;
		}

		String path = params[0];
		ArrayList<String> warterText=new ArrayList<>();
		if(params.length==2) {
		    warterText.add(params[1]);
		}

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
		intent.putStringArrayListExtra("warterText",warterText);
		startActivityForResult(intent, Constant.REQUEST_CODE_PDF_PREVIEW_ACTIVITY);
	}

    public void openView(String[] params){
		//处理回调
		if (params.length > 1) {
			try {
				mOpenViewCallbackId = Integer.valueOf(params[1]);
			} catch (Exception ignored) {
			}
		} else {
			mOpenViewCallbackId = -1;
		}
        final OpenVO openVO= DataHelper.gson.fromJson(params[0],OpenVO.class);
        openVO.path= BUtility.getRealPathWithCopyRes(mBrwView,openVO.path);
        if (mPDFView==null){
            mPDFView=new PDFView(mContext, openVO.path, new PDFView.PDFViewCallback() {
				@Override
				public void onStatus(boolean isSuccess) {
					//TODO 还未添加回调，需要时与iOS一起增加
					if (isSuccess){
						RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(openVO.width,openVO.height);
						lp.topMargin=openVO.y;
						lp.leftMargin=openVO.x;
						addViewToCurrentWindow(mPDFView,lp);
					}
					cbOpenViewToJS(isSuccess);
				}
			});
        }

    }

    public void closeView(String[] params){
        if (mPDFView!=null){
            mPDFView.close();
            removeViewFromCurrentWindow(mPDFView);
            mPDFView=null;
        }
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

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constant.REQUEST_CODE_PDF_PREVIEW_ACTIVITY){
			if (data != null){
				//默认是成功的，因为可能会有没有照顾到的其他途径关闭了Activity未放入成功状态
				boolean isSuccess = true;
				try {
					isSuccess = data.getBooleanExtra("isSuccess", true);
				} catch (Exception ignore) {
				}
				cbOpenToJS(isSuccess);
			}else{
				cbOpenToJS(true);
			}
		}
	}

	private void cbOpenToJS(boolean isSuccess){
		if (mOpenPDFReaderCallbackId != -1) {
			callbackToJs(mOpenPDFReaderCallbackId, false, isSuccess ? EUExCallback.F_C_SUCCESS : EUExCallback.F_C_FAILED);
		}else{
			MyLog.getLog().i("cbOpenToJS: isSuccess? " + isSuccess + ", but no cbFunction to handle!!!");
		}
	}

	private void cbOpenViewToJS(boolean isSuccess){
		if (mOpenViewCallbackId != -1) {
			callbackToJs(mOpenViewCallbackId, false, isSuccess ? EUExCallback.F_C_SUCCESS : EUExCallback.F_C_FAILED);
		}else{
			MyLog.getLog().i("cbOpenViewToJS: isSuccess? " + isSuccess + ", but no cbFunction to handle!!!");
		}
	}

	@Override
	protected boolean clean() {
		return false;
	}

}
