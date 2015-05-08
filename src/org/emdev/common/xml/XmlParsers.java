package org.emdev.common.xml;

import org.emdev.BaseDroidApp;
import org.emdev.utils.enums.ResourceConstant;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public enum XmlParsers implements ResourceConstant {

    /**
     *
     */
//    VTDEx(R.string.pref_fb2_xmlparser_vtd_ex),
	 VTDEx(EUExUtil.getResStringID("plugin_pdf_pref_fb2_xmlparser_vtd_ex")),
    /**
     *
     */
//    Duckbill(R.string.pref_fb2_xmlparser_duckbill);
	 Duckbill(EUExUtil.getResStringID("plugin_pdf_pref_fb2_xmlparser_duckbill"));
    private final String resValue;

    private XmlParsers(final int resId) {
        this.resValue = BaseDroidApp.context.getString(resId);
    }

    @Override
    public String getResValue() {
        return resValue;
    }

}
