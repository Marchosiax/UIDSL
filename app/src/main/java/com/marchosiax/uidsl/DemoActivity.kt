package com.marchosiax.uidsl

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.marchosiax.uidsllib.build
import com.marchosiax.uidsllib.rectangle
import kotlinx.android.synthetic.main.activity_demo.*

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        frm.background = rectangle {
            corner { radius = 16f }
            gradient {
                colors {
                    start = Color.RED
                    center = Color.GREEN
                    end = Color.YELLOW
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.build {
            item {
                id = 10
                title = "Test"
            }
            group {
                id = 100
                item {
                    id = 101
                    title = "Test 1"
                }
                item {
                    id = 102
                    title = "Test 2"
                }
            }
        }
        return true
    }
}
