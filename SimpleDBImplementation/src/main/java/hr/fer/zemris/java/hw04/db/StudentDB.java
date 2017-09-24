package hr.fer.zemris.java.hw04.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * The Class StudentDB instantiates a student database and allows you to write queries through
 * the console. For valid queries check the QueryParser documentation. Enter exit to enter the program.
 */
public class StudentDB {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * 
	 * @author Ivica Šimić
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<String> lines;
		StudentDatabase db = null;
		
		try {
			lines = Files.readAllLines(
					 Paths.get("C:\\Users\\Ivica\\Desktop\\faks\\database.txt"),
					 StandardCharsets.UTF_8
					);
			db = new StudentDatabase(lines.toArray(new String[lines.size()]));
		} catch (IOException e) {
			System.out.println("Cannot find the file!");
			System.exit(1);
		}
		
		while(true) {
			System.out.print(">");
			String line = sc.nextLine();
			line = line.trim();
			if(!line.startsWith("query")) {
				System.out.println("Queries must start with key word 'query' after which you write the queries.");
				continue;
			}
			if(line.equals("exit")) {
				System.out.println("Goodbye!");
				break;
			}
			
			QueryParser parser = new QueryParser(line.substring(5).trim());
			
			if(parser.isDirectQuery()) {
				StudentRecord r = db.forJMBAG(parser.getQueriedJMBAG());
				System.out.println("Using index for record retrieval.");
			 	System.out.println("+============+========+========+====+");
			 	
			 	System.out.format("| %s | %s | %s | %s |%n", r.getJmbag(), r.getLastName(), 
			 			r.getFirstName(), r.getGrade());
			 	
			 	System.out.println("+============+========+========+====+");
			 	System.out.println("Records selected: 1");
			} else {
				
				System.out.println("+============+========+========+====+");
				int i = 0;
				
			 for(StudentRecord r : db.filter(new QueryFilter(parser.getQuery()))) {
				 System.out.format("| %s | %s | %s | %s |%n", r.getJmbag(), r.getLastName(), 
						 r.getFirstName(), r.getGrade());
				 i++;
			 }
			 	System.out.println("+============+========+========+====+");
			 	System.out.println("Records selected: "+i);
			}

		}
		sc.close();
	}
}
