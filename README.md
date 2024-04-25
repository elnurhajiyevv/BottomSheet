[![](https://jitpack.io/v/elnurhajiyevv/BottomSheet.svg)](https://jitpack.io/#elnurhajiyevv/BottomSheet)



# BottomSheet

## BottomSheet

![Screenshot2](images/screenshot2.png)
![Screenshot1](images/screenshot1.png)
![Screenshot3](images/screenshot3.png)
![Screenshot4](images/screenshot4.png)


## About
***This bottomsheet extends BottomSheetDialogFragment class and it is a responsive bottomsheet Android library which is hosted on ***Jitpack***. This library allows easily create bottom view concept.*** 


## Setup

#### 1. Add the gradle dependency

Add JitPack repository to your root build.gradle at the end of repositories:
```

dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency:
```
dependencies {
	        implementation 'com.github.elnurhajiyevv:BottomSheet:-847f249cbe-1'
	}
```


* How to use:

```kotlin
elhaBottomSheet {
            // set true for fullscreen or false half screen
            showFullScreen(true)
            // submit list for adapter
            itemList = list
            onItemsSelected = {
                // handle item selection
            }
            onBack {
                // handle back button click
            }
            setTopMarginView(70)
        }?.show(supportFragmentManager, ElhaBottomSheetDialog::class.java.canonicalName)
```

MIT License

Copyright (c) 2024 Elnur Hajiyev

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
