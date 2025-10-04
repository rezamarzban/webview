This is a template project for Android Studio that allows you to create an android webview application in minutes. You can use it to create a simple app for your website or as a starting point for your HTML5 based android app.

### Getting started

[Download](https://github.com/slymax/webview/archive/master.zip) or clone this repository and import it into Android Studio.

### Using a remote source

To create an app that shows the content of a remote website

1. comment line **27** and uncomment line **24** in `MainActivity.java` and replace `https://example.com` with your url

	```java
	mWebView.loadUrl("https://example.com");
	```

2. open the `MyWebViewClient.java` file and replace `example.com` on line **15** with your hostname

	```java
	hostname = "example.com";
	```

### Using a local source 

To create a local HTML5 android app

1. uncommented line **27** in `MainActivity.java` by default:

	```java
	mWebView.loadUrl("file:///android_asset/index.html");
	```

2. put all your files (including your `index.html`) in the `assets` directory

##* Build

Use `Build.ipynb` or Colab:

```
%%shell
set -x
wget -q --show-progress https://dl.google.com/android/repository/commandlinetools-linux-9123335_latest.zip
mkdir -p sdk
unzip -o commandlinetools-linux-9123335_latest.zip -d sdk
# force line-buffered output so Colab shows live-like output
stdbuf -oL -eL yes | stdbuf -oL -eL ./sdk/cmdline-tools/bin/sdkmanager --sdk_root=/content/sdk "tools"
git clone https://github.com/rezamarzban/webview/
chmod -c 755 /content/webview/gradlew
export ANDROID_HOME=/content/sdk
cd /content/webview
# make Gradle output plain and line-buffered for Colab
stdbuf -oL -eL ./gradlew assembleDebug --console=plain -Dorg.gradle.console=plain
```

To download built android app:

```
from google.colab import files
files.download('/content/webview/app/build/outputs/apk/debug/app-debug.apk')
```
