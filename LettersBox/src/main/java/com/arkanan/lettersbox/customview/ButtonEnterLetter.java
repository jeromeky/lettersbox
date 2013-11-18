package com.arkanan.lettersbox.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;


/**
 * Custom view for enter letter
 * @author jeromeky
 *
 */
public class ButtonEnterLetter extends Button {

	private int indexOfRandomLetter;

	public ButtonEnterLetter(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ButtonEnterLetter(Context context) {
		super(context);

		setGravity(Gravity.CENTER);
		setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f));
	}

	public int getIndexOfRandomLetter() {
		return indexOfRandomLetter;
	}

	public void setIndexOfRandomLetter(int indexOfRandomLetter) {
		this.indexOfRandomLetter = indexOfRandomLetter;
	}

}
