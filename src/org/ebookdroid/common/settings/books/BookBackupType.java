package org.ebookdroid.common.settings.books;

import org.emdev.BaseDroidApp;
import org.emdev.utils.enums.ResourceConstant;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public enum BookBackupType implements ResourceConstant {

    /**
     *
     */
//    NONE(R.string.pref_bookbackuptype_none),
	NONE(EUExUtil.getResStringID("plugin_pdf_pref_bookbackuptype_none")),
    /**
     *
     */
//    RECENT(R.string.pref_bookbackuptype_recent),
	RECENT(EUExUtil.getResStringID("plugin_pdf_pref_bookbackuptype_recent")),
    /**
     *
     */
//    ALL(R.string.pref_bookbackuptype_all);
	ALL(EUExUtil.getResStringID("plugin_pdf_pref_bookbackuptype_all"));

    public final String resValue;

    private BookBackupType(final int resId) {
        this.resValue = BaseDroidApp.context.getString(resId);
    }

    @Override
    public String getResValue() {
        return resValue;
    }
}
