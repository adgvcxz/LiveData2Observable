# LiveData2Observable

```kotlin
private val number = MutableLiveData<Int>().also { it.value = 1 }
number.toObservable(this).map { "$it" }.subscribe(textView.text())
```

#### dependency:

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
            compile 'com.github.adgvcxz:livedata2observable:0.1.0'
    }

