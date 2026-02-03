package org.obaro.portfolio.musicshow;

/**
 * Represents a musical artist.
 * <p>
 * An Artist may perform at one or more music shows and is defined
 * by basic identifying information such as name, genre, and hometown.
 * </p>
 */
public class Artist {

    /** Unique identifier for the artist. */
    private int id;

    /** The artist's display or stage name. */
    private String name;

    /** Primary musical genre of the artist. */
    private String genre;

    /** City or region the artist is based in. */
    private String hometown;

    /**
     * No-argument constructor required for frameworks
     * and JavaBean compliance.
     */
    public Artist() {
    }

    /**
     * Constructs an Artist with all fields initialized.
     *
     * @param id       the unique artist identifier
     * @param name     the artist's name
     * @param genre    the artist's musical genre
     * @param hometown the artist's hometown
     */
    public Artist(int id, String name, String genre, String hometown) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.hometown = hometown;
    }

    /**
     * Returns the artist identifier.
     *
     * @return the artist id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the artist identifier.
     *
     * @param id the artist id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the artist's name.
     *
     * @return the artist name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the artist's name.
     *
     * @param name the artist name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the artist's musical genre.
     *
     * @return the artist genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the artist's musical genre.
     *
     * @param genre the artist genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Returns the artist's hometown.
     *
     * @return the artist hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * Sets the artist's hometown.
     *
     * @param hometown the artist hometown
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
