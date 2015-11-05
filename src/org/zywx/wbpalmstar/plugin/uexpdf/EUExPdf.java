package org.zywx.wbpalmstar.plugin.uexpdf;

import java.io.File;

import org.ebookdroid.common.settings.books.Bookmark;
import org.ebookdroid.ui.viewer.ViewerActivity;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;

public class EUExPdf extends EUExBase {

	private static final String TAG = "EUExPdf";
	public static Context context;
	private boolean isOpen = false;
	public static ViewerActivity instance;

	public EUExPdf(Context context, EBrowserView inParent) {
		super(context, inParent);
		EUExUtil.init(mContext);
		
	}

	public void openPDFReader(String[] args) {
		if (args == null || args.length < 1) {
			return;
		}
		final String path = args[0];

		if (path == null || path.length() == 0) {
			PdfUtils.showMsg((Activity) mContext,
					"plugin_pdf_file_name_is_null");
			return;
		}
	
//		if(!isOpen)
//		{
			closeWindow();
			openPdf(PdfUtils.getAbsPath(path, mBrwView)); 
//		}
	}
	
	
	public void close(String[] args)
	{
//		LocalActivityManager mgr = ((ActivityGroup) mContext)
//				.getLocalActivityManager();
//		ViewerActivity viewerActivity = (ViewerActivity) mgr.getActivity(TAG);
//		if(viewerActivity != null){
//			View view = viewerActivity.getWindow().getDecorView();
//			removeViewFromCurrentWindow(view);
//			viewerActivity.finish();
//			viewerActivity = null;
//			view = null;
//		}
        if (instance != null){
            instance.finish();
            instance = null;
        }
		
		if(isOpen){
			isOpen = false;
		}
		
	}
	

	private AsyncTask<Void, Void, String> task = null;

	private void openPdf(final String file) {
		if (task != null) {
			task.cancel(true);
			task = null;
		}
		task = new OpenTask(file);
		task.execute();

	}

	class OpenTask extends AsyncTask<Void, Void, String> {
		String file;
		Dialog dialog = null;

		public OpenTask(String file) {
			this.file = file;
		}

		@Override
		protected void onPreExecute() {
			dialog = PdfUtils.showLoadDialog(mContext);
		}

		@Override
		protected String doInBackground(Void... params) {
			return PdfUtils.makeFile(mContext, file);
		}

		@Override
		protected void onPostExecute(String result) {
			if (dialog != null) {
				dialog.dismiss();
			}
			if (file == null || file.length() == 0) {
				PdfUtils.showMsg((Activity) mContext,
						"plugin_pdf_file_name_is_null");
				return;
			}
			if(result == null) {
				PdfUtils.showMsg((Activity) mContext,
						"plugin_pdf_file_not_exists");
				return;
			}
			final File f = new File(result);
			if (!f.exists()) {
				PdfUtils.showMsg((Activity) mContext,
						"plugin_pdf_file_not_exists");
				return;
			}
			//showDocument(Uri.fromFile(f), null);
			
			final Intent intent = new Intent(Intent.ACTION_VIEW, Uri
					.fromFile(f));
			intent.setClass(mContext, ViewerActivity.class);
			startActivity(intent);
            isOpen = true;
			
//			((ActivityGroup) mContext).runOnUiThread(new Runnable() {

//				@SuppressWarnings("deprecation")
//				@Override
//				public void run() {
//					final Intent intent = new Intent(Intent.ACTION_VIEW, Uri
//							.fromFile(f));
//					intent.setClass(mContext, ViewerActivity.class);
//					LocalActivityManager mgr = ((ActivityGroup) mContext)
//							.getLocalActivityManager();
//					Window window = mgr.startActivity(TAG, intent);
//					ViewerActivity viewerContext = (ViewerActivity) window.getContext();
//					viewerContext.setEUExPdf(EUExPdf.this);
//					View mView = window.getDecorView();
//					RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
//							RelativeLayout.LayoutParams.FILL_PARENT,
//							RelativeLayout.LayoutParams.FILL_PARENT);
//					lp.topMargin = 300;
//					lp.leftMargin=10;
//					addViewToCurrentWindow(mView, lp);
//					if(!isOpen)
//					{
//						isOpen=!isOpen;
//					}
				}
//			});
			
//			task = null;
//		}

		@Override
		protected void onCancelled() {
			cancel(true);
		}
	}

	@Override
	protected boolean clean() {
		return false;
	}

	public void showDocument(final Uri uri, final Bookmark b) {

		final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		intent.setClass(mContext, ViewerActivity.class);
		if (b != null) {
			intent.putExtra("pageIndex", "" + b.page.viewIndex);
			intent.putExtra("offsetX", "" + b.offsetX);
			intent.putExtra("offsetY", "" + b.offsetY);
		}
		startActivity(intent);
	}
	
	public void closeWindow()
	{
		close(null);
	}
	
	 

}
