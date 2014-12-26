# Disable completely the [secure storage password of Eclipse](http://stackoverflow.com/questions/4223059/eclipse-secure-storage)
- Exit Eclipse
- Delete the directory ~/.eclipse/org.eclipse.equinox.security
- Create a text file containing your master password, e.g. echo "secret" > ~/.eclipse/master
- Add to the very top of eclipse.ini, found in the Eclipse program directory (as two lines, don't combine into one)
> -eclipse.password  
> /home/user/.eclipse/master
- Start Eclipse again.

# Source [look up](https://bugs.eclipse.org/bugs/show_bug.cgi?id=384065)
	-javaagent:/home/lplin/apps/eclipse-java-ee/plugins/com.ifedorenko.m2e.sourcelookup_1.1.0.201409160038/com.ifedorenko.m2e.sourcelookup.javaagent.jar

# Eclipse[HTMLEditor](http://amateras.sourceforge.jp/cgi-bin/fswiki_en/wiki.cgi?page=EclipseHTMLEditor)
However, I just found a super-lightweight javascript editor called Eclipse HTML Editor Plugin, made by Amateras, which was exactly what I was looking for.

