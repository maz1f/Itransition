package game;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {

	public static void main(String[] args) {
		try {
			if (args.length < 3  || args.length % 2 == 0) {
				throw new IllegalArgumentException("Wrong arguments. For correct operation, please use an odd number of arguments >= 3. For example: 'rock paper scissors'.");
			}
			Set<String> arguments = new HashSet<>();
			Collections.addAll(arguments, args);
			if (arguments.size() != args.length)
				throw new IllegalArgumentException("Wrong arguments. For correct operation, please use non-duplicate arguments");
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		SecureRandom rnd = new SecureRandom();
		String computer_move = args[rnd.nextInt(args.length)];
		Hmac hmac = new Hmac(computer_move);
		System.out.println("HMAC: " + hmac.getHmac());
		String[] moves = new String[args.length + 1];
		for (int i = 0; i < args.length; i++) {
			moves[i] = Integer.toString(i + 1);
		}
		moves[args.length] = "0";
		Scanner in = new Scanner(System.in);
		String getMove = null;
		while (true) {
			System.out.println("Available moves:");
			for (int i = 0; i < args.length; i++ ) {
				System.out.println((i + 1) + " - " + args[i] );
			}
			System.out.println("0 - exit\n? - help");
			getMove = in.nextLine();
			if (getMove.equals("0"))
				System.exit(0);
			if (getMove.equals("?")) {
				Table.getTable(args);
			}
			if (Arrays.asList(moves).contains(getMove))
				break;
		}
		in.close();
		String user_move = args[Integer.parseInt(getMove) - 1];
		String res = WinnerIdentifying.play(user_move, computer_move, args);
		String result = null;
		switch(res) {
		case "Draw": result = "Draw!"; break;
		case "Win": result = "You win!"; break;
		case "Lose": result = "You lost =("; break;
		}
		System.out.println("Your move: " + user_move);
		System.out.println("Computer move: " + computer_move);
		System.out.println(result);
		System.out.println("Hmac key: " + hmac.getKey());
	}
	
}
