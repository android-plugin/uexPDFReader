package org.ebookdroid.ui.settings.fragments;


import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class ScrollFragment extends BasePreferenceFragment {

    public ScrollFragment() {
//        super(R.xml.fragment_scroll);
        super(EUExUtil.getResXmlID("fragment_scroll"));
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decorateScrollSettings();
    }

}
