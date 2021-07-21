package se.experis.assignment2.models;

public class Track {
    private String name;
    private String artist;
    private String album;
    private String genre;

    public Track(String name) {
        this.name = name;
    }

    public Track(String name, String album, String artist, String genre) {
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
