package com.scxh.android1502.util;

import android.content.Context;
import android.util.TypedValue;

public class ResourceUtils {
	public static final int DimensionPixelOffset = 1;
	public static final int DimensionPixelSize = 2;
	public static final int Dimension = 3;
	
	private static TypedValue mTmpValue = new TypedValue();

	public static int getXmlDef(Context context, int id) {
		synchronized (mTmpValue) {
			TypedValue value = mTmpValue;
			if (value == null) {
                mTmpValue = value = new TypedValue();
            }
			context.getResources().getValue(id, value, true);
			return (int) TypedValue.complexToFloat(value.data);
		}
	}

	public static int getXmlDef(Context context, int id,int type) {
		synchronized (mTmpValue) {
			TypedValue value = mTmpValue;
			if (value == null) {
                mTmpValue = value = new TypedValue();
            }
			context.getResources().getValue(id, value, true);
			switch(type){
			case DimensionPixelOffset:
				return (int) TypedValue.complexToDimensionPixelOffset(value.data, context.getResources().getDisplayMetrics());
			case DimensionPixelSize:
				return (int) TypedValue.complexToDimensionPixelSize(value.data, context.getResources().getDisplayMetrics());
			case Dimension:
				return (int) TypedValue.complexToDimension(value.data, context.getResources().getDisplayMetrics());
			default:
				return 0;
			}
			
			
		}
	}
	
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param dipValue
	 * @param scale
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
}
