package com.artifex.mupdfdemo;

import java.util.LinkedList;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChoosePDFAdapter extends BaseAdapter {
	private final LinkedList<ChoosePDFItem> mItems;
	private final LayoutInflater mInflater;

	public ChoosePDFAdapter(LayoutInflater inflater) {
		mInflater = inflater;
		mItems = new LinkedList<ChoosePDFItem>();
	}

	public void clear() {
		mItems.clear();
	}

	public void add(ChoosePDFItem item) {
		mItems.add(item);
		notifyDataSetChanged();
	}

	public int getCount() {
		return mItems.size();
	}

	public Object getItem(int i) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	private int iconForType(ChoosePDFItem.Type type) {
		switch (type) {
		case PARENT:
			return EUExUtil.getResDrawableID("plugin_uexpdfreader_ic_arrow_up");
		case DIR:
			return EUExUtil.getResDrawableID("plugin_uexpdfreader_ic_dir");
		case DOC:
			return EUExUtil.getResDrawableID("plugin_uexpdfreader_ic_doc");
		default:
			return 0;
		}
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
			v = mInflater.inflate(EUExUtil.getResLayoutID("plugin_uexpdfreader_picker_entry"), null);
		} else {
			v = convertView;
		}
		ChoosePDFItem item = mItems.get(position);
		((TextView) v.findViewById(EUExUtil.getResIdID("name"))).setText(item.name);
		((ImageView) v.findViewById(EUExUtil.getResIdID("icon"))).setImageResource(iconForType(item.type));
		((ImageView) v.findViewById(EUExUtil.getResIdID("icon"))).setColorFilter(Color.argb(255, 0, 0, 0));
		return v;
	}

}
