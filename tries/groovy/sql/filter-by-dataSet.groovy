def sql = groovy.sql.Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo',
    'sa', '8888', 'com.mysql.jdbc.Driver');

dataSet = sql.dataSet('weather')
citiesBelowFreezing = dataSet.findAll { it.temperature < 32 }
println "Cities below freezing:"
citiesBelowFreezing.each {
  println it.city
}

