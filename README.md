# UIDSL
Kotlin DSL for building drwables, menus, etc.
Inspired by [Jetpack Compose](https://developer.android.com/jetpack/compose)

## Installation
```gradle
implementation 'com.marchosiax:UIDSL:0.2-beta01'
```

## Usage
### Shape drawables
```kotlin
demo1.background = rectangle {
    corner { radius = 16f }
    gradient {
        colors {
          start = Color.RED
          center = Color.GREEN
          end = Color.YELLOW
        }
    }
}
```

### State selector
```kotlin
demo2.background = selector {
    enabled {
        drawable = oval {
            solid { color = Color.BLUE }
        }
    }

    pressed {
        drawable = rectangle {
            gradient {
                colors {
                    start = Color.RED
                    center = Color.GREEN
                    end = Color.YELLOW
                }
            }
        }
    }

    disabled {
        drawable = line {
            stroke {
                width = 16
                dashWidth = 3f
            }
            size {
                width = 16
                height = 8
            }
        }
    }
}
```

### Building menus
For menus, you have to override `onCreateOptionsMenu(menu: Menu?)` and use the provided `build()` extension function to build your menu items and groups:
```kotlin
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menu?.build {
        item {
            id = 10
            title = "Item 1"
            iconResource = R.drawable.ic_android_black_24dp
            showAsAction = MenuItem.SHOW_AS_ACTION_ALWAYS
            onClick {
                Toast.makeText(this@DemoActivity, "onClick", Toast.LENGTH_SHORT).show()
                true
            }
        }
        group {
            id = 100
            item {
                id = 101
                title = "Item 2"
                showAsAction = MenuItem.SHOW_AS_ACTION_NEVER
            }
            item {
                id = 102
                title = "Item 3"
                   showAsAction = MenuItem.SHOW_AS_ACTION_NEVER
            }
        }
    }
    return true
}
```

## Contributions
New features will be added soon!

Contributions are welcome.
