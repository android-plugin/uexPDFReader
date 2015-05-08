package org.ebookdroid.ui.settings.fragments;


import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class UIFragment extends BasePreferenceFragment {
    public UIFragment() {
//        super(R.xml.fragment_ui);
    	super(EUExUtil.getResXmlID("plugin_pdf_fragment_ui"));
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decorateUISettings();
    }
}
