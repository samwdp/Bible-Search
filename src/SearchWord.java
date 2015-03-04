/**
 * Class to search for a word
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchWord {

	// path to where the files are
	private static File folder = new File("data");

	public SearchWord() {

	}

	/**
	 * Searches through all the files to find a word
	 * 
	 * @param book
	 *            the word that is being searched for
	 */
	public static void findWord(String book) {
		// makes an array of all the files in a folder KJBible
		File[] listOfFiles = folder.listFiles();
		// looks into txt file one by one and reads them to find the searched
		// word
		for (File file : listOfFiles) {
			try {
				// buffer reader iterates through txt file and keeps a count on
				// how many lines the word appears
				BufferedReader input = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));

				// data needed
				String line;
				ArrayList<String> list = new ArrayList<String>();
				int lineCounter = 0;
				int lineWithoutWord = 0;
				int chapterCount = 0;
				String name = "test";

				// removes the .txt from the file name
				if (file.getName().endsWith(".txt")) {
					name = file.getName().replace(".txt", "");
				}

				// iterates through the file line by line
				while ((line = input.readLine()) != null) {

					// resets line counter after checking the chapter name
					if (line.indexOf("CHAPTER") > -1) {
						chapterCount++;
						lineCounter = 0;
					}

					// resets in case of psalm
					if (line.indexOf("PSALM") > -1) {
						chapterCount++;
						lineCounter = 0;
					}

					// checks line of file for the word
					if (line.indexOf(book) > -1) {
						list.add(book);
						System.out.println("[" + name + " " + chapterCount
								+ ":" + (lineCounter + lineWithoutWord) + "]");

					}
					lineCounter++;
				}

				// if the word is not in the list
				if (list.isEmpty()) {
					System.out.println(name + " does not contain " + book);
				}

				// closes file
				input.close();
			}
			// if there is no file an exception is thrown
			catch (Exception e) {
				System.out.println("Could not find file");
			}
		}
	}
}
