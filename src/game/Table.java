package game;

import java.util.Arrays;

import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

public class Table {
	public static void getTable(String[] args) {
		String[] headers = new String[args.length + 1];
		headers[0] = "";
		System.arraycopy(args, 0, headers, 1, args.length);
		Object[][] data = new Object[args.length][args.length + 1];
		for (int i = 0; i < args.length; i++) {
			data[i][0] = args[i];
			for (int j = 1; j < args.length + 1; j++) {
				data[i][j] = WinnerIdentifying.play(args[i], args[j-1], args);
			}
		}
		System.out.println(FlipTableConverters.fromObjects(headers, data));
		
	}
}
