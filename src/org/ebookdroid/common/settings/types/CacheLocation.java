package org.ebookdroid.common.settings.types;

import org.ebookdroid.EBookDroidApp;

import org.emdev.utils.enums.ResourceConstant;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public enum CacheLocation implements ResourceConstant {

//    /**
//     *
//     */
//    System(R.string.pref_cachelocation_system),
//    /**
//     *
//     */
//    Custom(R.string.pref_cachelocation_custom);
	
	 /**
    *
    */
   System(EUExUtil.getResStringID("plugin_pdf_pref_cachelocation_system")),
   /**
    *
    */
   Custom(EUExUtil.getResStringID("plugin_pdf_pref_cachelocation_custom"));

    private final String resValue;

    private CacheLocation(final int resId) {
    	
        this.resValue = EBookDroidApp.context.getString(resId);
    }

    public String getResValue() {
        return resValue;
    }

}
