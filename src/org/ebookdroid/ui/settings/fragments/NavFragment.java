package org.ebookdroid.ui.settings.fragments;


import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class NavFragment extends BasePreferenceFragment {
    public NavFragment() {
//        super(R.xml.fragment_navigation);
    	super(EUExUtil.getResXmlID("plugin_pdf_fragment_navigation"));
        
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decorateUISettings();
    }
}
