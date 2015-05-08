package org.ebookdroid;

import org.ebookdroid.common.bitmaps.BitmapManager;
import org.ebookdroid.common.bitmaps.ByteBufferManager;
import org.ebookdroid.common.cache.CacheManager;
import org.ebookdroid.common.settings.AppSettings;
import org.ebookdroid.common.settings.BackupSettings;
import org.ebookdroid.common.settings.LibSettings;
import org.ebookdroid.common.settings.LibSettings.Diff;
import org.ebookdroid.common.settings.SettingsManager;
import org.ebookdroid.common.settings.listeners.IAppSettingsChangeListener;
import org.ebookdroid.common.settings.listeners.IBackupSettingsChangeListener;
import org.ebookdroid.common.settings.listeners.ILibSettingsChangeListener;
//import org.ebookdroid.ui.library.RecentActivityController;
import org.emdev.BaseDroidApp;
import org.emdev.common.backup.BackupManager;
import org.emdev.common.filesystem.MediaManager;
import org.emdev.common.fonts.FontManager;
import org.emdev.ui.actions.ActionController;
import org.emdev.ui.actions.ActionDialogBuilder;
import org.emdev.ui.gl.GLConfiguration;
import org.emdev.utils.concurrent.Flag;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;
import org.zywx.wbpalmstar.plugin.uexpdf.PdfUtils.ResUtils;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

public class EBookDroidApp extends BaseDroidApp implements IAppSettingsChangeListener, IBackupSettingsChangeListener,
        ILibSettingsChangeListener {

    public static final Flag initialized = new Flag();

    public static EBookDroidVersion version;

    private static EBookDroidApp instance = new EBookDroidApp();
    private static Context mContext;
    public static EBookDroidApp newInstance(Context context){
        mContext = context;
        BaseDroidApp.context = context;
        return instance;
    } 
    
    /**
     * {@inheritDoc}
     *
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        version = EBookDroidVersion.get(APP_VERSION_CODE);
        SettingsManager.init(mContext);
        CacheManager.init(mContext);
        MediaManager.init(mContext);

        initFonts();

        preallocateHeap(AppSettings.current().heapPreallocate);

        SettingsManager.addListener(this);
        onAppSettingsChanged(null, AppSettings.current(), null);
        onBackupSettingsChanged(null, BackupSettings.current(), null);

        GLConfiguration.stencilRequired = !IS_EMULATOR;

        initialized.set();
    }

    public static void initFonts() {
        FontManager.init(APP_STORAGE);
    }

  
    public void onTerminate() {
        SettingsManager.onTerminate();
        MediaManager.onTerminate(mContext);
    }

    /**
     * {@inheritDoc}
     *
     * @see android.app.Application#onLowMemory()
     */
    
    public void onLowMemory() {
        BitmapManager.clear("on Low Memory: ");
        ByteBufferManager.clear("on Low Memory: ");
    }

   
    public void onAppSettingsChanged(final AppSettings oldSettings, final AppSettings newSettings,
            final AppSettings.Diff diff) {

        ByteBufferManager.setPartSize(1 << newSettings.bitmapSize);

        setAppLocale(newSettings.lang);
    }

  
    public void onBackupSettingsChanged(final BackupSettings oldSettings, final BackupSettings newSettings,
            final BackupSettings.Diff diff) {
        BackupManager.setMaxNumberOfAutoBackups(newSettings.maxNumberOfAutoBackups);
    }


    public void onLibSettingsChanged(final LibSettings oldSettings, final LibSettings newSettings, final Diff diff) {
        if (diff.isCacheLocationChanged()) {
            CacheManager.setCacheLocation(newSettings.cacheLocation, !diff.isFirstTime());
        }
    }

    public static void checkInstalledFonts(final Context context) {
        if (!FontManager.external.hasInstalled()) {
            if (!SettingsManager.isInitialFlagsSet(SettingsManager.INITIAL_FONTS)) {
                SettingsManager.setInitialFlags(SettingsManager.INITIAL_FONTS);

                final ActionDialogBuilder b = new ActionDialogBuilder(context, new ActionController<Context>(context));
                final WebView view = new WebView(context);

//                final String text = context.getResources().getString(R.string.font_reminder);
//                final String content = "<html><body>" + text + "</body></html>";
//
//                view.loadDataWithBaseURL("file:///fake/not_used", content, "text/html", "UTF-8", "");
//
//                b.setTitle(R.string.font_reminder_title);
//                b.setView(view);
//                b.setPositiveButton(android.R.string.ok, R.id.actions_no_action);
//                b.show();
            }
        }
    }

    public static void onActivityClose(final boolean finishing) {
//        if (finishing && !SettingsManager.hasOpenedBooks() && !RecentActivityController.working.get()) {
//            if (instance != null) {
//                instance.onTerminate();
//            }
//            Log.i(APP_NAME, "Application finished");
//            //System.exit(0);
//        }
    	
    	if (finishing && !SettingsManager.hasOpenedBooks()) {
            if (instance != null) {
                instance.onTerminate();
            }
            Log.i(APP_NAME, "Application finished");
            //System.exit(0);
        }
    }

    /**
     * Preallocate heap.
     *
     * @param size
     *            the size in megabytes
     * @return the object
     */
    private static Object preallocateHeap(final int size) {
        if (size <= 0) {
            Log.i(APP_NAME, "No heap preallocation");
            return null;
        }
        int i = size;
        Log.i(APP_NAME, "Trying to preallocate " + size + "Mb");
        while (i > 0) {
            try {
                byte[] tmp = new byte[i * 1024 * 1024];
                tmp[size - 1] = (byte) size;
                Log.i(APP_NAME, "Preallocated " + i + "Mb");
                tmp = null;
                return tmp;
            } catch (final OutOfMemoryError e) {
                i--;
            } catch (final IllegalArgumentException e) {
                i--;
            }
        }
        Log.i(APP_NAME, "Heap preallocation failed");
        return null;
    }

}
