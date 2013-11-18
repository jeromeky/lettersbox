package com.arkanan.lettersbox.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.LinearLayout;

import com.arkanan.lettersbox.R;


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
public class LettersBoxArg {
	
    private LettersBoxStyle enterLettersStyle = new EnterLettersBoxStyle();
    private LettersBoxStyle randomLettersStyle = new RandomLettersBoxStyle();

    /**
     * set it to use custom drawable for enter letters
     */
    private int enterLettersDrawableId = R.drawable.enter_letter;

    /**
     * set it to use custom drawable for random letters
     */
    private int randomLettersDrawableId =  R.drawable.random_letter;

    /**
     * set it to use custom drawable for tilt
     */
    private int tiltDrawableId = R.drawable.tilt;
	
	/**
	 * Max random letters show (by default : 16)
	 */
	private int maxLetters = 16;
	
	/**
	 * Max line of letters (by defaut : 2)
	 */
	private int maxLine = 2;

	/**
	 * current activity need for inflate view with linear layout of letters box
	 */
	private Activity currentActivity;
	
	
	/**
	 * A linear layout where random letters will be
	 */
	private LinearLayout randomLetters;

	/**
	 * A linear layout where enter letters will be
	 */
	private LinearLayout enterLetters;
	
	/**
	 * A button 
	 */
	private Button hintButton;
	
	/**
	 * A button
	 */
	private Button answerButton;
	
	/**
	 * List of words 
	 */
	private LinkedList<String> words = new LinkedList<String>();

	public LettersBoxArg(Activity currentActivity, LinearLayout enterLetters, LinearLayout randomLetters){
		this.currentActivity = currentActivity;
		this.enterLetters = enterLetters;
		this.randomLetters = randomLetters;
	}


    public int getEnterLettersDrawableId() {
        return enterLettersDrawableId;
    }

    public void setEnterLettersDrawableId(int enterLettersDrawableId) {
        this.enterLettersDrawableId = enterLettersDrawableId;
    }

    public Drawable getEnterLettersDrawable(){
        return currentActivity.getResources().getDrawable(enterLettersDrawableId);
    }

    public Drawable getRandomLettersDrawable(){
        return currentActivity.getResources().getDrawable(randomLettersDrawableId);
    }

    public Drawable getTiltDrawable(){
        return currentActivity.getResources().getDrawable(tiltDrawableId);
    }

    public LinearLayout getEnterLetters() {
		return enterLetters;
	}

	public LettersBoxArg setEnterLetters(LinearLayout enterLetters) {
		this.enterLetters = enterLetters;
		return this;
	}

	public LinearLayout getRandomLetters() {
		return randomLetters;
	}

	public LettersBoxArg setRandomLetters(LinearLayout randomLetters) {
		this.randomLetters = randomLetters;
		return this;
	}

	public Button getHintButton() {
		return hintButton;
	}

	public LettersBoxArg setHintButton(Button hint) {
		this.hintButton = hint;
		return this;
	}

	public Button getAnswerButton() {
		return answerButton;
	}

	public LettersBoxArg setAnswerButton(Button answer) {
		this.answerButton = answer;
		return this;
	}

	public Activity getCurrentActivity() {
		return currentActivity;
	}

	public LettersBoxArg setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
		return this;
	}


	public int getMaxLetters() {
		return maxLetters;
	}

	public LettersBoxArg setMaxLetters(int maxLetters) {
		this.maxLetters = maxLetters;
		return this;
	}

	public int getMaxLine() {
		return maxLine;
	}

	public LettersBoxArg setMaxLine(int maxLign) {
		this.maxLine = maxLign;
		return this;
	}


    public LettersBoxStyle getEnterLettersStyle() {
        return enterLettersStyle;
    }

    public void setEnterLettersStyle(LettersBoxStyle enterLettersStyle) {
        this.enterLettersStyle = enterLettersStyle;
    }

    public LettersBoxStyle getRandomLettersStyle() {
        return randomLettersStyle;
    }

    public void setRandomLettersStyle(LettersBoxStyle randomLettersStyle) {
        this.randomLettersStyle = randomLettersStyle;
    }

    public int getTiltDrawableId() {
        return tiltDrawableId;
    }

    public void setTiltDrawableId(int tiltDrawableId) {
        this.tiltDrawableId = tiltDrawableId;
    }
	
	public int getMaxLettersByLign(){
		return maxLetters / maxLine;
	}

	public LinkedList<String> getWords() {
		return words;
	}

	public LettersBoxArg setWords(LinkedList<String> words) {
		if(this.words != null)
			this.words.clear();
		this.words = words;
		return this;
	}
	
	public LettersBoxArg setWords(List<String> words){
		if(this.words != null)
			this.words.clear();
		this.words.addAll(words);
		return this;
	}
	
	public LettersBoxArg setWords(String[] words){
		if(this.words != null)
			this.words.clear();
		this.words.addAll(Arrays.asList(words));
		return this;
	}
	
	public LettersBoxArg addWord(String word){
		this.words.add(word);
		return this;
		
	}


}
