package com.arkanan.lettersbox.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents all alphabet letters
 * @author jeromeky
 *
 */
public class Alphabet {

	private static List<Character> alphabet = new ArrayList<Character>() {

		private static final long serialVersionUID = 1390369209346441844L;

		{
			add('A');
			add('B');
			add('C');
			add('D');
			add('E');
			add('F');
			add('G');
			add('H');
			add('I');
			add('J');
			add('K');
			add('L');
			add('M');
			add('N');
			add('O');
			add('P');
			add('Q');
			add('R');
			add('S');
			add('T');
			add('U');
			add('V');
			add('W');
			add('X');
			add('Y');
			add('Z');
		}
	};

	public static Character getRandomLetter() {
		return alphabet.get(new Random().nextInt(alphabet.size()));
	}
}
