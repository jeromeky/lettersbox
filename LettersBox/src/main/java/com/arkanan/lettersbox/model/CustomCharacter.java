package com.arkanan.lettersbox.model;

/**
 *
 * Custom character is used to create enter letter.
 *
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
