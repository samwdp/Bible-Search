/**
 * Class to search for a chapter in a specific book and chapter
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchChapterAndVerse {

	private static File folder = new File("data");

	public SearchChapterAndVerse() {

	}

	/**
	 * searches through books to find the book, verse and chapter
	 * 
	 * @param book
	 * @param chapter
	 * @param verse
	 * @throws IOException
	 */
	public static void findChapterAndVerse(String book, int chapter, int verse)
			throws IOException {

		File[] listOfFiles = folder.listFiles();
		ArrayList<String> list = new ArrayList<String>();

		verse += 1;
		for (File file : listOfFiles) {
			try {
				BufferedReader input = new BufferedReader(
						new InputStreamReader(new FileInputStream(file)));

				// list of variables
				String name = "";
				String line;
				int chapterCount = 0;
				int verseNo = 0;

				// gets name of book
				if (file.getName().endsWith(".txt")) {
					name = file.getName().replace(".txt", "");
				}

				if (name.equals(book)) {

					// reads book line by line
					while ((line = input.readLine()) != null) {

						// resets line counter after checking the chapter
						// name
						if (line.indexOf("CHAPTER") > -1) {
							verseNo = 0;
							chapterCount++;
							verseNo++;
						}

						// checker for PsalmS
						if (line.indexOf("PSALM") > -1) {
							chapterCount++;
							verseNo++;
						}

						// if the chapter and the verse are the same as the
						// input then the line is printed out
						if ((chapterCount == chapter) && (verseNo == verse)) {
							list.add(line);
							System.out.println(line);
							verseNo++;
						} else {
							verseNo++;
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
					.println("This book does not contain the verse in that chapter");
		}
	}
}
