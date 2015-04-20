import java.util.Date;
import java.sql.*;

Class.forName("com.googlecode.sqlsheet.Driver");

Connection writeConnection = DriverManager.getConnection("jdbc:xls:file:test.xlsx");

Statement writeStatement = writeConnection.createStatement();

writeStatement.executeUpdate("CREATE TABLE TEST_INSERT(COL1 INT, COL2 VARCHAR(255), COL3 DATE)");

PreparedStatement writeStatement2 = 
writeConnection.prepareStatement("INSERT INTO TEST_INSERT(COL1, COL2, COL3) VALUES(?,?,?)");

for(int i = 0; i<3;i++){
  writeStatement2.setDouble(1, i);
  writeStatement2.setString(2, "Row" + i);
  writeStatement2.setDate(3, new java.sql.Date(new Date().getTime()));
  writeStatement2.execute();
}

writeStatement.close();
writeStatement2.close();
writeConnection.close();