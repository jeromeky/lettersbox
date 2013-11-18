package com.arkanan.lettersbox.model;

/**
 *
 * Model use to create letters
 * @author jeromeky
 *
 */
public class CustomCharacter {

	private Character letter;

	private boolean correctLetter;

	public CustomCharacter(Character letter, boolean correctLetter) {
		this.letter = letter;
		this.correctLetter = correctLetter;
	}

	public Character getLetter() {
		return letter;
	}

	public void setLetter(Character letter) {
		this.letter = letter;
	}

	public boolean isCorrectLetter() {
		return correctLetter;
	}

	public void setCorrectLetter(boolean correctLetter) {
		this.correctLetter = correctLetter;
	}

}
