package org.ebookdroid.ui.settings.fragments;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class PerformanceFragment extends BasePreferenceFragment {

    public PerformanceFragment() {
//        super(R.xml.fragment_performance);
        super(EUExUtil.getResXmlID("plugin_pdf_fragment_performance"));
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decoratePerformanceSettings();
    }
}
