package com.arkanan.lettersbox.model;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.LinearLayout;


/**
 * 
 * This class uses to define default style of random letters
 *
 * @author jeromeky
 *
 */
public class RandomLettersBoxStyle extends LettersBoxStyle{

    public RandomLettersBoxStyle(){

        typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD);
        setColor(Color.WHITE);
        sizeUnit = TypedValue.COMPLEX_UNIT_DIP;
        size = 10;
        layoutParams = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        setMargin(10);
        setPadding(10);

    }

}
