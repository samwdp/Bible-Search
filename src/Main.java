import java.io.IOException;
import java.util.*;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) throws IOException {
		// sets up the welcome to the application
		System.out.println(
				  "<<<            Input search below                >>>\n"
				+ "<<<             Enter single word                >>>\n"
				+ "<<<       Enter format 'bookName chapter'        >>>\n"
				+ "<<<    Enter format 'bookName chapter:verse'     >>>\n"
				+ "<<< Enter format 'bookName chapter:verse1-verse2 >>>\n"
				+ "<<<             Type '/E' to exit                >>>");

		// initialise variable
		int startVerse;
		int endVerse;
		int chapter;
		String book;
		// makes the scanner to read input
		scanner = new Scanner(System.in);

		// main loop
		while ((book = scanner.nextLine()) != null) {
			// splits the input into separate parts
			String[] part = book.split("(?<=\\D)(?=\\d)");
			book = part[0];
			book = book.trim();

			// if the search only has 1 item in the array, it is searching for a
			// word
			if (part.length == 1) {
				// checks if the input is empty, is it is an exception is thrown
				if (book.isEmpty()) {
					throw new IOException("Nothing was entered");
				}

				// exits application
				if (book.indexOf("/E") > -1) {
					System.out.println("<<< System exit. are you sure >>>\n"
							+ "<<< Type '/Y' for Yes          >>>\n"
							+ "<<< Type '/N' for No           >>>");
					book = scanner.nextLine();

					// decision for application exit
					if (book.indexOf("/Y") > -1) {
						System.out.println("Thanks for using this application");
						System.exit(0);
					} else if (book.indexOf("/N") > -1) {
						System.out.println("<<< Do a search >>>");
						book = scanner.nextLine();
						SearchWord.findWord(book);
					}
				} else {
					SearchWord.findWord(book);
				}
			}

			// searches for book and chapter
			if (part.length == 2) {
				chapter = Integer.valueOf(part[1]);
				SearchChapter.findChapter(book, chapter);
			}

			// searches for book, chapter and verse
			if (part.length == 3) {
				String tempChap = part[1];
				tempChap = tempChap.trim().replace(":", "");
				chapter = Integer.valueOf(tempChap);

				startVerse = Integer.valueOf(part[2]);

				SearchChapterAndVerse.findChapterAndVerse(book, chapter,
						startVerse);
			}

			// searches for book, chapter and range of verse
			if (part.length == 4) {
				// change chapter
				String tempChap = part[1];
				tempChap = tempChap.trim().replace(":", "");
				chapter = Integer.valueOf(tempChap);

				// change start verse
				String tempStart = part[2];
				tempStart = tempStart.trim().replace("-", "");
				startVerse = Integer.valueOf(tempStart);

				// set last value
				endVerse = Integer.valueOf(part[3]);

				SearchRangeOfVerse.findChapterAndVerse(book, chapter,
						startVerse, endVerse);
			}
		}

	}
}
