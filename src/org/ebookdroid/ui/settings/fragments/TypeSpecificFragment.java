package org.ebookdroid.ui.settings.fragments;


import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class TypeSpecificFragment extends BasePreferenceFragment {

    public TypeSpecificFragment() {
//        super(R.xml.fragment_typespec);
    	super(EUExUtil.getResXmlID("plugin_pdf_fragment_typespec"));
    }

    @Override
    public void decorate() {
        super.decorate();
        decorator.decorateTypeSpecificSettings();
    }
}
