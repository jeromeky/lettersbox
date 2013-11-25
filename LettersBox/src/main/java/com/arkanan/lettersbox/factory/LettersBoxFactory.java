package com.arkanan.lettersbox.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.arkanan.lettersbox.customview.ButtonEnterLetter;
import com.arkanan.lettersbox.customview.ButtonRandomLetter;
import com.arkanan.lettersbox.model.CustomCharacter;
import com.arkanan.lettersbox.model.LettersBoxArg;
import com.arkanan.lettersbox.model.LettersBoxStyle;
import com.arkanan.lettersbox.util.Alphabet;
import com.arkanan.lettersbox.R;

/**
 * Factory use to create all the stuff (Random letter / Enter Letter / Answer)
 *
 * All you have to do is give argument of type LettersBoxArg, to call the method
 * construct() and to override onFullEnterLetters & onEmptyListWords methods.
 *
 * @author jeromeky
 * 
 */
public abstract class LettersBoxFactory {

    /**
     * arg used to create letters box
     */
	private LettersBoxArg  lettersBoxArg;

    /**
     * represents list of random letters
     * randomletters is a list of shuffle letters which contains letters of the answer
     * and random letters
     */
	private List<ButtonRandomLetter> buttonRandomLetters;

    /**
     * represents list of enter letters
     * enterletters is a list of letter typed of randomletters by a user
     */
	private List<ButtonEnterLetter> buttonEnterLetters;

    /**
     * used to know if enterletters list is full
     */
	private boolean fullEnterLetters;

    /**
     * represents list of custom character
     */
	private List<CustomCharacter> currentShuffleLetters;

    /**
     * The current word that user has to guess
     */
	private String currentWord;

	/**
	 * Constructor
	 *
	 * @param lettersBoxArg Factory need arguments to create letters box
	 */
	public LettersBoxFactory(LettersBoxArg lettersBoxArg) {
		this.lettersBoxArg = lettersBoxArg;
        buttonEnterLetters = new ArrayList<ButtonEnterLetter>(lettersBoxArg.getMaxLetters());
        buttonRandomLetters = new ArrayList<ButtonRandomLetter>(lettersBoxArg.getMaxLetters());
		nextWord();
		currentShuffleLetters = shuffleLetters(currentWord);
	}

    /**
     * This method is private because you have to use constructor with arguments
     */
    private LettersBoxFactory(){

    }

    /**
     * This method will construct all the display for letters box like
     * Random / Enter Letters, Hint / Answer button
     *
     */
	public void construct() {
		createRandomLetters();
		createEnterLetters();

		if(lettersBoxArg.getHintButton() != null){
			
			lettersBoxArg.getHintButton().setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View button) {
	
					fullEnterLetters = false;
	
					for (ButtonEnterLetter loopButton : buttonEnterLetters) {
						loopButton.setText("");
						loopButton.setOnTouchListener(null);
						loopButton.setEnabled(false);
					}
	
					for (ButtonRandomLetter loopButton : buttonRandomLetters) {
						loopButton.setVisibility(loopButton.isCorrectLetter() ? LinearLayout.VISIBLE
								: LinearLayout.INVISIBLE);
					}
	
					button.setVisibility(LinearLayout.INVISIBLE);
                    onClickHintButton();
				}
			});
		}
		
		if(lettersBoxArg.getAnswerButton()!= null){
			
			lettersBoxArg.getAnswerButton().setOnClickListener(new OnClickListener() {
	
				@Override
				public void onClick(View button) {
	
                fullEnterLetters = false;

                for (ButtonEnterLetter loopButton : buttonEnterLetters) {
                    loopButton.setText("");
                    loopButton.setOnTouchListener(null);
                }

                int cpt = 0;
                for (char loopLetter : currentWord
                        .toUpperCase(Locale.FRANCE).toCharArray()) {
                    if (loopLetter == ' ')
                        continue;
                    buttonEnterLetters.get(cpt).setText("" + loopLetter);
                    cpt++;
                }

                for (ButtonRandomLetter loopButton : buttonRandomLetters) {
                    loopButton.setVisibility(LinearLayout.INVISIBLE);
                }
	            onClickAnswerButton();
				}
			});
		}
		
	}

    private void onTestAnswer(boolean result){
        if(result){
            //We have to block type on enter letters
            for(Button loopEnterLetter : buttonEnterLetters)
                loopEnterLetter.setEnabled(false);
        }
        onFullEnterLetters(result);
    }

	/**
	 * 
	 * This function is call when user have complete enter letter
	 * 
	 * @param result
	 *            : true if the word enter by user and the answer is the same,
	 *            false otherwise
	 */
	public abstract void onFullEnterLetters(boolean result);

	/**
	 * This function is called when the list words is empty
	 */
	public abstract void onEmptyListWords();

    /**
     * This function is called when user click on hint button
     */
    public abstract void onClickHintButton();

    /**
     * This function is called when user click on answer button
     */
    public abstract void onClickAnswerButton();

	/**
	 * reload letters with the next word of the list
	 */
	public void reloadWithNextWord() {
		if (!nextWord())
			return;

		lettersBoxArg.getEnterLetters().removeAllViews();
		buttonEnterLetters.clear();

		fullEnterLetters = false;
		createEnterLetters();
		currentShuffleLetters = shuffleLetters(currentWord);
		int cpt = 0;
		int minIndex = 0;
		int maxIndex = lettersBoxArg.getMaxLettersByLign();
		for (int i = 1; i <= lettersBoxArg.getMaxLine(); i++) {
			for (CustomCharacter loopChar : currentShuffleLetters.subList(
					minIndex, maxIndex)) {
				ButtonRandomLetter loopButton = buttonRandomLetters.get(cpt);
				loopButton.setVisibility(LinearLayout.VISIBLE);
				loopButton.setText(loopChar.getLetter().toString());
				loopButton.setCorrectLetter(loopChar.isCorrectLetter());
				cpt++;
			}

			minIndex = maxIndex;
			maxIndex = (i + 1) * lettersBoxArg.getMaxLettersByLign();
		}
	}

    /**
     * Use to create all random letters
     */
	private void createRandomLetters() {

		int minIndex = 0;
		int maxIndex = lettersBoxArg.getMaxLettersByLign();

		for (int i = 1; i <= lettersBoxArg.getMaxLine(); i++) {
			LinearLayout loopLinearLayoutEnterLetters = new LinearLayout(
					lettersBoxArg.getCurrentActivity());
			loopLinearLayoutEnterLetters
					.setOrientation(LinearLayout.HORIZONTAL);
			loopLinearLayoutEnterLetters.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			for (CustomCharacter loopChar : currentShuffleLetters.subList(
					minIndex, maxIndex)) {

                ButtonRandomLetter loopButton = (ButtonRandomLetter) lettersBoxArg
						.getCurrentActivity().getLayoutInflater()
						.inflate(R.layout.layout_random_letter, null);

				//set letters drawable background
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    loopButton.setBackgroundDrawable(lettersBoxArg.getRandomLettersDrawable());
                } else {
                    loopButton.setBackground(lettersBoxArg.getRandomLettersDrawable());
                }

                setLettersStyle(lettersBoxArg.getRandomLettersStyle(), loopButton);
				loopButton.setText(loopChar.getLetter().toString());
				loopButton.setCorrectLetter(loopChar.isCorrectLetter());
				loopButton.setOnClickListener(onClickRandomLetter);


				buttonRandomLetters.add(loopButton);
				loopLinearLayoutEnterLetters.addView(loopButton);
			}

			minIndex = maxIndex;
			maxIndex = (i + 1) * lettersBoxArg.getMaxLettersByLign();
			lettersBoxArg.getRandomLetters().addView(
					loopLinearLayoutEnterLetters);
		}

	}

    /**
     * Use to create all enter letters
     */
	private void createEnterLetters(){
		
		int cptLineLetters = 0;
		LinearLayout loopLinearLayout = new LinearLayout(lettersBoxArg.getCurrentActivity());
		lettersBoxArg.getEnterLetters().addView(loopLinearLayout);

		for (String loopAnswer : currentWord.split(" ")) {
			
			if (cptLineLetters + loopAnswer.length() + 1 >= lettersBoxArg.getMaxLettersByLign()) {
				loopLinearLayout = new LinearLayout(lettersBoxArg.getCurrentActivity());
				loopLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

				lettersBoxArg.getEnterLetters().addView(loopLinearLayout);
				loopLinearLayout.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

				cptLineLetters = 0;
			} else if (cptLineLetters != 0) {

                ImageView tiltView = (ImageView) lettersBoxArg.getCurrentActivity().
						getLayoutInflater().inflate(R.layout.tilt_letter,  null);

                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    tiltView.setBackgroundDrawable(lettersBoxArg.getTiltDrawable());
                } else {
                    tiltView.setBackground(lettersBoxArg.getTiltDrawable());
                }

				tiltView.setLayoutParams(new LayoutParams(0,
                        LayoutParams.MATCH_PARENT, 0.3f));
				loopLinearLayout.addView(tiltView);
			}
			for (int i = 0; i < loopAnswer.length(); i++) {

                ButtonEnterLetter loopButton = (ButtonEnterLetter) lettersBoxArg.getCurrentActivity()
						.getLayoutInflater().inflate(
								R.layout.layout_enter_letter, null);

                //set letters drawable background
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    loopButton.setBackgroundDrawable(lettersBoxArg.getEnterLettersDrawable());
                } else {
                    loopButton.setBackground(lettersBoxArg.getEnterLettersDrawable());
                }
                setLettersStyle(lettersBoxArg.getEnterLettersStyle(), loopButton);
				loopButton.setEnabled(false);
				loopLinearLayout.addView(loopButton);
				buttonEnterLetters.add(loopButton);
			}
			cptLineLetters += loopAnswer.length() + 1;
		}

		
	}

    /**
     * set onclick event on random letters
     */
	private OnClickListener onClickRandomLetter = new OnClickListener() {

		@Override
		public void onClick(View button) {

			if (fullEnterLetters || currentWord == null)
				return;

			int position = buttonRandomLetters.indexOf((Button) button);
			Button currentButtonL = (Button) button;
			enterLetter(position, currentButtonL.getText().toString(),
					buttonRandomLetters.get(position));

		}
	};

    /**
     * set onclick event on enter letters
     */
	private OnClickListener onClickEnterLetter = new OnClickListener() {

		@Override
		public void onClick(View button) {

			if (currentWord == null)
				return;

			ButtonEnterLetter currentButton = (ButtonEnterLetter) button;
			currentButton.setText("");
			currentButton.setOnTouchListener(null);
			currentButton.setEnabled(false);
			buttonRandomLetters.get(currentButton.getIndexOfRandomLetter())
					.setVisibility(LinearLayout.VISIBLE);
			fullEnterLetters = false;
		}
	};

    /**
     * method call on click on enter letter
     * This method is synchronized to prevent multi-click on letters.
     */
	private synchronized void enterLetter(int index, String charLetter,
			Button button) {
        //Loop of all enter letters
		for (ButtonEnterLetter loopButton : buttonEnterLetters) {
            //We are looking for the first empty enter letter
			if (loopButton.getText() == null || loopButton.getText().equals("")) {
				loopButton.setText(charLetter);
				loopButton.setIndexOfRandomLetter(index);
				loopButton.setOnClickListener(onClickEnterLetter);
				loopButton.setEnabled(true);
				button.setVisibility(LinearLayout.INVISIBLE);
                //We check if with this letter, enter letters if full
				if (currentWord.replaceAll(" ", "").length() == countEnterLetter()) {
					fullEnterLetters = true;
					onTestAnswer(testResult());
				}
                //Exit method when we have set the button
				return;
			}
		}
	}

    /**
     * Test if the enter word correspond with the answer
     * @return true if word = answer, false otherwise
     */
	private boolean testResult() {
		StringBuffer buffer = new StringBuffer();
		for (ButtonEnterLetter loopButton : buttonEnterLetters) {
			buffer.append(loopButton.getText().toString());
		}
		return currentWord.replaceAll(" ", "").equalsIgnoreCase(
				buffer.toString());
	}

    /***
     * count the number of enter letter to know when user have set all enter letters
     * @return the number of enter letters
     */
	private int countEnterLetter() {
		int cpt = 0;
		for (ButtonEnterLetter loopButton : buttonEnterLetters) {
			if (!loopButton.getText().toString().equals(""))
				cpt++;
		}
		return cpt;
	}

    /**
     * Method use to shuffle letters to have random list of letters
     * You have to give the word you want to guess then it will automatically
     * shuffle letter of the answer with other letters.
     * @param answer the word to guess
     * @return a list of CustomCharacter
     */
	private List<CustomCharacter> shuffleLetters(String answer) {
		List<CustomCharacter> randomLetters = new ArrayList<CustomCharacter>();

		for (char loopChar : answer.replaceAll(" ", "").toCharArray()) {
			randomLetters.add(new CustomCharacter(Character
					.toUpperCase(loopChar), true));
		}
		while (randomLetters.size() < lettersBoxArg.getMaxLetters()) {
			randomLetters.add(new CustomCharacter(Alphabet.getRandomLetter(),
					false));
		}
		Collections.shuffle(randomLetters);
		return randomLetters;
	}

	/**
	 * 
	 * This function is for set currentWord with the next word of the list in
	 * lettersBoxArg.
	 * 
	 * @return true if there is a word, false otherwise
	 */
	private boolean nextWord() {
		currentWord = lettersBoxArg.getWords().poll();

        //Check if it is the list is empty, poll return null when there is no more items in list
        if (currentWord == null) {
			onEmptyListWords();
			return false;
		}

        //Check if current word length is longer than the max number letter show, if yes, we
        //cannot use this word and we take the next
        if(currentWord.length() > lettersBoxArg.getMaxLetters())
            return nextWord();

		return true;
	}

    /**
     * This method use to set style of a button
     * @param style style to apply
     * @param letter the button
     */
    private void setLettersStyle(LettersBoxStyle style, Button letter){
        letter.setTextSize(style.getSizeUnit(), style.getSize());
        letter.setTextColor(style.getColor());
        letter.setLayoutParams(style.getLayoutParams());
        letter.setPadding(style.getPaddingLeft(), style.getPaddingTop(), style.getPaddingRight(), style.getPaddingBottom());
        letter.setTypeface(style.getTypeface());
    }

}
