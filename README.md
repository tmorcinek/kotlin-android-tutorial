Kotlin Android Tutorial
================


## Content

This is a tutorial project to show how to setup and use Kotlin in Android Project.
I have recently started going to the gym, and I found out that it would be easier if I had 
application to assist me. I am calling this application 'Workout'.

Work on this application goes in steps, each step either integrates some new functionality 
or demonstrates usage of specific feature of Kotlin Language. 

Each step has its own tag (name in the round brackets), and a corresponding video on Youtube.

### 1 Project Setup (project-setup)

 - Creating new Project in Android Studio
 - Initialising GIT

### 2 Kotlin Integration (kotlin-integration)

 - Adding Kotlin plugins to build.gradle
 - Converting Java Code into Kotlin Code

### 3 Kotlin View Injections (view-injections)

 - Adding Kotlin Android Extensions Plugin  
 `apply plugin: 'kotlin-android-extensions'`
 - Using auto-generated properties for views in View Class (Activity)  
 `import kotlinx.android.synthetic.main.home.*`

### 4 Anko Integration (anko-integration)

 - Code Cleanup
 - Adding Anko library to the project  
 `compile "org.jetbrains.anko:anko:$versions.anko"`
 - Examples of one line functions (Kotlin introduces specific syntax)  
 `fun isThisAJoke() = "NO Its not a Joke"`


## Useful links

[Kotlin.org] (https://kotlinlang.org/)

[Kotlink.Anko] (https://github.com/Kotlin/anko/)


## Developed By

Tomasz Morcinek - http://morcinek.co.uk

<a href="https://plus.google.com/+TomaszMorcinek">
  <img alt="Follow me on Google+"
       src="https://github.com/tmorcinek/kotlin-section-adapter/blob/master/raw/google-plus-logo.png" />
</a>
<a href="https://play.google.com/store/apps/developer?id=Tomasz+Morcinek">
  <img alt="Checkout my Applications in Google Play"
       src="https://github.com/tmorcinek/kotlin-section-adapter/blob/master/raw/google-play-logo.png" />
</a>


## License

    You are free to do whatever you want with it. 
