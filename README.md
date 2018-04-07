# paycent-sdk-eclipse
mobile payment sdk for Eclipse ADT


## Eclipse: integrate PaySdkProject

If you still use eclipse to build the project, we provide a library project for integration. But we do NOT recommend due to it’s more complicated than Android Studio. 
1) First download Eclipse ADT and Android SDK v23 (or higher version). 
2) Download android support tools (reference link). 

## Dependencies

In Eclipse, use API v23 or higher version to build, the output APK supports Android 4.2 +.
* Import PaysdkProject as library
* Under your project workspace, Import eclipse project “PaysdkProject”. 
* Then open properties for PaysdkProject. Please check “Is library”, to make it as a library. 

## Import android support projects 

Please find 4 android support projects source code in your ADT sdk installation directory “sdk/extras/android/support”:
<pre>
 v7/appcompact
 v7/cardview
 v7/recyclerview
 design
</pre>
Import these 4 Projects in your workspace like showed in above figure.

Edit each project.properties file under the root directory of each dependent project, make the project as library: 
 <pre>     android.library=true
</pre>
Edit AndroidMenifest.xml file (target version could be greater than 23).

Importantly, need to add dependencies for project “design”: In package explore view, right click project “design”, and open the dialog from properties (menu), select android and check API , then add above dependent projects “appcompat” and “cardview” .
 
Finally, right click your App project and open the dialog from properties (menu), select android and add above 4 dependent projects .

Now, it should clean the compilation errors. If you still have compile errors, please check the SDK API version (23+) and AndroidMenifest.xml file sdk version.

## Add library project reference

Add the library project as reference in your app project. Like the demo app.
 
If built success, it will generate R files under your app gen directory. One of R file should be “com.paycent.android.R”. 
Trouble shooting notes: 
1) Under some version of Eclipse, if R class file was not generated successfully, please clean the project build or restart the eclipse workspace, it will build the workspace again.  
2) Use JDK1.7 and higher version. 

After the sdk imported successfully, you can write the integration code as the sample reference.
