package org.ebookdroid.ui.settings.fragments;


import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class BrowserFragment extends BasePreferenceFragment {

    public BrowserFragment() {
//        super(R.xml.fragment_browser);
    	 super(EUExUtil.getResXmlID("plugin_pdf_fragment_browser"));
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decorateBrowserSettings();
    }

}
