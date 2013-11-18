package com.arkanan.lettersbox.model;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;


/**
 * 
 * This class uses to define default style of enter letters.
 *
 * @author jeromeky
 *
 */
public class EnterLettersBoxStyle extends LettersBoxStyle{

    public EnterLettersBoxStyle(){

        typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD);
        setColor(Color.BLACK);
        sizeUnit = TypedValue.COMPLEX_UNIT_DIP;
        size = 20;
        layoutParams = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f);
        setMargin(5);
        setPadding(5);

    }

}
