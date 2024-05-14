import java.io.Serializable;

/**
 * The Movie class represents a movie with various attributes such as year, title, duration, genres, rating, score,
 * director, and actors.
 * 
 * Nicole Koran (40281430) and Justin Tran (40281429)
 * COMP249
 * Assignment #2
 * Date Due: Mar 27, 2024
 * 
 * @author Nicole Koran
 * @author Justin Tran
 * @version 1.0
 */
public class Movie implements Serializable {

	/**
	 * Represents the release year of the movie.
	 */
	private int year;

	/**
	 * Represents the title of the movie.
	 */
	private String title;

	/**
	 * Represents the duration of the movie in minutes.
	 */
	private int duration;

	/**
	 * Represents the genres of the movie.
	 */
	private String genres;

	/**
	 * Represents the rating of the movie.
	 */
	private String rating;

	/**
	 * Represents the score of the movie.
	 */
	private double score;

	/**
	 * Represents the director of the movie.
	 */
	private String director;

	/**
	 * Represents the first actor of the movie.
	 */
	private String actor1;

	/**
	 * Represents the second actor of the movie.
	 */
	private String actor2;

	/**
	 * Represents the third actor of the movie.
	 */
	private String actor3;
	
	
    /**
     * Constructs a Movie object with the specified attributes.
     * 
     * @param year     the release year of the movie
     * @param title    the title of the movie
     * @param duration the duration of the movie in minutes
     * @param genres   the genres of the movie
     * @param rating   the rating of the movie
     * @param score    the score of the movie
     * @param director the director of the movie
     * @param actor1   the first actor of the movie
     * @param actor2   the second actor of the movie
     * @param actor3   the third actor of the movie
     */
    public Movie(int year, String title, int duration, String genres, String rating, double score,
            String director, String actor1, String actor2, String actor3) {
        this.year = year;
        this.title = title;
        this.duration = duration;
        this.genres = genres;
        this.rating = rating;
        this.score = score;
        this.director = director;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    // Accessor methods

    /**
     * Gets the release year of the movie.
     * 
     * @return the year of the movie
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the title of the movie.
     * 
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the duration of the movie in minutes.
     * 
     * @return the duration of the movie
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the genres of the movie.
     * 
     * @return the genres of the movie
     */
    public String getGenres() {
        return genres;
    }

    /**
     * Gets the rating of the movie.
     * 
     * @return the rating of the movie
     */
    public String getRating() {
        return rating;
    }

    /**
     * Gets the score of the movie.
     * 
     * @return the score of the movie
     */
    public double getScore() {
        return score;
    }

    /**
     * Gets the director of the movie.
     * 
     * @return the director of the movie
     */
    public String getDirector() {
        return director;
    }

    /**
     * Gets the first actor of the movie.
     * 
     * @return the first actor of the movie
     */
    public String getActor1() {
        return actor1;
    }

    /**
     * Gets the second actor of the movie.
     * 
     * @return the second actor of the movie
     */
    public String getActor2() {
        return actor2;
    }

    /**
     * Gets the third actor of the movie.
     * 
     * @return the third actor of the movie
     */
    public String getActor3() {
        return actor3;
    }

    // Mutator methods 
    
    /**
     * Sets the release year of the movie.
     * 
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the title of the movie.
     * 
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the duration of the movie in minutes.
     * 
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Sets the genres of the movie.
     * 
     * @param genres the genres to set
     */
    public void setGenres(String genres) {
        this.genres = genres;
    }

    /**
     * Sets the rating of the movie.
     * 
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Sets the score of the movie.
     * 
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Sets the director of the movie.
     * 
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Sets the first actor of the movie.
     * 
     * @param actor1 the first actor to set
     */
    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    /**
     * Sets the second actor of the movie.
     * 
     * @param actor2 the second actor to set
     */
    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    /**
     * Sets the third actor of the movie.
     * 
     * @param actor3 the third actor to set
     */
    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    // Override equals method

    /**
     * Compares this Movie object to the specified object. The result is true if and only if the argument is not null
     * and is a Movie object that represents the same year, duration, score, title, genres, rating, director, and
     * actors.
     * 
     * @param obj the object to compare this Movie against
     * @return true if the given object represents a Movie equivalent to this movie, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return year == movie.year && duration == movie.duration && Double.compare(movie.score, score) == 0 &&
                title.equals(movie.title) && genres.equals(movie.genres) && rating.equals(movie.rating) &&
                director.equals(movie.director) && actor1.equals(movie.actor1) && actor2.equals(movie.actor2) &&
                actor3.equals(movie.actor3);
    }

    
    /**
    * Returns a string representation of the Movie object. The string representation consists of the movie's attributes
    * such as year, title, duration, genres, rating, score, director, and actors.
    * 
    * @return a string representation of the Movie object
    */

    // Override toString method
    @Override
    public String toString() {
        return "Movie{" +
                "year=" + year +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", genres='" + genres + '\'' +
                ", rating='" + rating + '\'' +
                ", score=" + score +
                ", director='" + director + '\'' +
                ", actor1='" + actor1 + '\'' +
                ", actor2='" + actor2 + '\'' +
                ", actor3='" + actor3 + '\'' +
                '}';
    }
}

