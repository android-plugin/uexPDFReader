package com.artifex.mupdfdemo;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;
import org.zywx.wbpalmstar.plugin.uexpdf.CloseActivityReceiver;
import org.zywx.wbpalmstar.plugin.uexpdf.util.LocalBroadcastUtil;
import org.zywx.wbpalmstar.plugin.uexpdf.util.MyLog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

enum Purpose {
	PickPDF, PickKeyFile
}

public class ChoosePDFActivity extends ListActivity {
	static public final String PICK_KEY_FILE = "com.artifex.mupdfdemo.PICK_KEY_FILE";
	static private File mDirectory;
	static private Map<String, Integer> mPositions = new HashMap<String, Integer>();
	private File mParent;
	private File[] mDirs;
	private File[] mFiles;
	private Handler mHandler;
	private Runnable mUpdateFiles;
	private ChoosePDFAdapter adapter;
	private Purpose mPurpose;

	// TODO
	// 关闭Activity本地广播接收器
	private CloseActivityReceiver mCloseActivityReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mPurpose = PICK_KEY_FILE.equals(getIntent().getAction()) ? Purpose.PickKeyFile : Purpose.PickPDF;

		String storageState = Environment.getExternalStorageState();

		if (!Environment.MEDIA_MOUNTED.equals(storageState)
				&& !Environment.MEDIA_MOUNTED_READ_ONLY.equals(storageState)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(EUExUtil.getString("plugin_uexpdfreader_no_media_warning"));
			builder.setMessage(EUExUtil.getString("plugin_uexpdfreader_no_media_hint"));
			AlertDialog alert = builder.create();
			alert.setButton(AlertDialog.BUTTON_POSITIVE, EUExUtil.getString("plugin_uexpdfreader_dismiss"),
					new OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							finish();
						}
					});
			alert.show();
			return;
		}

		if (mDirectory == null)
			mDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

		// Create a list adapter...
		adapter = new ChoosePDFAdapter(getLayoutInflater());
		setListAdapter(adapter);

		// ...that is updated dynamically when files are scanned
		mHandler = new Handler();
		mUpdateFiles = new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Resources res = getResources();
				String appName = EUExUtil.getString("app_name");
				String version = "";// res.getString(R.string.version);
				String title = EUExUtil.getString("plugin_uexpdfreader_picker_title_App_Ver_Dir");
				setTitle(String.format(title, appName, version, mDirectory));

				mParent = mDirectory.getParentFile();

				mDirs = mDirectory.listFiles(new FileFilter() {

					public boolean accept(File file) {
						return file.isDirectory();
					}
				});
				if (mDirs == null)
					mDirs = new File[0];

				mFiles = mDirectory.listFiles(new FileFilter() {

					@SuppressLint("DefaultLocale")
					public boolean accept(File file) {
						if (file.isDirectory())
							return false;
						String fname = file.getName().toLowerCase();
						switch (mPurpose) {
						case PickPDF:
							if (fname.endsWith(".pdf"))
								return true;
							if (fname.endsWith(".xps"))
								return true;
							if (fname.endsWith(".cbz"))
								return true;
							if (fname.endsWith(".epub"))
								return true;
							if (fname.endsWith(".png"))
								return true;
							if (fname.endsWith(".jpe"))
								return true;
							if (fname.endsWith(".jpeg"))
								return true;
							if (fname.endsWith(".jpg"))
								return true;
							if (fname.endsWith(".jfif"))
								return true;
							if (fname.endsWith(".jfif-tbnl"))
								return true;
							if (fname.endsWith(".tif"))
								return true;
							if (fname.endsWith(".tiff"))
								return true;
							return false;
						case PickKeyFile:
							if (fname.endsWith(".pfx"))
								return true;
							return false;
						default:
							return false;
						}
					}
				});
				if (mFiles == null)
					mFiles = new File[0];

				Arrays.sort(mFiles, new Comparator<File>() {
					public int compare(File arg0, File arg1) {
						return arg0.getName().compareToIgnoreCase(arg1.getName());
					}
				});

				Arrays.sort(mDirs, new Comparator<File>() {
					public int compare(File arg0, File arg1) {
						return arg0.getName().compareToIgnoreCase(arg1.getName());
					}
				});

				adapter.clear();
				if (mParent != null)
					adapter.add(new ChoosePDFItem(ChoosePDFItem.Type.PARENT,
							EUExUtil.getString("plugin_uexpdfreader_parent_directory")));
				for (File f : mDirs)
					adapter.add(new ChoosePDFItem(ChoosePDFItem.Type.DIR, f.getName()));
				for (File f : mFiles)
					adapter.add(new ChoosePDFItem(ChoosePDFItem.Type.DOC, f.getName()));

				lastPosition();
			}
		};

		// Start initial file scan...
		mHandler.post(mUpdateFiles);

		// ...and observe the directory and scan files upon changes.
		FileObserver observer = new FileObserver(mDirectory.getPath(), FileObserver.CREATE | FileObserver.DELETE) {
			public void onEvent(int event, String path) {
				mHandler.post(mUpdateFiles);
			}
		};
		observer.startWatching();

		// TODO
		// 注册关闭Activity本地广播
		mCloseActivityReceiver = new CloseActivityReceiver(this);
		if (mCloseActivityReceiver != null) {
			LocalBroadcastUtil.registerLocalBroadcastCloseActivity(mCloseActivityReceiver);
		}
	}

	private void lastPosition() {
		String p = mDirectory.getAbsolutePath();
		if (mPositions.containsKey(p))
			getListView().setSelection(mPositions.get(p));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		mPositions.put(mDirectory.getAbsolutePath(), getListView().getFirstVisiblePosition());

		if (position < (mParent == null ? 0 : 1)) {
			mDirectory = mParent;
			mHandler.post(mUpdateFiles);
			return;
		}

		position -= (mParent == null ? 0 : 1);

		if (position < mDirs.length) {
			mDirectory = mDirs[position];
			mHandler.post(mUpdateFiles);
			return;
		}

		position -= mDirs.length;

		Uri uri = Uri.fromFile(mFiles[position]);
		MyLog.getLog().i("uri = " + uri.toString());
		Intent intent = new Intent(this, MuPDFActivity.class);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(uri);
		switch (mPurpose) {
		case PickPDF:
			// Start an activity to display the PDF file
			MyLog.getLog().i("PickPDF");
			startActivity(intent);
			break;
		case PickKeyFile:
			// Return the uri to the caller
			MyLog.getLog().i("PickKeyFile");
			setResult(RESULT_OK, intent);
			finish();
			break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mDirectory != null)
			mPositions.put(mDirectory.getAbsolutePath(), getListView().getFirstVisiblePosition());
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
