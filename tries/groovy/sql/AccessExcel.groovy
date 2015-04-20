import java.sql.*

Class.forName("com.googlecode.sqlsheet.Driver");
//Connection writeConnection = DriverManager.getConnection("jdbc:xls:file:test.xlsx");
def sql = new groovy.sql.Sql(DriverManager.getConnection("jdbc:xls:file:test.xlsx"))
println "City\t\tTemperature"
sql.eachRow('SELECT * FROM [temperatures$]') {
println "${it.city}\t\t${it.temperature}"
}