import CustomExceptions.*;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Nicole Koran (40281430) and Justin Tran (40281429)
 * COMP249
 * Assignment #2
 * Date Due: Mar 27, 2024
 */


/**
 * This Java program assists Mr. Filmbuff, a movie theater employee from the 90s, 
 * in organizing and accessing movie information stored in text files. It involves 
 * partitioning movie records into genre-based text files, serializing these records 
 * into binary files for efficient storage, deserializing them to reconstruct movie 
 * objects in a 2D array, and providing an interactive program for users to navigate 
 * and view movie details, enhancing efficiency and ease of access to the movie catalog.
 */


/**
 * This is the main driver class for the movie application.
 * It contains methods to start and manage the application.
 */
public class Driver {
	
	
	/**
     * Default constructor for the Driver class.
     * This constructor initializes a new instance of the Driver class.
     */
    public Driver() {
        // Constructor logic goes here
    }
	
	/**
	 * Default constructor of main class.
	 */
	public void main() {};
	
	
	
	
	
	/**
     * Keeps track of the current index position in each movie array for vertical navigation
     */
    private static int[] currPosition = new int[17];
    
    /**
     * An array to store the number of records for each genre (17 genres).
     */
    private static int[] numGenreRecords = new int[17];
    
    /**
     * An array containing names of different genres.
     */
    private static String[] genreNames = {
            "musical", "comedy", "animation", "adventure", "drama", "crime", "biography",
            "horror", "action", "documentary", "fantasy", "mystery", "sci-fi", "family",
            "romance", "thriller", "western"
        };
    
    /**
     * A two-dimensional array to store all movies. Rows represent different genres, and columns represent movies within each genre.
     */
    static Movie[][] allMovies;
    
    /**
     * A static Scanner object used for reading input from the console.
     */
    static Scanner scan = new Scanner(System.in);
	

    /**
     * The main method of the program.
     *
     * @param args The command-line arguments.
     * @throws MissingFieldsException If required fields are missing in movie records.
     * @throws ExcessFieldsException If there are excess fields in movie records.
     * @throws MissingQuotesException If quotes are missing in movie records.
     * @throws BadYearException If the year in movie records is invalid.
     * @throws BadDurationException If the duration in movie records is invalid.
     * @throws BadScoreException If the score in movie records is invalid.
     * @throws BadRatingException If the rating in movie records is invalid.
     * @throws BadGenreException If the genre in movie records is invalid.
     * @throws BadTitleException If the title in movie records is invalid.
     * @throws BadNameException If the name in movie records is invalid.
     * @throws IOException If an I/O error occurs while reading or writing files.
     */
	public static void main(String[] args) throws MissingFieldsException, ExcessFieldsException, MissingQuotesException, BadYearException, BadDurationException, BadScoreException, BadRatingException, BadGenreException, BadTitleException, BadNameException, IOException {

		
		
		// display welcome message
		System.out.println("Welcome to the Movie Catalog Organization Program by Nicole Koran and Justin Tran.\n");
		
		// create and write to the part1_manifest file
		String part1_manifest = createPart1ManifestFile();
		
		// part 2’s manifest file
		String part2_manifest = do_part1(part1_manifest); // partition
		
		// part 3’s manifest file
	    String part3_manifest = do_part2(part2_manifest);  // serialize
	     allMovies = do_part3(part3_manifest); // deserialize
		
	    // initialize numGenrRecords
	    // Iterate over the genres and populate the array
	    for (int i = 0; i < 17; i++) {
		    numGenreRecords[i] = findNumRecords(i, allMovies);
		}
	    
	    
	 // start by showing the first genre which is musical.
	 		int currGenre = 0;
	 		displayMenu(0);
	 		
	 		
	 		return;

	}
	

	
	/**
	 * Displays the genre sub-menu, allowing the user to select a genre to navigate.
	 */
	private static void displayGenreSubMenu() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("	Genre Sub-Menu");
		System.out.println("------------------------------");
		// Loop through each genre and print its menu option
	    for (int i = 0; i < genreNames.length; i++) {
	        System.out.println((i + 1) + "\t" + genreNames[i] + "   (" + numGenreRecords[i] + " movies)");
	    }

	    System.out.println("18\tExit");
	    System.out.println("------------------------------");
	    System.out.println();
	    System.out.print("Enter Your Choice: ");

	    String genreChoice = scanner.nextLine();

	    // Handle genre choice using switch statement
	    switch (genreChoice) {
	        case "1":
	            displayMenu(0);
	            break;
	        case "2":
	            displayMenu(1);
	            break;
	        case "3":
	            displayMenu(2);
	            break;
	        case "4":
	            displayMenu(3);
	            break;
	        case "5":
	            displayMenu(4);
	            break;
	        case "6":
	            displayMenu(5);
	            break;
	        case "7":
	            displayMenu(6);
	            break;
	        case "8":
	            displayMenu(7);
	            break;
	        case "9":
	            displayMenu(8);
	            break;
	        case "10":
	            displayMenu(9);
	            break;
	        case "11":
	            displayMenu(10);
	            break;
	        case "12":
	            displayMenu(11);
	            break;
	        case "13":
	            displayMenu(12);
	            break;
	        case "14":
	            displayMenu(13);
	            break;
	        case "15":
	            displayMenu(14);
	            break;
	        case "16":
	            displayMenu(15);
	            break;
	        case "17":
	            displayMenu(16);
	            break;
	        case "18":
	            System.out.println("Exiting. Thank you for using our program. Have a nice day!");
	            scan.close();
	            System.exit(0);
	            break;
	        default:
	            System.out.println("Invalid choice. Please enter a number between 1 and 18.");
	            displayGenreSubMenu();
	            break;
	    }

	    scanner.close();
	}

	
	/**
	 * Finds the number of non-null records in the specified row of the 2D array of movie records.
	 *
	 * @param i The index of the row in the array. The row specifies which genre.
	 * @param allMovies The 2D array of movie records.
	 * @return The number of non-null records in the specified row(genre).
	 */
	private static int findNumRecords(int i, Movie[][] allMovies) {

		int count = 0;
	    
	    // Check if the specified row exists in the array
	    if (i >= 0 && i < allMovies.length) {
	        // Iterate through the row and count non-null elements
	        for (Movie movie : allMovies[i]) {
	            if (movie != null) {
	                count++;
	            }
	        }
	    }
	    
	    return count;

	}




	/**
	 * Displays the main menu of the program, allowing the user to select different options.
	 *
	 * @param currGenre The current genre index. 
	 */
	private static void displayMenu(int currGenre) {
		
		Scanner scan = new Scanner(System.in);
		
		
		
	
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("         	 Main Menu");
		System.out.println("--------------------------------------------");
		System.out.println("s	Select a movie array to navigate");
		System.out.println("n	Navigate "+genreNames[currGenre]+" movies ("+numGenreRecords[currGenre]+" records)");
		System.out.println("x	Exit");
		System.out.println("--------------------------------------------");
		System.out.println();
		System.out.print("Enter Your Choice: ");
		
		
				
	    String choice = scan.nextLine();
        
        switch (choice.toLowerCase()) {
        case "s":
            displayGenreSubMenu();
            break;
        case "n":
            navigateGenre(currGenre);
            break;
        case "x":
            System.out.println("Exiting. Thank you for using our program. Have a nice day!");
            scan.close();
            System.exit(0);
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
            displayMenu(currGenre);
            break;
    }

		
		
		
	}



	/**
	 * Navigates through movies of a specific genre.
	 *
	 * @param currGenre The index of the current genre.
	 */
	private static void navigateGenre(int currGenre) {
		
		
		System.out.println();
        System.out.println("Navigating "+genreNames[currGenre]+" movies ("+numGenreRecords[currGenre]+")");
        System.out.println("Enter Your Choice (0 to return to main menu): ");
        
        int index = scan.nextInt();
        
        if (index == 0) {
        	displayMenu(currGenre);
        } else if (numGenreRecords[currGenre] == 0) {
        	System.out.println("There are currently no movies in the "+genreNames[currGenre]+ " genre.");
        	displayMenu(currGenre);
        } else if ((index > 0 || index < 0)) {
        	displayMovieRecord(currGenre, index);
        } else {
        	System.out.println("Please enter a valid choice. Choice must be an integer or enter 0 to return to main menu.");
        	navigateGenre(currGenre);
        }

		
		
	}




	/**
	 * Displays movie records of a specific genre based on user input.
	 *
	 * @param currGenre The index of the current genre.
	 * @param index The index of the movie record to display or navigate to.
	 */
	private static void displayMovieRecord(int currGenre, int index) {
		
		int absIndex = Math.abs(index);
		
		if (index < 0) {
			int start = (currPosition[currGenre] - (absIndex - 1));
			//System.out.println("start: "+start);
			if (start < 0)
			{
				start = 0;
				System.out.println("BOF has been reached");
				displayCells (currGenre, start , currPosition[currGenre]);
				currPosition[currGenre] = start;
				//System.out.println("The current position is: " +currPosition[currGenre]);

			} else if (start >= 0) {
				displayCells (currGenre, start , currPosition[currGenre]);
				currPosition[currGenre] = start;
				//System.out.println("The current position is: " +currPosition[currGenre]);

			}
		}
		
		if (index > 0) {
			int end = currPosition[currGenre] + absIndex - 1;

			if (end > (numGenreRecords[currGenre]-1)) {
				end = numGenreRecords[currGenre] - 1;
				System.out.println("EOF has been reached");
				displayCells (currGenre, currPosition[currGenre], end);
				currPosition[currGenre] =  end;

			} else if (end < (numGenreRecords[currGenre]-1)) {
				displayCells (currGenre, currPosition[currGenre], end);
				currPosition[currGenre] = end;
			}
		
		}
		
		navigateGenre(currGenre);
		
	}






	/**
	 * Displays movie records of a specific genre within a specified range.
	 *
	 * @param currGenre The index of the current genre.
	 * @param startIndex The starting index of movie records to display.
	 * @param endIndex The ending index of movie records to display.
	 */
	private static void displayCells(int currGenre, int startIndex, int endIndex) {

		
		 for (int i = startIndex; i <= endIndex; i++) {
	            System.out.println(allMovies[currGenre][i]);
	        }
		 
		 
	}



	/**
	 * Creates the manifest file for part 1, which lists the CSV files containing movie records for each year.
	 * 
	 * @return The filename of the created manifest file.
	 */
	public static String createPart1ManifestFile() {
	
	// part 1’s manifest file
			String part1_manifest = "part1_manifest.txt";
			
			String[] movieFiles = {
		            "movies1990.csv",
		            "movies1991.csv",
		            "movies1992.csv",
		            "movies1993.csv",
		            "movies1994.csv",
		            "movies1995.csv",
		            "movies1996.csv",
		            "movies1997.csv",
		            "movies1998.csv",
		            "movies1999.csv",
		            };
			
			
			try {
				PrintWriter writer = new PrintWriter( new FileOutputStream(part1_manifest));
				
				for (int i=0; i<movieFiles.length; i++) {
					writer.println(movieFiles[i]);
				}
					// Close the writer
		            writer.close();
		            
		            //System.out.println("Data has been written to the Part1_manifest.txt file.");

			}catch (FileNotFoundException e) {
				System.out.println("The specified file was not found.");
	        } catch (IOException e) {
	            System.out.println("An error occurred while writing to the file.");
			}
			
			
			return "part1_manifest.txt";
			
          }
	
	
	
	/**
	 * Performs the tasks outlined in part 1 of the program's instructions:
	 * (1) Partition all valid movie records into new genre-based text files.
	 * (2) Validate records for syntax and semantic errors, categorize them into genre-based arrays, and handle exceptions accordingly.
	 * (3) Write the validated records to genre-based CSV files.
	 * (4) Create a manifest file for part 2.
	 * 
	 * @param part1_manifest The filename of the manifest file for part 1.
	 * @return The filename of the created manifest file for part 2.
	 * @throws MissingFieldsException If a movie record is missing fields.
	 * @throws ExcessFieldsException If a movie record has excess fields.
	 * @throws MissingQuotesException If a movie record has missing quotes.
	 * @throws BadYearException If a movie record has an invalid year.
	 * @throws BadDurationException If a movie record has an invalid duration.
	 * @throws BadScoreException If a movie record has an invalid score.
	 * @throws BadRatingException If a movie record has an invalid rating.
	 * @throws BadGenreException If a movie record has an invalid genre.
	 * @throws BadTitleException If a movie record has a missing title.
	 * @throws BadNameException If a movie record has missing actor names.
	 */
	public static String do_part1(String part1_manifest) throws MissingFieldsException, ExcessFieldsException, MissingQuotesException, BadYearException, BadDurationException, BadScoreException, BadRatingException, BadGenreException, BadTitleException, BadNameException {
		
		// Arrays to store lines for each genre
        String[] musicalArray = new String[500];
        String[] comedyArray = new String[500];
        String[] animationArray = new String[500];
        String[] adventureArray = new String[500];
        String[] dramaArray = new String[500];
        String[] crimeArray = new String[500];
        String[] biographyArray = new String[500];
        String[] horrorArray = new String[500];
        String[] actionArray = new String[500];
        String[] documentaryArray = new String[500];
        String[] fantasyArray = new String[500];
        String[] mysteryArray = new String[500];
        String[] sciFiArray = new String[500];
        String[] familyArray = new String[500];
        String[] romanceArray = new String[500];
        String[] thrillerArray = new String[500];
        String[] westernArray = new String[500];
        
        // Counters for each genre array
        int[] counters = new int[17];
		
        String[] badRecordArray = new String[300]; // Adjust the size as per your requirement
        int badRecordCounter = 0;

        
        
		 String[] movieFiles = readManifestFile(part1_manifest);
		 
	        //Printing the movieRecordFiles array for verification
	        //for (String file : movieFiles) {
	        //    System.out.println(file);
	        //}
	        
		
		
		for (int i = 0; i < movieFiles.length; i++) {
			// reads on line from the record csv file and stores the values in an array and then into a movie object.	
			String line; 
			int lineCount = 0;
			
			
			BufferedReader br = null;
			
			
			try {
								
				br = new BufferedReader(new FileReader(movieFiles[i]));
                

				while ((line = br.readLine()) != null) {
					
					
				    int numCommas = countCommas(line);
				    String[] data = line.split("(?<!\\\\),(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);   // using patterns to split the line where a comma is unless there the comma is inside double quotes
				    lineCount++;
				    
				    
				   
				boolean missingQuote = false;    
			    boolean syntaxError = false;
				boolean semanticError = false;

			    
				try {
					
					   boolean containsOddQuotes = containsOddDoubleQuotes(line);
			           if (containsOddQuotes) {
			        	    missingQuote = true;
					    	syntaxError = true;
					    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "missing quotes, syntax", movieFiles[i], lineCount);
					        throw new MissingQuotesException();
			           }
			            
					
						// 2. check if there is missing fields (syntax error)
						if (numCommas < 9 && !missingQuote) {
							syntaxError = true;
					    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "missing field(s), syntax", movieFiles[i], lineCount);
							throw new MissingFieldsException();
						}
						// 3. check if there is excess fields (syntax error)
						if (numCommas > 9 && !missingQuote) {
							syntaxError = true;
					    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "excess field(s), syntax", movieFiles[i], lineCount);
							throw new ExcessFieldsException();
						}
				} catch (MissingQuotesException e) {
					//System.out.println(e.getMessage());
				    //e.printStackTrace();
				}  catch (ExcessFieldsException e) {
					//System.out.println(e.getMessage());
					//e.printStackTrace();
				}catch (MissingFieldsException e) {
					//System.out.println(e.getMessage());
					//e.printStackTrace();
				}

				
				if (!syntaxError) {
					// write if statements so that if there are missing fields, you wont have an index array out of bounds exception.
					int year = 0;
					try {
						year = Integer.parseInt(data[0]);
					} catch (NumberFormatException e) {
						semanticError = true;
				    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "invalid year, sematic", movieFiles[i], lineCount);
				        //System.out.println("Invalid year format");

				    } 
					String title = data[1];
					
					int duration = 0;
					try {
					    duration = Integer.parseInt(data[2]);
					} catch (NumberFormatException e) {
						semanticError = true;
				    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "invalid duration, sematic", movieFiles[i], lineCount);
				        //System.out.println("Invalid duration format");
				    }
					String genres = data[3];
					String rating = data[4];
					
					double score = 0;
					try {
					score = Double.parseDouble(data[5]);
					} catch (NumberFormatException e) {
						semanticError = true;
				    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "invalid score, sematic", movieFiles[i], lineCount);
				        //System.out.println("Invalid score format");
					}
					
					String director = data[6];
					String actor1 = data[7];
					String actor2 = data[8];	
					String actor3 = data[9]; 

					
					
					// validate records and throw and catch exceptions 
						
					try {
						 
						 // don't check other data if syntax error.
													
							if (year < 1990 || year > 1999) {
								semanticError = true;
						    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "invalid year, sematic", movieFiles[i], lineCount);
								throw new BadYearException();
							}
							if (duration < 30 || duration > 300) {
								semanticError = true;
						    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "invalid duration, sematic", movieFiles[i], lineCount);
								throw new BadDurationException();
							}
							if (score > 10.0 || score < 0.0) {
								semanticError = true;
						    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "invalid score, sematic", movieFiles[i], lineCount);
								throw new BadScoreException();
							}
							if (!rating.trim().equals("PG") && !rating.trim().equals("Unrated")
									&& !rating.trim().equals("G") && !rating.trim().equals("R")
									&& !rating.trim().equals("PG-13") && !rating.trim().equals("NC-17")) {
								semanticError = true;
						    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "ivalid rating, sematic", movieFiles[i], lineCount);
								throw new BadRatingException();
							}
							if (!genres.equalsIgnoreCase("musical") && !genres.equalsIgnoreCase("comedy")
									&& !genres.equalsIgnoreCase("animation") && !genres.equalsIgnoreCase("adventure")
									&& !genres.equalsIgnoreCase("drama") && !genres.equalsIgnoreCase("crime")
									&& !genres.equalsIgnoreCase("biography") && !genres.equalsIgnoreCase("horror")
									&& !genres.equalsIgnoreCase("action") && !genres.equalsIgnoreCase("documentary")
									&& !genres.equalsIgnoreCase("fantasy") && !genres.equalsIgnoreCase("mystery")
									&& !genres.equalsIgnoreCase("sci-fi") && !genres.equalsIgnoreCase("family")
									&& !genres.equalsIgnoreCase("romance") && !genres.equalsIgnoreCase("thriller")
									&& !genres.equalsIgnoreCase("western")) {
								semanticError = true;
						    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "invalid genre, sematic", movieFiles[i], lineCount);
								throw new BadGenreException();
							}
							if (title == null || title.trim().isEmpty()) {
								semanticError = true;
						    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "missing title, sematic", movieFiles[i], lineCount);
								throw new BadTitleException();
							}
							if ( actor1.trim().isEmpty() ||  actor2.trim().isEmpty()
									||  actor3.trim().isEmpty()) {
								semanticError = true;
						    	badRecordArray[badRecordCounter++] = badMovieRecordFormat(line, "missing name(s), sematic", movieFiles[i], lineCount);
								throw new BadNameException();
							} 
				
				
				     
				    } catch (BadYearException e) {
						//System.out.println(e.getMessage());
						//e.printStackTrace();
					} catch (BadDurationException e) {
						//System.out.println(e.getMessage());
						//e.printStackTrace();
					} catch (BadScoreException e) {
						//System.out.println(e.getMessage());
						//e.printStackTrace();
					} catch (BadRatingException e) {
						//System.out.println(e.getMessage());
						//e.printStackTrace();
					} catch (BadGenreException e) {
						//System.out.println(e.getMessage());
						//e.printStackTrace();
					} catch (BadTitleException e) {
						//System.out.println(e.getMessage());
						//e.printStackTrace();
					} catch (BadNameException e) {
						//System.out.println(e.getMessage());
						//e.printStackTrace();
					}
					
					
					if (!syntaxError && !semanticError) {
						
						switch (genres.toLowerCase()) {
		                case "musical":
		                    musicalArray[counters[0]++] = line;
		                    break;
		                case "comedy":
		                    comedyArray[counters[1]++] = line;
		                    break;
		                case "animation":
		                    animationArray[counters[2]++] = line;
		                    break;
		                case "adventure":
		                    adventureArray[counters[3]++] = line;
		                    break;
		                case "drama":
		                    dramaArray[counters[4]++] = line;
		                    break;
		                case "crime":
		                    crimeArray[counters[5]++] = line;
		                    break;
		                case "biography":
		                    biographyArray[counters[6]++] = line;
		                    break;
		                case "horror":
		                    horrorArray[counters[7]++] = line;
		                    break;
		                case "action":
		                    actionArray[counters[8]++] = line;
		                    break;
		                case "documentary":
		                    documentaryArray[counters[9]++] = line;
		                    break;
		                case "fantasy":
		                    fantasyArray[counters[10]++] = line;
		                    break;
		                case "mystery":
		                    mysteryArray[counters[11]++] = line;
		                    break;
		                case "sci-fi":
		                    sciFiArray[counters[12]++] = line;
		                    break;
		                case "family":
		                    familyArray[counters[13]++] = line;
		                    break;
		                case "romance":
		                    romanceArray[counters[14]++] = line;
		                    break;
		                case "thriller":
		                    thrillerArray[counters[15]++] = line;
		                    break;
		                case "western":
		                    westernArray[counters[16]++] = line;
		                    break;
						
						}
						
						
					}
					
					
				}
				
			} // bracket for the while loop
			
			
			
				  

			} catch (FileNotFoundException e) {
				System.err.println("File not found: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Error reading file: " + e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("Invalid number format on line " + lineCount);
			} 

		
	         
		
		} // bracket for for loop
		
		
		writeToFiles(badRecordArray, "bad_movie_records.txt");
		
		
		String[] GenresCSVfileNames = {
	            "musical.csv",
	            "comedy.csv",
	            "animation.csv",
	            "adventure.csv",
	            "drama.csv",
	            "crime.csv",
	            "biography.csv",
	            "horror.csv",
	            "action.csv",
	            "documentary.csv",
	            "fantasy.csv",
	            "mystery.csv",
	            "sci-fi.csv",
	            "family.csv",
	            "western.csv",
	            "romance.csv",
	            "thriller.csv"
	        };
		CreateFiles(GenresCSVfileNames);
		
		
		writeToFiles(musicalArray,"musical.csv");
		writeToFiles(comedyArray,"comedy.csv");
		writeToFiles(animationArray,"animation.csv");
		writeToFiles(adventureArray,"adventure.csv");
		writeToFiles(dramaArray,"drama.csv");
		writeToFiles(crimeArray,"crime.csv");
		writeToFiles(biographyArray,"biography.csv");
		writeToFiles(horrorArray,"horror.csv");
		writeToFiles(actionArray,"action.csv");
		writeToFiles(documentaryArray,"documentary.csv");
		writeToFiles(fantasyArray,"fantasy.csv");
		writeToFiles(mysteryArray,"mystery.csv");
		writeToFiles(sciFiArray,"sci-fi.csv");
		writeToFiles(familyArray,"family.csv");
		writeToFiles(westernArray,"western.csv");
		writeToFiles(romanceArray,"romance.csv");
		writeToFiles(thrillerArray,"thriller.csv");

		return makePart2ManifestFile();
				
		
	}



	/**
	 * Reads the filenames from a manifest file and returns them as an array of strings.
	 *
	 * @param filename The filename of the manifest file to read.
	 * @return An array of strings containing the filenames read from the manifest file.
	 */
	public static String[] readManifestFile(String filename) {
        String[] lines = null;
        BufferedReader br = null;
        int lineCount = 0;

        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            // Count the number of lines in the file
            while (br.readLine() != null) {
                lineCount++;
            }
            // Re-initialize the BufferedReader to start from the beginning
            br.close();
            br = new BufferedReader(new FileReader(filename));
            lines = new String[lineCount];
            int index = 0;
            // Read each line and store it in the array
            while ((line = br.readLine()) != null) {
                lines[index++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // You may handle this exception according to your requirements
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    // You may handle this exception according to your requirements
                }
            }
        }

        return lines;
    }
	
	
	
	/**
	 * Formats a bad movie record with error details.
	 *
	 * @param line The bad movie record.
	 * @param errorType The type of error encountered.
	 * @param inputFileName The name of the input file containing the bad record.
	 * @param lineCount The line number where the bad record occurred.
	 * @return A formatted string containing the error details and bad movie record.
	 */
		public static String badMovieRecordFormat(String line, String errorType, String inputFileName, int lineCount) {
			
			return "(a) Type of error: "+ errorType +"\n"
					+ "(b) "+ line +"\n"
					+ "(c) Bad record taken from: " + inputFileName +"\n"
					+ "(d) The record is on line: "+lineCount;
		}
	
	
	
	
	
		/**
		 * Counts the number of commas in a string, excluding those within double quotes.
		 *
		 * @param line The record to count commas in.
		 * @return The number of commas in the string.
		 */
		public static int countCommas(String line) {
		    int count = 0;
		    boolean insideQuotes = false;

		    for (char c : line.toCharArray()) {
		        if (c == '"') {
		            insideQuotes = !insideQuotes; // Toggle insideQuotes when encountering a quote
		        } else if (c == ',' && !insideQuotes) {
		            count++; // Increment count if the comma is outside quotes
		        }
		    }

		    return count;
		}
	
	
	
		/**
		 * Checks if a string (record) contains an odd number of double quotes.
		 *
		 * @param str The record to check.
		 * @return True if the string contains an odd number of double quotes, false otherwise.
		 */
		public static boolean containsOddDoubleQuotes(String str) {

	        int count = 0;
	        for (int i = 0; i < str.length(); i++) {
	            if (str.charAt(i) == '"') {
	                count++;
	            }
	        }
	        return count % 2 != 0;
	    }
	
		/**
		 * Creates files with the given filenames.
		 *
		 * @param fileNames An array of filenames to create.
		 */
		private static void CreateFiles(String[] fileNames) {
		        

		        try {
		            for (String fileName : fileNames) {
		                File file = new File(fileName);
		                if (file.createNewFile()) {
		                    //System.out.println("File created: " + file.getName());
		                } else {
		                    //System.out.println("File already exists: " + file.getName());
		                }
		            }
		        } catch (IOException e) {
		            System.out.println("An error occurred.");
		            e.printStackTrace();
		        }
		    }
		
		
		/**
		 * Writes an array of strings to a file.
		 *
		 * @param array    The array of strings to write.
		 * @param fileName The name of the file to write to.
		 */
       public static void writeToFiles(String[] array, String fileName) {
		
    	   try (PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))) {
               for (String line : array) {
                   if (line == null) {
                       break; // Exit loop if null is encountered
                   }
                   writer.println(line); // Write each string to the file followed by a newline
               }
               //System.out.println("File writing completed successfully.");
           } catch (IOException e) {
               System.err.println("Error writing to file: " + e.getMessage());
           }

       }
       
       
       /**
        * Creates a manifest file for part 2 of the program, listing the serialized files for each genre.
        * 
        * @return The filename of the created manifest file for part 2.
        */
	public static String makePart2ManifestFile() {
		String fileName = "part2_manifest.txt";

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName))) {  
        	pw.println("musical.csv");
            pw.println("comedy.csv");
            pw.println("animation.csv");
            pw.println("adventure.csv");
            pw.println("drama.csv");
            pw.println("crime.csv");
            pw.println("biography.csv");
            pw.println("horror.csv");
            pw.println("action.csv");
            pw.println("documentary.csv");
            pw.println("fantasy.csv");
            pw.println("mystery.csv");
            pw.println("sci-fi.csv");
            pw.println("family.csv");
            pw.println("western.csv");
            pw.println("romance.csv");
            pw.println("thriller.csv");
            
            
            pw.close();
           // System.out.println("File names successfully written to " + fileName);
        } catch (Exception e) {
            System.err.println("Error writing record to file: " + e.getMessage());
        }
		
        return fileName;
		
		
	}
	
	
	
	
	
	/**
	 * Performs the tasks outlined in part 2 of the program's instructions:
	 * (1) Reads the filenames from the part 2 manifest file.
	 * (2) Loads movie records from each genre CSV file into movie objects.
	 * (3) Serializes the movie objects and writes them to corresponding binary files.
	 * (4) Creates a manifest file for part 3.
	 * 
	 * @param part2_manifest The filename of the manifest file for part 2.
	 * @return The filename of the created manifest file for part 3.
	 * @throws IOException If an I/O error occurs while reading or writing files.
	 */
	public static String do_part2(String part2_manifest) throws IOException {
		
	    String[] genresFiles = readManifestFile(part2_manifest);
	    
	    
	    // get name of genre file
	    
	    for (int i = 0; i < genresFiles.length; i++) {
	    	
	        Movie[] genreMoviesObjects = loadMovies(genresFiles[i]); // returns an array of movie objects 
	        
	        String[] part = genresFiles[i].split("\\."); // Escaping the dot because split() takes a regex
	        String genreName = part[0];
	        String serFileName = genreName+".ser";
	        
	        serializeMovies(genreMoviesObjects, serFileName);

	    }
	  
	    

		return   createPart3Manifest();
		
	}

	/**
	 * Creates a manifest file for part 3 of the program, listing the serialized files for each genre.
	 * 
	 * @return The filename of the created manifest file for part 3.
	 */
	public static String createPart3Manifest() {
		String fileName = "part3_manifest.txt";
		
		try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName))) {  
		    pw.println("musical.ser");
		    pw.println("comedy.ser");
		    pw.println("animation.ser");
		    pw.println("adventure.ser");
		    pw.println("drama.ser");
		    pw.println("crime.ser");
		    pw.println("biography.ser");
		    pw.println("horror.ser");
		    pw.println("action.ser");
		    pw.println("documentary.ser");
		    pw.println("fantasy.ser");
		    pw.println("mystery.ser");
		    pw.println("sci-fi.ser");
		    pw.println("family.ser");
		    pw.println("western.ser");
		    pw.println("romance.ser");
		    pw.println("thriller.ser");
		    
		    pw.close();
		    //System.out.println("File names successfully written to " + fileName);
		} catch (Exception e) {
		    System.err.println("Error writing record to file: " + e.getMessage());
		}
		
		return fileName;
	}
	

	/**
	 * Loads movie records from a CSV file into an array of Movie objects.
	 * 
	 * @param csvFileName The filename of the CSV file containing movie records.
	 * @return An array of Movie objects containing the loaded movie records.
	 * @throws IOException If an I/O error occurs while reading the CSV file.
	 */
	private static Movie[] loadMovies(String csvFileName) throws IOException {
		int numMovies = countLines(csvFileName);
		
        // Load movie records from CSV into an array
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            
            Movie[] movies = new Movie[numMovies];
            String line;
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                // Extracting data into variables
                int year = Integer.parseInt(data[0]);
                String title = data[1];
                int duration = Integer.parseInt(data[2]);
                String genres = data[3];
                String rating = data[4];
                double score = Double.parseDouble(data[5]);
                String director = data[6];
                String actor1 = data[7];
                String actor2 = data[8];
                String actor3 = data[9];
                
                // Create and populate movie object
                Movie movie = new Movie(year, title, duration, genres, rating, score, director, actor1, actor2, actor3);
                movies[index++] = movie;
                
            }
            return movies;
        } catch (IOException e) {
            e.printStackTrace();
            return new Movie[0]; // Return an empty array in case of an error
        }
    }
	
	/**
	 * Counts the number of lines in a file.
	 * 
	 * @param filename The filename of the file to count lines in.
	 * @return The number of lines in the file.
	 * @throws IOException If an I/O error occurs while reading the file.
	 */
	private static int countLines(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int lines = 0;
            while (reader.readLine() != null) lines++;
            return lines;
        }
    }
	
	/**
	 * Serializes an array of Movie objects and writes them to a binary file.
	 * 
	 * @param movies          An array of Movie objects to serialize.
	 * @param binaryFileName  The filename of the binary file to write the serialized objects to.
	 */
	private static void serializeMovies(Movie[] movies, String binaryFileName) {
        // Serialize the movie array to a binary file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binaryFileName))) {
            for (Movie movie : movies) {
                oos.writeObject(movie);
            }
            //System.out.println("Serialization complete for "+binaryFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Performs the tasks outlined in part 3 of the program's instructions:
	 * (1) Reads the filenames from the part 3 manifest file.
	 * (2) Deserializes movie objects from each serialized genre file.
	 * (3) Constructs a 2D array to store all deserialized movie objects.
	 * 
	 * @param part3_manifest The filename of the manifest file for part 3.
	 * @return A 2D array containing deserialized movie objects organized by genre.
	 * @throws IOException If an I/O error occurs while reading the manifest file or deserializing objects.
	 */
	public static Movie[][] do_part3(String part3_manifest) throws IOException{
		
		 String[] serGenresFiles = readManifestFile(part3_manifest);
		
		int numMovieArrays = countLines(part3_manifest);
		
		//int maxNumMovies = findLargestLength(serGenresFiles); can't use because its binary file 
		Movie[][] allMovies = new Movie[numMovieArrays][200];  // assume length of 300 for now.
		
		// first for loop iterates over the ser genre files
		for (int i=0; i<serGenresFiles.length; i++) {
			
			Movie[] deserializedMovies = deserializeMovies(serGenresFiles[i]);
			
			if (deserializedMovies != null) {
				// second for loop goes through each serialized object in the ser genre file. it deserializes it and adds it to the second dimension of the array.
				for (int j = 0; j < deserializedMovies.length; j++) {

					allMovies[i][j] = deserializedMovies[j];

				} 
			}
			
		}
		
		
		 return allMovies;
		
	}
	
	
	/**
	 * Deserializes movie objects from a binary file and returns an array containing the deserialized objects.
	 * 
	 * @param fileName The filename of the binary file containing serialized movie objects.
	 * @return An array of Movie objects containing the deserialized movie records.
	 */
	public static Movie[] deserializeMovies(String fileName) {
		 Movie[] movies = null;
	        try (FileInputStream fileIn = new FileInputStream(fileName);
	             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
	        	
	        	// Check if the file is empty
	            if (fileIn.available() == 0) {
	                //System.err.println("File is empty.");
	                return new Movie[0]; // Return an empty array
	            }

	            // Create an initial capacity for the array
	            int initialCapacity = 200;
	            movies = new Movie[initialCapacity];
	            int index = 0;

	            // Deserialize the first movie separately
	            try {
	                Movie firstMovie = (Movie) objectIn.readObject();
	                if (firstMovie != null) {
	                    movies[index++] = firstMovie;
	                }
	            } catch (ClassNotFoundException e) {
	                System.err.println("Error reading first movie: " + e.getMessage());
	            }

	            // Deserialize each subsequent movie until encountering a null object or end of file
	            while (true) {
	                try {
	                    Movie movie = (Movie) objectIn.readObject();
	                    // Break if encountering a null object
	                    if (movie == null) {
	                        break;
	                    }
	                    // If the array is full, break the loop
	                    if (index == movies.length) {
	                        System.out.println("Reached maximum capacity of movies array.");
	                        break;
	                    }
	                    movies[index++] = movie;
	                } catch (EOFException e) {
	                    // Reached end of file
	                    break;
	                } catch (ClassNotFoundException e) {
	                    System.err.println("Error reading movie: " + e.getMessage());
	                }
	            }
	        } catch (FileNotFoundException e) {
	            System.err.println("File not found: " + e.getMessage());
	        } catch (IOException e) {
	            System.err.println("Error reading from file: " + e.getMessage());
	        }
	        return movies;
	    }

	
	
		
		
		
	}
	
	