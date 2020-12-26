
/**
 * This program reads the a given file and checks whether the data in the file is
 * in the correct format or not.
 * @author Daniel Rao
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FormatChecker {

	public static void main(String[] args) {

		// checks if there is input in command line
		if (args.length == 0) {
			System.err.println("Usage: $ java FormatChecker file1 [file2 ... fileN] ");

		}
		// takes all
		for (int i = 0; i < args.length; i++) {

			try {
				checkFormat(args[i]);
			} catch (FileNotFoundException e) {
				System.out.println(e + ": " + args[i] + " noSuchFile (The system cannot find the file specified)");
				System.out.println("INVALID");
				System.out.println("");

			} catch (NumberFormatException e) {
				System.out.println(e);
				System.out.println("INVALID");
				System.out.println("");
			} catch (DimensionMismatchException e) {
				System.out.println(e + " (There are more or less number of rows or columns of data than specified.)");
				System.out.println("INVALID");
				System.out.println("");

			}


		}

	}

	/**
	 * 
	 * @param fileName takes a parameter of string version of filename
	 * @throws FileNotFoundException
	 */

	public static void checkFormat(String fileName) throws FileNotFoundException {

		String stringRow;
		int row;
		int column;
		String line1;
		Scanner lineScan;
		String ColumntokenInString, columnInString;

		File file = new File(fileName);

		// checks if the file is there.
		Scanner scan = new Scanner(file);

		stringRow = scan.next();
		columnInString = scan.next();
		// converts the rows to an integer
		row = Integer.parseInt(stringRow);
		// converts the column an to integer
		column = Integer.parseInt(columnInString);

		int rowTotal = 0;

		while (scan.hasNextLine()) {
			line1 = scan.nextLine();

			if (!line1.isEmpty()) {
				lineScan = new Scanner(line1);
				while (lineScan.hasNext()) {
					for (int j = 0; j < column; j++) {
						// checks and throws the mimensionmismatchexception is there is an extra column
						// in the file than what has been deginated in the heading of the file.
						if (!lineScan.hasNext()) {
							lineScan.close();
							throw new DimensionMismatchException();
						}

						ColumntokenInString = lineScan.next();
						double keyword = Double.parseDouble(ColumntokenInString);

					}

					if (lineScan.hasNext())
					// throws exception if there are extra tokens
					{
						lineScan.close();
						throw new DimensionMismatchException();
					}
				}

				rowTotal++;
				lineScan.close();

			}
		}

		if (rowTotal == row)
		// checking if row count is equal to the length of row.
		{
			System.out.println("VALID");
			System.out.println("");

		} else {
			scan.close();
			throw new DimensionMismatchException();
		}
		scan.close();

	}

}

