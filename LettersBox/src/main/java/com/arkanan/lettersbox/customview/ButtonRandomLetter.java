package com.arkanan.lettersbox.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;


/**
 * Custom view for random letter
 * @author jeromeky
 *
 */
public class ButtonRandomLetter extends Button {

	private boolean correctLetter;

	public ButtonRandomLetter(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ButtonRandomLetter(Context context) {
		super(context);

		setGravity(Gravity.CENTER);
		setPadding(0, 0, 0, 0);

	}

	public boolean isCorrectLetter() {
		return correctLetter;
	}

	public void setCorrectLetter(boolean correctLetter) {
		this.correctLetter = correctLetter;
	}

}
