static String toCamelCase( String text, boolean capitalized = false ) {
text = text.replaceAll( "(_)([A-Za-z0-9])", { Object[] it -> it[2].toUpperCase() } )
return capitalized ? text.capitalize() : text
}
 
static String toSnakeCase( String text ) {
text.replaceAll( /([A-Z])/, /_$1/ ).toLowerCase().replaceAll( /^_/, '' )
}
 
 
println toCamelCase("some_field_name", true)
println()
 
println toSnakeCase("CamelCaseClass")
