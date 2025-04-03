
# AirBox Task

Job Application Task for Airbox Systems. This is a automation framework that uses TestNG/Maven/Java.

## Setup
These are the steps to set up the local testing environment for Linux users.
### Dependencies
- Download and install [Android Studio](https://developer.android.com/studio). We will be using the emulator that comes with this.
- Install Node Version Manager: `curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash`

- Install the latest stable version (LTS) of Node Package Manager: `nvm install --lts`
- Install Appium: `npm install -g appium`
- (OPTIONAL) Install Appium Doctor : `npm install -g appium-doctor`
- (OPTIONAL) Install IntelliJ
- Install Maven: `sudo apt install maven -y`
- Install Java 21: `sudo apt install openjdk-21-jdk`
### Set up Appium and the emulator

- There are 3 environment variables that we need to set in order for Appium to work: `ANDROID_HOME`, `ANDROID ROOT` and `JAVA_HOME`. Let's go over each of them.
- First, Let's create/open the `.zshrc` file in your home directory and set up the `ANDROID_HOME`, `ANDROID ROOT` and `JAVA_HOME` variables to the following:
    - `JAVA_HOME`: If you're using OpenJDK with Ubuntu, the path we need should be `/usr/lib/jvm/java-21-openjdk-amd64/`
    - `ANDROID_HOME`: This should typically be set to `$HOME/Android/Sdk`
    - `ANDROID_ROOT`: This is the same as `ANDROID_HOME`: `$HOME/Android/Sdk`
    - Your `.zshrc` file should look something like this:
    ```
    export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64/
    export ANDROID_HOME=$HOME/Android/Sdk
    export ANDROID_SDK_ROOT=$HOME/Android/Sdk
    ```
- Now you need to add all the relevant tools to the `$PATH`. Append the following to your `.zshrc` file:
```
export PATH=$JAVA_HOME/bin:$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/cmdline-tools/latest/bin/:$ANDROID_HOME/cmdline-tools/latest/bin/:$PATH
```
- Now we need to install the `Android Command Line Tools`
    - Open `Android Studio`
    - Go to: `More Actions` -> `SDK Manager` -> `SDK Tools`
    - Select and install the `Android SDK Command Line Tools`
- New let's set up the emulator
    - Open `Android Studio`
    - Go to: `More Actions` -> `Virtual Device Manager`
    - There should be a default emulator already installed. If there isn't add a new emulator and select the `API-ext17` version
### Running the tests
- Clone the repository: `git clone git@github.com:epaulins/TestNG-Java---AirBox.git`
- Create an `.env` file in the root directory with the following environment variables:
    - `FULL_NAME:<Google Account Full Name>`
    - `EMAIL:<Google Account Email>`
    - `PASSWORD:<Google Account Password>`
- Start an Appium server: `appium server`
- Start the emulator: `emulator @<Name of the emulator> -wipe-data`. The name of the emulator can be found in the `Virtual Device Manager` using `Android Studio`
- There are 4 suites in this project. To run them use one of the following test commands:
    - All Tests: `mvn test -DsuiteXmlFile=src/test/resources/testng-all.xml`
    - Login Suite: `mvn test -DsuiteXmlFile=src/test/resources/testng-login.xml`
    - Search Suite: `mvn test -DsuiteXmlFile=src/test/resources/testng-search.xml`
    - Navigation Suite: `mvn test -DsuiteXmlFile=src/test/resources/testng-navigation.xml`
- The tests can also be ran via IntelliJ by right clicking on the suite xml file in `src/main/resources` and clicking the `Run` option
- An ExtentReport should be generated in the `test-output` directory. To view it open the `ExtentReport.html` file via any browser.
## Limitations
There are some things to consider when running the tests:
- Make sure to use a clean emulator. Since these tests are designed for a fresh emulator with no previous data.
- If you need to run the tests multiple times, make sure to close and relaunch the emulator using the same command.
- The tests are tailored to only one specific Android version
- The NavigationRerouting test is a bit flaky. When manually changing the GpsLocation when navigating, it occasionally will not successfully reroute and will continue from the previous location.
## Possible Improvements
- Add more Android versions/devices and refactor the tests to be compatible with them
- Improve the flakiness of NavigationRerouting by adding a more sophisticated wait before chaning the geolocation
## Comments
- The Manual Test Cases are located in the `AirBox Test Cases` PDF File
- The reports are located in the `test-output` directory
