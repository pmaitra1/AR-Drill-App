# AR Drill Selector

An Android app that allows users to select from a list of drills and visualise a virtual drill marker in Augmented Reality using ARCore and Sceneform.

##  Features

- Dropdown menu to select one of three drills
- Contextual information for each drill, including:
  - Description
  - Safety Tips
  - Image preview
- Clean, user-friendly UI with custom spinner and themed styling
- AR screen with a virtual red cube marker representing the selected drill
- Reset and Back to Menu buttons on the AR screen

##  Built With

- Kotlin
- Android Studio
- ARCore
- Sceneform (AR rendering)
- Custom Spinner Adapter
- XML-based layouts

##  Getting Started

1. **Clone the repository**  

<pre> <code> git clone https://github.com/your-username/ar-drill-selector.git </code> </pre>
 <pre> <code> cd ar-drill-selector </code> </pre>


2. **Open in Android Studio**  
File > Open > Select the project directory

3. **Build & Run**  
- Ensure your physical device supports ARCore
- Connect your device via USB and click **Run**

## 📷 Screenshots

### Main Drill Selection Screen
![Main Drill Screen](assets/screenshot_spinner.jpeg)

---

### Drill Dropdown (Before Selection)
![Drill Spinner Dropdown](assets/screenshot_main.jpeg)

---

### AR Mode – Place the Drill
![AR Mode](assets/screenshot_ar.jpeg)

## 📂 Directory Structure

<pre> app/
├── manifests/
│   └── AndroidManifest.xml
├── java/
│   └── com/example/myapp/
│       ├── MainActivity.kt
│       ├── ARActivity.kt
│       └── DrillAdapter.kt
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_ar.xml
│   │   ├── custom_spinner_item.xml
│   │   └── custom_spinner_dropdown_item.xml
│   ├── drawable/
│   └── values/
│       └── (colors.xml, styles.xml, etc.)
</pre>


##  Requirements

- Android 7.0+ (Nougat) and ARCore-compatible device
- Android Studio Arctic Fox or later
- Internet access for downloading dependencies


##  License

This project is for academic/demonstration purposes. For commercial use, please modify accordingly and comply with ARCore and Sceneform licensing.
