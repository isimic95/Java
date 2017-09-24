package hr.fer.zemris.java.hw04.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadDB {
	
	public static StudentDatabase getDB() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("C:\\Users\\Ivica\\Desktop\\faks\\database.txt"));
		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
		  lines.add(sc.nextLine());
		}
		sc.close();

		String[] arr = lines.toArray(new String[0]);
		StudentDatabase db = new StudentDatabase(arr);
		return db;
	}
}
