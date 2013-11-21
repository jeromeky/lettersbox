LettersBox Framework

TODO : 
- Put aar to the central repository
- Add 

0.0.1 :
- library aar available

How to use

With Android Studio / gradle build : 
- Download library (aar format)
- To using local .aar Android Library Package in gradle builds, you have to create a local repository. Indeed, to add an .aar package to your project, it had to be stored in the central maven repository (TODO), which is not the case currently.
- Use maven to install aar package in a local repository :
- mvn install:install-file -Dfile=d:\lettersbox.aar -DgroupId=com.arkanan -DartifactId=lettersbox -Dversion=0.0.1 -Dpackaging=aar
- Declare this library in build.gradle :
dependencies {
    compile 'com.arkanan:lettersbox:0.0.1'
}
- Declare 2 LinearLayout which will contains lettersbox.
- Construct LettersBox with LettersBoxFactory.

A sample is available here : http://github.com/jeromeky/lettersboxsample/
