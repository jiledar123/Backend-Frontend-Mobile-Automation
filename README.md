### Backend-Frontend-Mobile-Automation
* Pre-requisites
  * JDK 1.8
  * maven 3.5.5
  * Mobile test will run on browser stack cloud platform, in order to run mobile tests register your self with https://www.browserstack.com/ and get the key and secrets from app automate product
  * Download latest apk for linkedin and zoom
    * https://zoom-us-zoom.en.uptodown.com/android
    * https://linkedin.en.uptodown.com/android
  * Upload the above binary files on browserstack and get the <b>app_url</b>
    * Refer the guide https://www.browserstack.com/docs/app-automate/appium/upload-app-from-filesystem
  * Register and get your API token from https://pro.coinmarketcap.com/login
 
1. <b>How to run API tests</b>
   1. Run below command
      1. Clone the repository
      2. Run =>   mvn clean compile
      3. Update the @tag in Runner file as **@BackendTask**
      4. Update the @tag in Runner file as **@FrontendTask**
          1. Refer <code>src/test/java/com/test/assignment/Runner.java</code>
         <code>e.g. 	tags = {"@BackendTask"}, </code>
      5. Update the project.properties file with below your correct values for
          1. File path : src/test/resources/project.properties
              1. <code>API_Token=\<your API_Token details\></code>
      6. Run =>  mvn test
2. <b>How to run UI tests</b>
   2. Run below command
       1. Clone the repository
       2. Run => mvn clean compile
       3. Update the @tag in Runner file as **@FrontendTask**
            1. Refer <code>src/test/java/com/test/assignment/Runner.java</code>
             <code>e.g. 	tags = {"@FrontendTask"}, </code>
       4. Run =>  mvn test
3. <b>How to run Mobile tests</b>
   1. Run below command
       1. Clone the repository
       2. Update the project.properties file with below your correct values for
          1. File path : src/test/resources/project.properties
             1. <code>browserstack_username=\<your details\></code>
             2. <code>browserstack_password=\<your details\> </code>
             3. <code>zoom_app_url=\<your details\> </code> eg. bs://eqwtyy354676575543ad3c
             4. <code>linkedin_app_url=\<your details\> </code> eg. bs://eqwtyy354676575543ad3c
          2. Run => mvn clean compile
             1. Update the @tag in Runner file as **@MobileTask**
             2. Refer <code>src/test/java/com/test/assignment/Runner.java</code>
                <code>e.g. 	tags = {"@MobileTask"}, </code>
             3. Run =>  mvn test
* #####<i>Note : Successful test execution results can be found on Project/SuccessfulExecutionEvidence </i>