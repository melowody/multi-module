# multi-module

## Installation

```kotlin
plugins {
    id("dev.meluhdy.multi-module") version "1.0.1"
}
```

## Usage

```kotlin
dependencies {
    multiModule.tryMultiModule(":MODULE", "com.example:fallback:VERSION")
    
    // Or, if you want something other than compileOnly
    multiModule.tryMultiModule(":MODULE", "com.example2:fallback:VERSION", "implementation")
}
```

This will attempt to load `project(":MODULE")` and fallback to `"com.example(2):fallback:VERSION"` if it cannot find it.