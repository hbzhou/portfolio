### Portfolio
a code exercise to calculate the NAV of a Portfolio and print out the details on console basing on the price you publish

#### 1. Getting Started
Build the project with Gradle and Java 17(I don't use JDK8, as it shouldn't be a limit for a coding exercise):
```shell
  ./gradlew build
```

Run the project and the shell console will show
```shell
java -jar ./build/libs/portfolio-0.0.1-SNAPSHOT.jar
```

Publish the price of stock
```shell
publish AAPL 120.00
```
```jshelllanguage
shell:>publish AAPL 140.00
AAPL change to 140.00
## Portfolio
Symbol                   | Price    | Qty        | Value 
AAPL                     | 140.00   | 1000       | 140000.00
AAPL-JAN-2028-150-C      | 18.71    | -20000     | -374200.00
AAPL-JAN-2028-150-P      | 2.47     | 20000      | 49400.00
TELSA-DEC-2026-400-P     | 300.00   | -10000     | -3000000.00
TELSA-JUN-2028-600-C     | 550.00   | 10000      | 5500000.00
#Total portfolio                               2315200.00

```

if you prefer run it with container you can build the image 
This project has been configured to let you generate either a lightweight container or a native executable.
It is also possible to run your tests in a native image.

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```shell
./gradlew bootBuildImage
```

Then, you can run the app like any other container:

```shell
 docker run --rm portfolio:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```shell
./gradlew nativeCompile
```

Then, you can run the app as follows:
```shell
build/native/nativeCompile/portfolio
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.2-SNAPSHOT/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.2-SNAPSHOT/gradle-plugin/packaging-oci-image.html)
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/3.4.2-SNAPSHOT/reference/packaging/native-image/introducing-graalvm-native-images.html)
* [Spring Shell](https://docs.spring.io/spring-shell/reference/index.html)