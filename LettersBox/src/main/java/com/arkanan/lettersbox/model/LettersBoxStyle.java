package com.arkanan.lettersbox.model;

import android.graphics.Typeface;

import android.widget.LinearLayout;


/**
 * 
 * This class uses to define the settings of LetterBox framework.
 *
 * You can custom your lettersbox with different settings like :
 * - Max random letters (default : 16)
 * - Max random line of letters (default : 2)
 * - Custom font / background for random letters
 * - Custom font / background for enter letters
 *
 * @author jeromeky
 *
 */
public abstract class LettersBoxStyle {

    /**
     * set it to use custom font for enter letters
     */
    protected Typeface typeface;

    protected int color;

    protected int sizeUnit;

    protected float size;

    protected LinearLayout.LayoutParams layoutParams;

    protected int marginLeft;

    protected int marginRight;

    protected int marginTop;

    protected int marginBottom;

    protected int paddingLeft;

    protected int paddingRight;

    protected int paddingTop;

    protected int paddingBottom;

    public void setMargin(int marginLeft, int marginTop, int marginRight, int marginBottom){
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
    }

    public void setMargin(int margin){
        setMargin(margin, margin, margin, margin);
    }

    public void setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom){
        this.paddingLeft = paddingLeft;
        this.paddingTop = paddingTop;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;
    }

    public void setPadding(int padding){
        setPadding(padding, padding, padding, padding);
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(int sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public LinearLayout.LayoutParams getLayoutParams() {
        return layoutParams;
    }

    public void setLayoutParams(LinearLayout.LayoutParams layoutParams) {
        this.layoutParams = layoutParams;
    }
}
