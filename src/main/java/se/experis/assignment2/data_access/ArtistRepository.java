package se.experis.assignment2.data_access;

import se.experis.assignment2.models.Artist;
import se.experis.assignment2.models.Genre;
import se.experis.assignment2.models.Track;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Data access class for extracting information about artists, tracks and genres from the database
 */
public class ArtistRepository {
    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    /**
     * Method used to extract five random artists from the database.
     * @return : ArrayList
     */
    public ArrayList<Artist> getFiveRandomArtists() {
        ArrayList<Artist> list = new ArrayList<>();
        ArrayList<String> names = this.get5Random("SELECT Name FROM Artist ORDER BY random() LIMIT 5;");
        for (String name : names) {
            list.add(new Artist(name));
        }
        return list;
    }

    /**
     * Method used to extract five random tracks from the database.
     * @return : ArrayList
     */
    public ArrayList<Track> getFiveRandomTracks() {
        ArrayList<Track> list = new ArrayList<>();
        ArrayList<String> names = this.get5Random("SELECT Name FROM Track ORDER BY random() LIMIT 5");
        for (String name : names) {
            list.add(new Track(name));
        }
        return list;
    }

    /**
     * Method used to extract five random genres from the database.
     * @return : ArrayList
     */
    public ArrayList<Genre> getFiveRandomGenres() {
        ArrayList<Genre> list = new ArrayList<>();
        ArrayList<String> names = this.get5Random("SELECT Name FROM Genre ORDER BY random() LIMIT 5");
        for (String name : names) {
            list.add(new Genre(name));
        }
        return list;
    }

    /**
     * Private method which takes in a query, performs the query and returns a list of the result.
     * @param query : String
     * @return : ArrayList
     */
    private ArrayList<String> get5Random(String query) {
        ArrayList<String> names = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                names.add(resultSet.getString("Name"));
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }

        return names;
    }

    /**
     * Method for extracting tracks based which matches a search term from the Thymeleaf view.
     * @param searchterm : String
     * @return : ArrayList
     */
    public ArrayList<Track> searchForTrack(String searchterm) {
        ArrayList<Track> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Track.Name as 'trackName', A.Title as 'albumTitle', G.Name as 'genreName', A2.Name as 'artistName' " +
                    "FROM Track " +
                    "JOIN Genre G on Track.GenreId = G.GenreId " +
                    "JOIN Album A on Track.AlbumId = A.AlbumId " +
                    "JOIN Artist A2 on A2.ArtistId = A.ArtistId " +
                    "WHERE Track.Name LIKE ?");

            preparedStatement.setString(1, "%" + searchterm + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Track track = new Track(
                        resultSet.getString("trackName"),
                        resultSet.getString("albumTitle"),
                        resultSet.getString("artistName"),
                        resultSet.getString("genreName"));
                list.add(track);
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing the connection");
                System.out.println(ex.toString());
            }
        }
        return list;
    }
}
