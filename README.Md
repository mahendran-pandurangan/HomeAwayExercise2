Pre-requisites to execute Exercise1:
* Java JDK must be installed - Either latest or version-7
* Maven must be installed - Preferably 3.3.X or above (I've used 3.3.9)
* Any Editor (OSS) (This is optional always user choice to use on their own). I've used ATOM to build this test suite.
* Need Jersey Client Maven dependency



Credentials:
APIKey : GmzU61EmH4RiGv6C18Y1fBaeOcSBinoA3rfNrZ2F


How to run AutomationExercise2 from command line: 

1. Clone the project into your local. 
2. Goto Project Location, and run the following :

To run Test Suite containing two tests, "mvn test -DsuiteXmlFile=testng-suite.xml"

To run individual testng test - RightClick on the test and Run as TestNG Test



Issues:

* For Test1 to fetch the stationId, Only if we get the corresponding stationId matching to 'HYATT AUSTIN' station name had to be used to match the street address provided in Test2.

