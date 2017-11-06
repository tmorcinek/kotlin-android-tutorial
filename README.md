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

https://youtu.be/zfmT8fVlMhY

 - Creating new Project in Android Studio
 - Initialising GIT


### 2 Kotlin Integration (kotlin-integration)

https://youtu.be/8HuBoPYb0gw

 - Adding Kotlin plugins to build.gradle
 - Converting Java Code into Kotlin Code


### 3 Kotlin View Injections (view-injections)

https://youtu.be/cs4YZg3P4Ik

 - Adding Kotlin Android Extensions Plugin  
 `apply plugin: 'kotlin-android-extensions'`
 - Using auto-generated properties for views in View Class (Activity)  
 `import kotlinx.android.synthetic.main.home.*`


### 4 Anko Integration (anko-integration)

https://youtu.be/SG77qTm38nw

 - Code Cleanup
 - Adding Anko library to the project  
 `compile "org.jetbrains.anko:anko:$versions.anko"`
 - Examples of one line functions (Kotlin introduces specific syntax)  
 `fun isThisAJoke() = "NO Its not a Joke"`


### 5 Splash Screen (splash-screen)

https://youtu.be/kUI1LRmPCBo

 - Adding Anko Design library to the project  
 `compile "org.jetbrains.anko:anko-design:$versions.anko"`
 - Examples of Anko usage:  
 `snackbar`  
 `doAsync`  
 `uiThread`  
 `startActivity<HomeActivity>()`  


### 6 Splash Screen according to Google Guidelines (google-splash)

https://youtu.be/r9KPvkAk0BM

 - Implementation of Splash Screen to enhance User Experience
 - Based on: https://www.bignerdranch.com/blog/splash-screens-the-right-way/


### 7 Dagger Integration (dagger-integration)

https://youtu.be/F0OwtSrhh0o

 - Integration of dagger into Android Kotlin Project.
 - Creation and usage of 'Extension Function'  
 `fun AppCompatActivity.component() : ApplicationComponent = (application as Application).component`
 - Creation and usage of 'Extension Property'  
 `val AppCompatActivity.component: ApplicationComponent
      get() = (application as Application).component`


### 8 Counter Initial (counter-initial)

https://youtu.be/v7pe5JN_eLs

 - Using CountDownTimer for executing task after interval.
 - Introduction to 'val' property  
 `val name: String = "tomasz" `
 - Introduction to 'var' property  
 `@Inject lateinit var context: Context`
 - Introduction to anonymous class in kotlin  
 `val timer = object : CountDownTimer(...`


### 9 Lateinit, Nullability (lateinit-nullability)

https://youtu.be/0N72ZisrwSE

 - Lateinit var property  
 `lateinit var onFinishFunction: () -> Unit)`
 - Nullability in practice  
 `private var onFinishFunction: (() -> Unit)? = null`
 - Function/Lambda as a property  
 `lateinit var onTickFunction: (Long) -> Unit`
 - Function/Lambda as a method argument  
 `fun onFinish(onFinishFunction: () -> Unit) {`
 - Invocation of Function/Lambda  
 `onFinishFunction.invoke()`  
 `onFinishFunction()`  


### 10 Dagger Injections, Val Property (dagger-val-property)

https://youtu.be/DXamJSAHyGQ

 - Adding new Dagger Module
 - Initializing component with two Modules
 - Creating Singleton object in Module
 - Val Property Lazy Initialization  
`private val ringtone by lazy {`  
`    RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))`  
`}`
 - Val Property with Getter  
 `private val flags: Int`  
 `get() = PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP`  


### 11 Method with Default Arguments (default-arguments-method)

https://youtu.be/mbXjX3xeOL0

 - Method with Default Arguments  
`    fun replaceFragment(fragment: Fragment, tag: String = fragment.javaClass.name, addToBackStack: Boolean = false) {`
 - Constructor with Default Arguments  
 `class ContentFragmentManager(private val activity: FragmentActivity, private val contentLayoutId: Int = R.id.contentFrame) {`  
 - Showing Fragment inside Activity
 - Creating `ContentFragmentManager` to replace fragments in `FrameLayout`
 
 
## Useful links

[Kotlin.org] (https://kotlinlang.org/)

[Kotlink.Anko] (https://github.com/Kotlin/anko/)

[Dagger] (https://github.com/google/dagger)


## Developed By

Tomasz Morcinek 
tomasz.morcinek@gmail.com
tomasz.morcinek.dev@gmail.com

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
