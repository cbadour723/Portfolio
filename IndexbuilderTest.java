package project3_final;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IndexbuilderTest {

	private static String document_path = "C:\\Users\\caleb\\eclipse-workspace\\CSCI 4130\\cran\\documents";
	private static String querie_path = "C:\\Users\\caleb\\eclipse-workspace\\CSCI 4130\\cran\\cran.QRY";
	private static String cranqrel = "C:\\Users\\caleb\\eclipse-workspace\\CSCI 4130\\cran\\cranqrel";
	private static String cran_all_path = "C:\\Users\\caleb\\eclipse-workspace\\CSCI 4130\\cran\\cran.all.txt";
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter directory for corpus: ");
		String corpusDir = scanner.nextLine();
		
		IndexBuilder indexBuilder = new IndexBuilder();
		try {
			indexBuilder.buildIndex(corpusDir);
		} catch (IOException e) {
			System.err.println("Error building index: " + e.getMessage());
			scanner.close();
			return;
		}
		
		boolean done = false;
		while (!done) {
			System.out.println("1. Print Inverted Index");
			System.out.println("2. Intersecting Algorithm");
			System.out.println("3. Print Documents for specific word");
			System.out.println("4. Exit");
			System.out.println("5. calculate TfIdf");
			System.out.println("6. load queries");
			System.out.println("7. run queries");
			System.out.println("8. caculate MAP");
			System.out.print("Select an option: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			switch (option) {
				case 1:
					indexBuilder.printIndex();
					System.out.println();
					break;
				case 2:
					List<String> terms = new ArrayList<>();
					System.out.print("Enter first term: ");
					String term1 = scanner.nextLine().toLowerCase();
					System.out.print("Enter second term: ");
					String term2 = scanner.nextLine().toLowerCase();
					terms.add(term1);
					terms.add(term2);
					System.out.println(indexBuilder.intersect(terms));
					System.out.println();
					break;
				case 3:
					System.out.print("Enter word to search: ");
					String word = scanner.nextLine().toLowerCase();
					indexBuilder.printdocs(word);
					System.out.println();
					break;
				case 4:
					done = true;
					break;
				case 5:
					TfIdf calculate = new TfIdf(document_path);
					calculate.printTfIdfMap();
					break;
				case 6:
					LoadQueries load = new LoadQueries(querie_path);
					load.load();
				case 7:
					LoadQueries loading = new LoadQueries(querie_path);
					TfIdf calculating = new TfIdf(document_path);
					RunQueries run = new RunQueries(loading.load(),calculating.getTfIdfMap());
					run.run();
					
					break;
				case 8:
					MapCalculator map = new MapCalculator(cranqrel);
					System.out.println(map.calculateMAP(indexBuilder.getIndex()));
				default:
					System.out.println("Invalid option.");
					System.out.println();
					break;
			}
		}
		
		scanner.close();
	}

}
