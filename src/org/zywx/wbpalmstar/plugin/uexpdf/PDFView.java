package org.zywx.wbpalmstar.plugin.uexpdf;

import android.content.Context;

import com.artifex.mupdfdemo.FilePicker;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.MuPDFReaderView;


/**
 * Created by ylt on 2017/4/24.
 */

public class PDFView extends MuPDFReaderView{
    private MuPDFCore mCore;
    private String mFilePath;
    private Context mContext;
    private MuPDFPageAdapter mPDFPageAdapter;
    private PDFViewCallback mCallback;

    public PDFView(Context context,String filePath, PDFViewCallback callback) {
        super(context);
        mContext=context;
        mFilePath=filePath;
        mCallback = callback;
        init();
    }


    private void init (){
        try {
            mCore=new MuPDFCore(mContext,mFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mCore != null){
            mPDFPageAdapter=new MuPDFPageAdapter(mContext, new FilePicker.FilePickerSupport() {
                @Override
                public void performPickFor(FilePicker picker) {

                }
            },mCore);

            setAdapter(mPDFPageAdapter);
            mCallback.onStatus(true);
        }else{
            mCallback.onStatus(false);
        }
    }


    public void close(){
        if (mCore!=null){
            mCore.stopAlerts();
            mCore.onDestroy();
        }
    }

    public interface PDFViewCallback{
        public void onStatus(boolean isSuccess);
    }


}
