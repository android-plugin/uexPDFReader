package org.ebookdroid.common.settings.definitions;


import org.ebookdroid.common.settings.types.BookRotationType;
import org.ebookdroid.common.settings.types.DocumentViewMode;
import org.ebookdroid.common.settings.types.PageAlign;
import org.ebookdroid.core.curl.PageAnimationType;

import org.emdev.common.settings.base.BooleanPreferenceDefinition;
import org.emdev.common.settings.base.EnumPreferenceDefinition;
import org.emdev.common.settings.base.IntegerPreferenceDefinition;
import org.emdev.common.settings.base.StringPreferenceDefinition;
import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public interface BookPreferences {

    /* =============== Book rendering settings =============== */

//    StringPreferenceDefinition BOOK = new StringPreferenceDefinition(pref_book_id, 0);
//
//    IntegerPreferenceDefinition BOOK_FIRST_PAGE_OFFSET = new IntegerPreferenceDefinition(pref_book_firstpageoffset_id,
//            pref_book_firstpageoffset_defvalue, pref_book_firstpageoffset_minvalue, pref_book_firstpageoffset_maxvalue);
//
//    BooleanPreferenceDefinition BOOK_NIGHT_MODE = new BooleanPreferenceDefinition(pref_book_nightmode_id, pref_nightmode_defvalue);
//
//    BooleanPreferenceDefinition BOOK_NIGHT_MODE_POS_IMAGES = new BooleanPreferenceDefinition(pref_book_posimages_in_nightmode_id, pref_posimages_in_nightmode_defvalue);
//
//    IntegerPreferenceDefinition BOOK_CONTRAST = new IntegerPreferenceDefinition(pref_book_contrast_id,
//            pref_contrast_defvalue, pref_contrast_minvalue, pref_contrast_maxvalue);
//
//    IntegerPreferenceDefinition BOOK_GAMMA = new IntegerPreferenceDefinition(pref_book_gamma_id,
//            pref_gamma_defvalue, pref_gamma_minvalue, pref_gamma_maxvalue);
//
//    IntegerPreferenceDefinition BOOK_EXPOSURE = new IntegerPreferenceDefinition(pref_book_exposure_id,
//            pref_exposure_defvalue, pref_exposure_minvalue, pref_exposure_maxvalue);
//
//    BooleanPreferenceDefinition BOOK_AUTO_LEVELS = new BooleanPreferenceDefinition(pref_book_autolevels_id, pref_autolevels_defvalue);
//
//    BooleanPreferenceDefinition BOOK_SPLIT_PAGES = new BooleanPreferenceDefinition(pref_book_splitpages_id,
//            pref_splitpages_defvalue);
//
//    BooleanPreferenceDefinition BOOK_SPLIT_RTL = new BooleanPreferenceDefinition(pref_book_splitpages_rtl_id,
//            pref_splitpages_rtl_defvalue);
//
//    BooleanPreferenceDefinition BOOK_CROP_PAGES = new BooleanPreferenceDefinition(pref_book_croppages_id,
//            pref_croppages_defvalue);
//
//    EnumPreferenceDefinition<BookRotationType> BOOK_ROTATION = new EnumPreferenceDefinition<BookRotationType>(
//            BookRotationType.class, pref_book_rotation_id, pref_rotation_unspecified);
//
//    EnumPreferenceDefinition<DocumentViewMode> BOOK_VIEW_MODE = new EnumPreferenceDefinition<DocumentViewMode>(
//            DocumentViewMode.class, pref_book_viewmode_id, pref_viewmode_vertical_scroll);
//
//    EnumPreferenceDefinition<PageAlign> BOOK_PAGE_ALIGN = new EnumPreferenceDefinition<PageAlign>(PageAlign.class,
//            pref_book_align_id, pref_align_by_width);
//
//    EnumPreferenceDefinition<PageAnimationType> BOOK_ANIMATION_TYPE = new EnumPreferenceDefinition<PageAnimationType>(
//            PageAnimationType.class, pref_book_animation_type_id, pref_animation_type_none);
	
	StringPreferenceDefinition BOOK = new StringPreferenceDefinition(EUExUtil.getResStringID("plugin_pdf_pref_book_id"), 0);

    IntegerPreferenceDefinition BOOK_FIRST_PAGE_OFFSET = new IntegerPreferenceDefinition(
            EUExUtil.getResStringID("plugin_pdf_pref_book_firstpageoffset_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_book_firstpageoffset_defvalue"),
            EUExUtil.getResStringID("plugin_pdf_pref_book_firstpageoffset_minvalue"),
            EUExUtil.getResStringID("plugin_pdf_pref_book_firstpageoffset_maxvalue"));

    BooleanPreferenceDefinition BOOK_NIGHT_MODE = new BooleanPreferenceDefinition(
            EUExUtil.getResStringID("plugin_pdf_pref_book_nightmode_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_nightmode_defvalue"));

    BooleanPreferenceDefinition BOOK_NIGHT_MODE_POS_IMAGES = new BooleanPreferenceDefinition(
    		EUExUtil.getResStringID("plugin_pdf_pref_book_posimages_in_nightmode_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_posimages_in_nightmode_defvalue"));

    IntegerPreferenceDefinition BOOK_CONTRAST = new IntegerPreferenceDefinition(
            EUExUtil.getResStringID("plugin_pdf_pref_book_contrast_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_contrast_defvalue"),
            EUExUtil.getResStringID("plugin_pdf_pref_contrast_minvalue"),
            EUExUtil.getResStringID("plugin_pdf_pref_contrast_maxvalue"));

    IntegerPreferenceDefinition BOOK_GAMMA = new IntegerPreferenceDefinition(
            EUExUtil.getResStringID("plugin_pdf_pref_book_gamma_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_gamma_defvalue"),
            EUExUtil.getResStringID("plugin_pdf_pref_gamma_minvalue"),
            EUExUtil.getResStringID("plugin_pdf_pref_gamma_maxvalue")
    		);

    IntegerPreferenceDefinition BOOK_EXPOSURE = new IntegerPreferenceDefinition(
            EUExUtil.getResStringID("plugin_pdf_pref_book_exposure_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_exposure_defvalue"),
            EUExUtil.getResStringID("plugin_pdf_pref_exposure_minvalue"),
            EUExUtil.getResStringID("plugin_pdf_pref_exposure_maxvalue")
    		);

    BooleanPreferenceDefinition BOOK_AUTO_LEVELS = new BooleanPreferenceDefinition(
    		EUExUtil.getResStringID("plugin_pdf_pref_book_autolevels_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_autolevels_defvalue"));

    BooleanPreferenceDefinition BOOK_SPLIT_PAGES = new BooleanPreferenceDefinition(
            EUExUtil.getResStringID("plugin_pdf_pref_book_splitpages_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_splitpages_defvalue"));

    BooleanPreferenceDefinition BOOK_SPLIT_RTL = new BooleanPreferenceDefinition(
            EUExUtil.getResStringID("plugin_pdf_pref_book_splitpages_rtl_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_splitpages_rtl_defvalue"));

    BooleanPreferenceDefinition BOOK_CROP_PAGES = new BooleanPreferenceDefinition(
            EUExUtil.getResStringID("plugin_pdf_pref_book_croppages_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_croppages_defvalue"));

    EnumPreferenceDefinition<BookRotationType> BOOK_ROTATION = new EnumPreferenceDefinition<BookRotationType>(
            BookRotationType.class, 
            EUExUtil.getResStringID("plugin_pdf_pref_book_rotation_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_rotation_unspecified"));

    EnumPreferenceDefinition<DocumentViewMode> BOOK_VIEW_MODE = new EnumPreferenceDefinition<DocumentViewMode>(
            DocumentViewMode.class, 
            EUExUtil.getResStringID("plugin_pdf_pref_book_viewmode_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_viewmode_vertical_scroll"));

    EnumPreferenceDefinition<PageAlign> BOOK_PAGE_ALIGN = new EnumPreferenceDefinition<PageAlign>(PageAlign.class,
            EUExUtil.getResStringID("plugin_pdf_pref_book_align_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_align_by_width"));

    EnumPreferenceDefinition<PageAnimationType> BOOK_ANIMATION_TYPE = new EnumPreferenceDefinition<PageAnimationType>(
            PageAnimationType.class, 
            EUExUtil.getResStringID("plugin_pdf_pref_book_animation_type_id"),
            EUExUtil.getResStringID("plugin_pdf_pref_animation_type_none"));

}
