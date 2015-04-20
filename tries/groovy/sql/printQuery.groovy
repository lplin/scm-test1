//println ''.metaClass.methods*.name.sort().unique()
def sql = groovy.sql.Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo',
    'sa', '8888', 'com.mysql.jdbc.Driver');
def cc = 0;    
processMeta = { metaData ->
    cc = metaData.columnCount
    print '||'
    cc.times { i ->
        printf "%-20s||", metaData.getColumnLabel(i+1).capitalize()
    }
    println ""
}

def query = 'SELECT * from weather';
sql.eachRow(query, processMeta) {
    print '|'
    cc.times { i ->
        printf "%-21s|", it[i]
    }
    println ''
}
