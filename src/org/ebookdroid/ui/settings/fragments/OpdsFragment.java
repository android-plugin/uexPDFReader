package org.ebookdroid.ui.settings.fragments;


import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class OpdsFragment extends BasePreferenceFragment {

    public OpdsFragment() {
//        super(R.xml.fragment_opds);
    	super(EUExUtil.getResXmlID("plugin_pdf_fragment_opds"));
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decorateOpdsSettings();
    }

}
