package org.ebookdroid.ui.settings.fragments;


import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.annotation.TargetApi;

@TargetApi(11)
public class BackupFragment extends BasePreferenceFragment {

    public BackupFragment() {
//        super(R.xml.fragment_backup);
    	super(EUExUtil.getResXmlID("plugin_pdf_fragment_backup"));
    	
    }
}
