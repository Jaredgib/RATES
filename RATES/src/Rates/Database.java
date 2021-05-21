package Rates;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Database class contains methods that perform specific actions on the database
 * e.g, get all usernames/passwords to compare login, get all topics for user
 */
public class Database {
    // To hold Reviews based on the ID
    TreeMap<String, Review> reviews = new TreeMap<>();

    /**
     * Method that connects to RATES database
     * Reads all account table data
     * Compares login username/password fields with database entries
     * Returns false if none found, or true if match found
     *
     * @param username String result from login text field
     * @param password String result from password text field
     * @return Boolean: True if match, false if none found
     */
    public static boolean authLogin(String username, String password) {
        Connection conn = null;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");


            // Prepare SQL query, all entries in accounts table
            String query = "select * from accounts";
            Statement stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through the result set and do comparison on login attempt
            while (rs.next()) {
                String user = rs.getString("username");
                String pword = rs.getString("password");

                if ((username.equals(user)) && (password.equals(pword))) {
                    return true;    // Match found! Log user in
                }
            }

            // Catch errors and finally close the database connection
        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Conn Closed!");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return false;   // No match found
    }

    /**
     * Method that adds a user to the database
     * Reads the newly added user from the database and prints to console
     *
     * @param username Account username to be entered
     * @param password Account password to be entered
     * @return Boolean, true if successful, false if not
     */
    public static boolean addUser(String username, String password) {
        Connection conn = null;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");

            // Create prepared statement and execute
            String query = "insert into accounts values (?, ?)";
            PreparedStatement prepState = conn.prepareStatement(query);
            prepState.setString(1, username);
            prepState.setString(2, password);
            prepState.executeUpdate();

            // Prepare SQL query
            query = "select * from accounts where username = '" + username + "'";
            Statement stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through the result set
            while (rs.next()) {
                String user = rs.getString("username");
                String pword = rs.getString("password");

                System.out.println("Successfully Added");
                System.out.println(user + " | " + pword);
            }
            return true;

            // Catch errors and finally close the database connection
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Conn Closed!");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Queries the database to create a list of all topics input username is enrolled in
     *
     * @param username Username of user being queried
     * @return ArrayList containing Strings entries of all topics taken by username
     */
    public static ArrayList<String> getTopics(String username) {
        ArrayList<String> topicList = new ArrayList<>();
        Connection conn = null;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");

            // Prepare SQL query
            String query = "select * from usertopics where username = '" + username + "'";
            Statement stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through the result set
            while (rs.next()) {
                String topicID = rs.getString("topic_id");
                String user = rs.getString("username");

                topicList.add(topicID);

                System.out.println(topicID + " | " + user);
            }
            return topicList;

            // Catch errors and finally close the database connection
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Conn Closed!");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
