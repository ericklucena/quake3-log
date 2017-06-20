# Quake 3 Log

Small command line application that reads a Quake 3 server log, parses it and outputs matches and ranking information. In addition is also available a simple web page for displaying the ranking information outputted by the command line app.

## Getting Started

The steps bellow will guide you in the process of compiling and using of both the application and the webpage.

### Prerequisites

* To compile and run the command line application, you will need to have Java (1.8+) and maven installed on your computer.
* For the web page, is necessary the use of a http server.


### Compiling

A maven compatible Java IDE can be used to compile and export the application jar.

If you are using Eclipse, you can compile and generate by using the export tool, selecting the 'Java/Runnable JAR file' wizard.

If you prefer to use the terminal, you can use the following command at the project root folder:

```
mvn package
```

The source code will be tested, compiled and a runnable jar file will be generated at the 'target' folder.

### Running

The tool will receive two or three parameters:

* Information type:
  
		-s [summary, default]
		-r [ranking] 

* Output type:
  
		-p [plain, default]
		-j [json]
    	
* Log's file path [required]

Assuming that your packaged version of the application is named 'quake3log.jar', the tool can be ran as follows:
  
	$ java -jar quake3log.jar [[-s | -r] [ -p | -j]] <log_file>

For example, if you want a ranking report in json format from the file q3server.log, you should input the following command:

	$ java -jar quake3log.jar -r -j q3server.log

It is also possible to store the output on a file to be shown by the web page, by running

	$ java -jar quake3log.jar -r -j q3server.log > ranking.json

## Running the tests

Using Eclipse you can run the tests by right-clicking the project an selecting 'Run as -> JUnit test'

If you prefer to use the terminal, you can use the following command at the project root folder:

```
mvn test
```

### About the tests

Unit tests were developed for the classes that contained logic necessary for the proper functioning of the application.

## Deployment

To serve the webpage you can use a http server like Apache or Xampp.

For simple tests, a lightweight Python web server can be used by running the following command at the webpage root folder:

```
$ python -m SimpleHTTPServer

```

The webpage will always load a 'ranking.json' file that must be in the same folder. This file can be generated by the command line application by using the parameters '-r -j'.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Jackson JSON Processor](https://github.com/FasterXML/jackson-docs) - Library used for JSON serializing
* [JUnit](http://junit.org/junit4/) - Java unit testing framework
* [Mockito](http://site.mockito.org) Mocking framework for unit tests in Java

