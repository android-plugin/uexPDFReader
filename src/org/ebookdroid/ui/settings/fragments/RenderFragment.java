package org.ebookdroid.ui.settings.fragments;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class RenderFragment extends BasePreferenceFragment {

    public RenderFragment() {
//        super(R.xml.fragment_render);
        super(EUExUtil.getResXmlID("plugin_pdf_fragment_performance"));
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decorateRenderSettings();
    }
}
