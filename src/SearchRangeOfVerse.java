/**
 * Class to search for a range of verses based on input
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchRangeOfVerse {

	private static File folder = new File("data");

	public SearchRangeOfVerse() {

	}

	/**
	 * Gets a range of verses bases on input
	 * 
	 * @param book
	 * @param chapter
	 * @param verseStart
	 * @param verseEnd
	 * @throws IOException
	 */
	public static void findChapterAndVerse(String book, int chapter,
			int verseStart, int verseEnd) throws IOException {

		// variables
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> list = new ArrayList<String>();

		// initialises book for reading
		for (File file : listOfFiles) {
			try {

				// reads the book
				BufferedReader input = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));

				// list of variables
				String name = "";
				String line;
				int chapterCount = 0;
				int currentVerse = 0;

				// gets name of book
				if (file.getName().endsWith(".txt")) {
					name = file.getName().replace(".txt", "");
				}

				if (name.equals(book)) {
					while ((line = input.readLine()) != null) {

						// resets line counter after checking the chapter
						// name
						if (line.indexOf("CHAPTER") > -1) {
							currentVerse = -1;
							chapterCount++;
							currentVerse++;
						}

						// checker for Psalm
						if (line.indexOf("PSALM") > -1) {
							chapterCount++;
							currentVerse++;
						}

						// if chapter, upper and lower bounds are the correct,
						// prints out the verse
						if ((chapterCount == chapter)
								&& (currentVerse >= verseStart)
								&& (currentVerse <= verseEnd)) {
							list.add(line);
							System.out.println(line);
							currentVerse++;

						} else {
							currentVerse++;
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
			System.out
					.println("There are not the range of verses in this book");
		}
	}
}
