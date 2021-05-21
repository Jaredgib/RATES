package Rates;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Database class contains methods that perform specific actions on the database
 * e.g, get all usernames/passwords to compare login, get all topics for user
 */
public class Database {

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
     * Gets profile image from database
     *
     * @param username Name of user
     * @return String of the photo location in database
     */
    public static String getProfilePic(String username) {
        Connection conn = null;
        String result;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");


            // Prepare SQL query, all entries in accounts table
            String query = "select * from accounts where username = '" + username + "'";
            Statement stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            ResultSet rs = stmt.executeQuery(query);

            result = rs.getString("picture");

            return result;

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

    /**
     * Gets topic name associated with topic_id in database
     *
     * @param topicID ID of the topic
     * @return String of the name of the topic from database
     */
    public static String getTopicName(String topicID) {
        Connection conn = null;
        String result;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");


            // Prepare SQL query, all entries in accounts table
            String query = "select * from topics where topic_id = '" + topicID + "'";
            Statement stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            ResultSet rs = stmt.executeQuery(query);

            result = rs.getString("topic_name");

            return result;

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
            String query = "insert into accounts values (?, ?, ?)";
            PreparedStatement prepState = conn.prepareStatement(query);
            prepState.setString(1, username);
            prepState.setString(2, password);
            prepState.setString(3, "./files/placeholder.png");
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
                String pic = rs.getString("picture");

                System.out.println("Successfully Added");
                System.out.println(user + " | " + pword + " | " + pic);
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
     * Gets a Arraylist of all topics in the database
     *
     * @return
     */
    public static ArrayList<String> getTopics() {
        ArrayList<String> topicList = new ArrayList<>();
        Connection conn = null;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");

            // Prepare SQL query
            String query = "select * from topics";
            Statement stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through the result set
            while (rs.next()) {
                String topicID = rs.getString("topic_id");
                String topicName = rs.getString("topic_name");

                topicList.add(topicID);

                System.out.println(topicID + " | " + topicName);
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

    /**
     * Attempts to add a user to a topic so it allows them to create a review for that topic
     * Returns false if they are already enrolled, or something else happens
     *
     * @param topicID
     * @param username
     * @return
     */
    public static boolean addUserTopic(String topicID, String username) {
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

            while (rs.next()) {
                if (rs.getString("topic_id").equals(topicID)) {
                    return false;
                }
            }

            // Create prepared statement and execute
            String preparedQuery = "insert into usertopics (topic_id, username) values (?, ?)";
            PreparedStatement prepState = conn.prepareStatement(preparedQuery);
            prepState.setString(1, topicID);
            prepState.setString(2, username);
            prepState.executeUpdate();


            // Prepare SQL query
            query = "select * from usertopics where username = '" + username + "'";
            stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.print("" + rs.getInt("topic_id") + " | ");
                System.out.print(rs.getString("username") + " | ");
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
     * Inserts a review into the database
     *
     * @param review
     * @return
     */
    public static boolean insertReview(Review review) {
        Connection conn = null;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");


            // Create prepared statement and execute
            String preparedQuery = "insert into review (topic_id, username, review_text, review_date, anonymous, file_up, review_type)" +
                    " values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepState = conn.prepareStatement(preparedQuery);
            prepState.setString(1, review.getTopic_id());
            prepState.setString(2, review.getUserName());
            prepState.setString(3, review.getReviewText());
            prepState.setString(4, review.getDate());
            prepState.setBoolean(5, review.isAnonymous());
            prepState.setString(6, review.getFileUpload());
            prepState.setString(7, review.getReview_type());
            prepState.executeUpdate();


            // Prepare SQL query
            String query = "select * from review where review_date = '" + review.getDate() + "'";
            Statement stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            ResultSet rs = stmt.executeQuery(query);

            System.out.print("" + rs.getInt("review_id") + " | ");
            System.out.print(rs.getString("topic_id") + " | ");
            System.out.print(rs.getString("username") + " | ");
            System.out.print(rs.getString("review_text") + " | ");
            System.out.print(rs.getString("review_date") + " | ");
            System.out.print("" + rs.getBoolean("anonymous") + " | ");
            System.out.print(rs.getString("file_up") + " | ");
            System.out.println(rs.getString("review_type"));

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
     * Gets an ArrayList of all reviews a user has made on their account
     *
     * @param username
     * @return
     */
    public static ArrayList<Review> getReviews(String username) {
        Connection conn = null;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");

            // Prepare SQL query
            String query = "select * from review where username = '" + username + "'";
            Statement stmt = conn.createStatement();

            // Result set of all the rows/tables obtained from query
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Review> results = new ArrayList<>();

            while (rs.next()) {
                Review review = new Review();

                review.setReview_id("" + rs.getInt("review_id"));
                review.setTopic_id(rs.getString("topic_id"));
                review.setUserName(rs.getString("username"));
                review.setReviewText(rs.getString("review_text"));
                review.setDate(rs.getString("review_date"));
                review.setAnonymous(rs.getBoolean("anonymous"));
                review.setFileUpload(rs.getString("file_up"));
                review.setReview_type(rs.getString("review_type"));

                results.add(review);

                System.out.print("" + rs.getInt("review_id") + " | ");
                System.out.print(rs.getString("topic_id") + " | ");
                System.out.print(rs.getString("username") + " | ");
                System.out.print(rs.getString("review_text") + " | ");
                System.out.print(rs.getString("review_date") + " | ");
                System.out.print("" + rs.getBoolean("anonymous") + " | ");
                System.out.print(rs.getString("file_up") + " | ");
                System.out.println(rs.getString("review_type"));
            }

            return results;

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

    /**
     * Adds a profile picture file path to the users account
     *
     * @param photoURL
     * @param username
     */
    public static void addProfilePic(String photoURL, String username) {
        Connection conn = null;

        // Try to connect to database and run SQL query
        try {
            // Connect to database, print in console if successful
            String url = "jdbc:sqlite:files/RATES.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Got it!");


            // Prepare SQL query, all entries in accounts table
            String preparedQuery = "UPDATE accounts SET picture = ? WHERE username = ?";
            PreparedStatement prepState = conn.prepareStatement(preparedQuery);
            prepState.setString(1, photoURL);
            prepState.setString(2, username);
            prepState.executeUpdate();

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
    }
}
