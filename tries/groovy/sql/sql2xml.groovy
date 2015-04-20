def sql = groovy.sql.Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo',
    'sa', '8888', 'com.mysql.jdbc.Driver');

mb = new groovy.xml.MarkupBuilder()

mb.weather {
    sql.eachRow('SELECT * from weather') {
        city(name: it.city, temperature: it.temperature)
   }
}

