# Android WebView Template

This is a template project for Android Studio that allows you to create an Android WebView application in minutes. You can use it to create a simple app for your website or as a starting point for your HTML5-based Android app, including support for WebAssembly (WASM) modules in local assets.

### Getting Started

1. [Download](https://github.com/slymax/webview/archive/master.zip) or clone this repository and import it into Android Studio.
2. Ensure Android SDK 35 is installed via **Tools > SDK Manager**.
3. Sync the project (**File > Sync Project with Gradle Files**).
4. For local content: Place your `index.html` and assets (e.g., `module.wasm`, JS files) in `app/src/main/assets/`.
5. Build and run: `./gradlew assembleDebug` or via Android Studio.

**Note:** This template requires JDK 17+. If using Google Colab or similar, install OpenJDK 17 and set `org.gradle.java.home` in `gradle.properties`.

### Using a Remote Source

To create an app that shows the content of a remote website:

1. In `MainActivity.java`, comment out line ~40 (local load) and uncomment/adjust line ~42:
   ```java
   // mWebView.loadUrl("https://appassets.androidplatform.net/assets/index.html");
   
   mWebView.loadUrl("https://example.com");

### Using a local source 

To create a local HTML5 android app

1. uncommented line **27** in `MainActivity.java` by default:

	```java
	mWebView.loadUrl("file:///android_asset/index.html");
	```

2. put all your files (including your `index.html`) in the `assets` directory

### Build with Colab

Use `Build.ipynb` or Colab:

```
%%shell
set -x
apt update
apt install openjdk-17-jdk -y
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
wget -q --show-progress https://dl.google.com/android/repository/commandlinetools-linux-9123335_latest.zip
mkdir -p sdk
unzip -o commandlinetools-linux-9123335_latest.zip -d sdk
# force line-buffered output so Colab shows live-like output
stdbuf -oL -eL yes | stdbuf -oL -eL ./sdk/cmdline-tools/bin/sdkmanager --sdk_root=/content/sdk "tools"
git clone https://github.com/rezamarzban/webview.git
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

To build with custom assets use `BuildCustomAssets.ipynb`.
