package game;
import java.util.Arrays;

public class WinnerIdentifying {
	public static String play(String userMove, String compMove, String[] args) {
		int indexUser = Arrays.asList(args).indexOf(userMove);
		int indexComp = Arrays.asList(args).indexOf(compMove);
		String[] results = new String[args.length];
		Arrays.fill(results, "Win");
		results[indexUser] = "Draw";
		for (int i = 0; i < (args.length - 1)/2; i++) {
			results[(indexUser + i + 1) % args.length] = "Lose";
		}
		return results[indexComp];
	}
}
