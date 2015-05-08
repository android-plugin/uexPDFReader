package org.ebookdroid.ui.settings;

import java.util.List;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;


import android.annotation.TargetApi;
import android.content.res.Configuration;

@TargetApi(11)
public class FragmentedSettingsActivity extends SettingsActivity {

    @Override
    protected void onCreate() {
    }

    @Override
    public void onBuildHeaders(final List<Header> target) {
//        loadHeadersFromResource(R.xml.preferences_headers, target);
    	  loadHeadersFromResource( EUExUtil.getResXmlID("plugin_pdf_preferences_headers"), target);
       
    }

    @Override
    public boolean onIsMultiPane() {
        final Configuration c = this.getResources().getConfiguration();
        if (0 != (Configuration.SCREENLAYOUT_SIZE_XLARGE & c.screenLayout)) {
            return c.orientation == Configuration.ORIENTATION_LANDSCAPE;
        }
        return false;
    }
}
