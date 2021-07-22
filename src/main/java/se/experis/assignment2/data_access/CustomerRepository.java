package se.experis.assignment2.data_access;

import se.experis.assignment2.models.Customer;
import se.experis.assignment2.models.CustomerCountry;
import se.experis.assignment2.models.CustomerGenre;
import se.experis.assignment2.models.CustomerSpender;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data access class for API requests
 */
public class CustomerRepository {
    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;
    private ArrayList<Customer> list = new ArrayList<>();

    /**
     * Retrieve all the Customers in database.
     * @return : ArrayList
     */
    public ArrayList<Customer> getAllCustomers() {
        list.clear();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email from Customer");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"));

                list.add(customer);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return list;
    }

    /**
     * Retrieves the Customer which matches a given ID.
     * @param Id : String
     * @return : Customer
     */
    public Customer getSpecificCustomer(String Id) {
        try {
            Connection conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email from Customer where CustomerId = ?");

            preparedStatement.setString(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            Customer customer = new Customer(
                    resultSet.getInt("CustomerId"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Country"),
                    resultSet.getString("PostalCode"),
                    resultSet.getString("Phone"),
                    resultSet.getString("Email"));
            return customer;

        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return null;
    }

    /**
     * Retrieves all Customers who's name matches a given search term.
     * @param name : String
     * @return : ArrayList
     */
    public ArrayList<Customer> getCustomerByName(String name) {
        list.clear();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email from Customer where FirstName like ? or LastName like ?");
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"));

                list.add(customer);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return list;
    }

    /**
     * Retrieves a page of Customers based on a given limit and offset.
     * @param limit : Integer
     * @param offset : Integer
     * @return : ArrayList
     */
    public ArrayList<Customer> getSubsetOfCustomers(int limit, int offset) {
        list.clear();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email from Customer limit ? offset ?");
            preparedStatement.setInt(1,limit);
            preparedStatement.setInt(2,offset);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"));

                list.add(customer);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return list;
    }

    /**
     * Adds a Customer to the database with a POST method.
     * @param customer : Customer
     * @return : boolean
     */
    public Boolean addCustomer(Customer customer) {
        boolean success = false;
        try{
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO customer(CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2,customer.getFirstName());
            preparedStatement.setString(3,customer.getLastName());
            preparedStatement.setString(4,customer.getCountry());
            preparedStatement.setString(5,customer.getPostalCode());
            preparedStatement.setString(6,customer.getPhoneNumber());
            preparedStatement.setString(7,customer.getMail());

            preparedStatement.executeUpdate();
            success = true;
        }
        catch (Exception exception){
            System.out.println("Something went wrong...");
            System.out.println(exception.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return success;
    }

    /**
     * Update an existing Customer in the database, based on a given id.
     * @param customer : Customer
     * @return : boolean
     */
    public Boolean updateCustomer(Customer customer) {
        boolean success = false;
        try{
            conn = DriverManager.getConnection(URL);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("update Customer set FirstName = ?, LastName = ?, Country = ?, PostalCode = ?, Phone = ?, Email = ? where CustomerId = ?");
            preparedStatement.setString(1,customer.getFirstName());
            preparedStatement.setString(2,customer.getLastName());
            preparedStatement.setString(3,customer.getCountry());
            preparedStatement.setString(4,customer.getPostalCode());
            preparedStatement.setString(5,customer.getPhoneNumber());
            preparedStatement.setString(6,customer.getMail());
            preparedStatement.setInt(7,customer.getId());

            preparedStatement.executeUpdate();
            success = true;
        }
        catch (Exception exception){
            System.out.println("Something went wrong...");
            System.out.println(exception.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return success;
    }

    /**
     * Returns a List with the number of Customers for each country
     * @return : ArrayList
     */
    public ArrayList<CustomerCountry> getCustomersPerCountry() {
        ArrayList<CustomerCountry> customerCountries = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Country, COUNT(Country) AS Number_of_Customers FROM Customer GROUP BY Country ORDER BY COUNT(Country) DESC;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerCountry customerCountry = new CustomerCountry(
                        resultSet.getString("Country"),
                        resultSet.getInt("Number_of_Customers"));

                customerCountries.add(customerCountry);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return customerCountries;
    }

    /**
     * Retrieves a List of Customers sorted based on their spending, from descending order.
     * @return : ArrayList
     */
    public ArrayList<CustomerSpender> getHighestCustomerSpenders() {
        ArrayList<CustomerSpender> customerSpenders = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT Customer.CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email, Sum(Total) " +
                    "as TotalPurchases " +
                    "from Customer " +
                    "JOIN Invoice I on Customer.CustomerId = I.CustomerId " +
                    "GROUP BY Customer.CustomerId " +
                    "ORDER BY TotalPurchases " +
                    "DESC;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerSpender customerSpender = new CustomerSpender(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        resultSet.getDouble("TotalPurchases"));

                customerSpenders.add(customerSpender);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return customerSpenders;
    }

    /**
     * Retrieves a Customer's most popular genre based on their purchases.
     * @param id : Integer
     * @return : ArrayList
     */
    public CustomerGenre getPopularGenreByCustomer(int id) {
        CustomerGenre customerGenre = null;

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT Customer.CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email, G.Name " +
                    "FROM Customer " +
                    "JOIN Invoice I on Customer.CustomerId = I.CustomerId " +
                    "JOIN InvoiceLine IL on I.InvoiceId = IL.InvoiceId " +
                    "JOIN Track T on T.TrackId = IL.TrackId " +
                    "JOIN Genre G on G.GenreId = T.GenreId " +
                    "GROUP BY G.GenreId, Customer.CustomerId " +
                    "HAVING I.CustomerId = ? " +
                        "AND Count(G.GenreId) = ( " +
                            "SELECT MAX(A.CNT) " +
                            "FROM (SELECT COUNT(G.GenreId) AS CNT " +
                            "FROM Customer " +
                            "JOIN Invoice I on Customer.CustomerId = I.CustomerId " +
                            "JOIN InvoiceLine IL on I.InvoiceId = IL.InvoiceId " +
                            "JOIN Track T on T.TrackId = IL.TrackId " +
                            "JOIN Genre G on G.GenreId = T.GenreId " +
                            "WHERE Customer.CustomerId = ? " +
                            "GROUP BY G.GenreId, Customer.CustomerId) AS A) ");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<String> genres = new ArrayList<>();
            while (resultSet.next()) {
                genres.add(resultSet.getString("Name"));

                customerGenre = new CustomerGenre(
                        resultSet.getInt("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email"),
                        genres);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            ConnectionHelper.closeDatabaseConnection(conn);
        }
        return customerGenre;
    }
}
