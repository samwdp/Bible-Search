/**
 * Class to search the books for a specific book and chapter
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchChapter {

	private static File folder = new File("data");

	public SearchChapter() {

	}

	/**
	 * Searches for chapter and prints it out
	 * 
	 * @param book
	 * @param chapter
	 * @throws IOException
	 */
	public static void findChapter(String book, int chapter) throws IOException {

		// setting up the variables
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> list = new ArrayList<String>();

		// iterating through the list of books
		for (File file : listOfFiles) {
			try {

				// reading the book
				BufferedReader input = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));

				// setting up more variables
				String name = "";
				String line;
				int chapterCount = 0;

				// getting the name of the book
				if (file.getName().endsWith(".txt")) {
					name = file.getName().replace(".txt", "");
				}

				// checking if the name of the book is the same as the input
				if (name.equals(book)) {

					// reads book line by line
					while ((line = input.readLine()) != null) {

						// resets line counter after checking the chapter
						// name
						if (line.indexOf("CHAPTER") > -1) {
							chapterCount++;
						}

						// checker for Psamls
						if (line.indexOf("PSALM") > -1) {
							chapterCount++;
						}

						// printing out the chapter
						if (chapterCount == chapter) {
							list.add(line);
							System.out.println(line);
						}
					}
				}

				input.close();
			} catch (FileNotFoundException e) {
				System.out.println("File was not found");
			}

		}
		// if the word is not in the list
		if (list.isEmpty()) {
			System.out.println("This book does not contain this chapter");
		}
	}
}
