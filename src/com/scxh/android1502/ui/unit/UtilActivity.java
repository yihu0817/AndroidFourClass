package com.scxh.android1502.ui.unit;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;
import com.scxh.android1502.util.ResourceUtils;

/**
 * API reference里的解释：
 * getDimension()是基于当前DisplayMetrics进行转换，获取指定资源id对应的尺寸。文档里并没说这里返回的就是像素
 * ，要注意这个函数的返回值是float，像素肯定是int。
 * getDimensionPixelSize()与getDimension()功能类似，不同的是将结果转换为int，并且小数部分四舍五入。
 * getDimensionPixelOffset()与getDimension()功能类似，不同的是将结果转换为int，并且偏移转换（offset
 * conversion，函数命名中的offset是这个意思）是直接截断小数位，即取整（其实就是把float强制转化为int，注意不是四舍五入哦）。
 * 
 * 
 * 由此可见，这三个函数返回的都是绝对尺寸，而不是相对尺寸（dp\sp等）。
 * 如果getDimension()返回结果是20.5f，
 * 那么getDimensionPixelSize()返回结果就是21，
 *    getDimensionPixelOffset()返回结果就是20。
 * 
 * 例如: R.dimen.size_in 值为 25.6
 * float size1 = getResources().getDimension(R.dimen.size_in)));
 * 基于当前DisplayMetrics进行转换，获取指定资源id对应的尺寸size1=25.6 
 * 
 * int size2 = getResources().getDimensionPixelOffset(R.dimen.size_in)));
 * 采用直接截断小数位，即取整，size2的值是25 
 * 
 * int size3 = getResources().getDimensionPixelSize(R.dimen.size_in)));
 * 采用四舍五入的方式返回对应的整数，size3的值是26 
 */
public class UtilActivity extends Activity {
	private TextView mFontTxt, mOneFontTxt, mTwoFontTxt, mThreeFontTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_util_layout);

		mFontTxt = (TextView) findViewById(R.id.font_txt);
		mOneFontTxt = (TextView) findViewById(R.id.font_one_txt);
		mTwoFontTxt = (TextView) findViewById(R.id.font_two_txt);
		mThreeFontTxt = (TextView) findViewById(R.id.font_three_txt);

		mFontTxt.setTextSize(24);
		
		mOneFontTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
		
//		mOneFontTxt.setWidth(TypedValue.applyDimension(unit, size, r.getDisplayMetrics()));
		
		mTwoFontTxt.setTextSize(getResources().getDimension(R.dimen.dimension_textview_size));

		mThreeFontTxt.setTextSize(ResourceUtils.getXmlDef(this,R.dimen.dimension_textview_size));
		

		Logs.v("24.6sp getDimension :"+ getResources().getDimension(R.dimen.dimension_textview_size));
		Logs.d("24.6sp getDimensionPixelOffset :"+ getResources().getDimensionPixelOffset(R.dimen.dimension_textview_size));
		Logs.i("24.6sp getDimensionPixelSize :"+ getResources().getDimensionPixelSize(R.dimen.dimension_textview_size));
		
		Logs.i("24.6sp ResourceUtils.getXmlDef Dimension :"+ ResourceUtils.getXmlDef(this,R.dimen.dimension_textview_size,ResourceUtils.Dimension));
		Logs.i("24.6sp ResourceUtils.getXmlDef DimensionPixelOffset :"+ ResourceUtils.getXmlDef(this,R.dimen.dimension_textview_size,ResourceUtils.DimensionPixelOffset));
		Logs.i("24.6sp ResourceUtils.getXmlDef DimensionPixelSize :"+ ResourceUtils.getXmlDef(this,R.dimen.dimension_textview_size,ResourceUtils.DimensionPixelSize));

		Logs.i("24.6sp ResourceUtils.getXmlDef :"+ ResourceUtils.getXmlDef(this,R.dimen.dimension_textview_size));

		
		//TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
	}
}
