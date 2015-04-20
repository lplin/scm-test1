def sql = groovy.sql.Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo',
    'sa', '8888', 'com.mysql.jdbc.Driver');
dataSet = sql.dataSet('weather')
println dataSet.getClass().methods*.name.sort().unique()
println "Number of cities : " + sql.rows('SELECT * from weather').size()
//dataSet.add(city: 'Denver', temperature: 20)
println "Number of cities : " + sql.rows('SELECT * from weather').size()
println sql.firstRow("SELECT * FROM weather WHERE city='Denver'");

dataSet.executeUpdate("UPDATE weather SET temperature=30 WHERE city='Denver'")
println sql.firstRow("SELECT * FROM weather WHERE city='Denver'");
