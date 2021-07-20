package se.experis.assignment2.data_access;

import se.experis.assignment2.models.Artist;
import se.experis.assignment2.models.Customer;
import se.experis.assignment2.models.Genre;
import se.experis.assignment2.models.Track;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class ThymeleafRepository {
    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;


    public ArrayList<Artist> getFiveRandomArtists() {
        ArrayList<Artist> list = new ArrayList<>();
        Random random = new Random();

        try {
            conn = DriverManager.getConnection(URL);

            for (int i = 0; i < 5; i++) {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT Name FROM Artist WHERE ArtistId = ?");

                preparedStatement.setInt(1, random.nextInt(275) + 1);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    list.add(new Artist(resultSet.getString("Name")));
                }
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
    public ArrayList<Track> getFiveRandomTracks() {
        ArrayList<Track> list = new ArrayList<>();
        Random random = new Random();

        try {
            conn = DriverManager.getConnection(URL);

            for (int i = 0; i < 5; i++) {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT Name FROM Track WHERE TrackId = ?");

                preparedStatement.setInt(1, random.nextInt(3503) + 1);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    list.add(new Track(resultSet.getString("Name")));
                }
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
    public ArrayList<Genre> getFiveRandomGenres() {
        ArrayList<Genre> list = new ArrayList<>();
        Random random = new Random();

        try {
            conn = DriverManager.getConnection(URL);

            for (int i = 0; i < 5; i++) {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT Name FROM Genre WHERE GenreId = ?");

                preparedStatement.setInt(1, random.nextInt(25) + 1);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    list.add(new Genre(resultSet.getString("Name")));
                }
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
