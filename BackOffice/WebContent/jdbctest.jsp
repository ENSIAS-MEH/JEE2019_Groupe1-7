<!doctype html public "-//w3c//dtd html 4.0 transitional//en"
                      "http://www.w3.org/TR/REC-html40/strict.dtd">

<!-- These import statements are needed to run the SQL queries, they are
     part of the JDK. -->
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<html>
<head>
<title>JDBC test with Connector/J</title>
</head>
<body>

<%!
/* My source for most of this code:
   http://www.webmasterbase.com/article/770/565 */

// Define variables
String uId;
String fName;
String lName;
%>
<%
// This is needed to use Connector/J. It basically creates a new instance
// of the Connector/J jdbc driver.
Class.forName("com.mysql.jdbc.Driver").newInstance();

// Open new connection.
java.sql.Connection conn;
/* To connect to the database, you need to use a JDBC url with the following 
   format ([xxx] denotes optional url components):
   jdbc:mysql://[hostname][:port]/[dbname][?param1=value1][&param2=value2]... 
   By default MySQL's hostname is "localhost." The database used here is 
   called "mydb" and MySQL's default user is "root". If we had a database 
   password we would add "&password=xxx" to the end of the url.
*/
conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/ecom_bd?user=root&password=root");
Statement sqlStatement = conn.createStatement();

// Generate the SQL query.
String query = "SELECT UserId, FirstName, LastName FROM user";

// Get the query results and display them.
ResultSet sqlResult = sqlStatement.executeQuery(query);
while(sqlResult.next()) {
uId = sqlResult.getString("UserId");
fName = sqlResult.getString("FirstName");
lName = sqlResult.getString("LastName");
out.println("<b>" + uId + "</b>, " + fName + ", " + lName + "<br />");
}

// Close the connection.
sqlResult.close();
sqlStatement.close();
conn.close();
%>
</body>
</html> 
 