call mvn eclipse:clean -f ../pom.xml
call mvn -U eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=false -f ../pom.xml
@pause
