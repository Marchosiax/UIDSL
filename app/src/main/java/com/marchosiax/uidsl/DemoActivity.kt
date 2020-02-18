package com.marchosiax.uidsl

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marchosiax.uidsllib.*
import kotlinx.android.synthetic.main.activity_demo.*

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

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

        demo2.apply {
            setOnClickListener { isEnabled = !isEnabled }

            background = selector {
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
        }
    }

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
}
