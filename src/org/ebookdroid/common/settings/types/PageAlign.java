package org.ebookdroid.common.settings.types;

import org.ebookdroid.EBookDroidApp;

import org.emdev.utils.enums.ResourceConstant;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

/**
 * The Enum PageAlign.
 */
public enum PageAlign implements ResourceConstant {
//    /** BY WIDTH. */
//    WIDTH(R.string.pref_align_by_width),
//    /** BY HEIGHT. */
//    HEIGHT(R.string.pref_align_by_height),
//    /** AUTO. */
//    AUTO(R.string.pref_align_auto);
	
	  /** BY WIDTH. */
    WIDTH(EUExUtil.getResStringID("plugin_pdf_pref_align_by_width")),
    /** BY HEIGHT. */
    HEIGHT(EUExUtil.getResStringID("plugin_pdf_pref_align_by_height")),
    /** AUTO. */
    AUTO(EUExUtil.getResStringID("plugin_pdf_pref_align_auto"));
    
     

    /** The resource value. */
    private final String resValue;

    /**
     * Instantiates a new page align object.
     *
     * @param resValue
     *            the res value
     */
    private PageAlign(final int resId) {
        this.resValue = EBookDroidApp.context.getString(resId);
    }

    /**
     * Gets the resource value.
     *
     * @return the resource value
     */
    public String getResValue() {
        return resValue;
    }
}
