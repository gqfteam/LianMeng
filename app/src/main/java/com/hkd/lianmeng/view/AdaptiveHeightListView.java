package com.hkd.lianmeng.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class AdaptiveHeightListView extends ListView{
  
	public AdaptiveHeightListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public AdaptiveHeightListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public AdaptiveHeightListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//		int height = 0;
//		Log.i("gqf", "onMeasure"+getChildCount());
//		for (int i = 0; i < getChildCount(); i++) {
//			View child = getChildAt(i);
//			child.measure(widthMeasureSpec,
//					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//			int h = child.getMeasuredHeight();
//			height=height+h;
//			Log.i("gqf", "onMeasure"+height);
//		}
//
//		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
//				MeasureSpec.EXACTLY);
//
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
}
