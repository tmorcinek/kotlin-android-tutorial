package com.morcinek.workout

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.home.*
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own Kotlin action, INjected Views", Snackbar.LENGTH_LONG)
                    .setAction("Action", {
                        toast(isThisAJoke())
                    }).show()
        }
    }

    fun isThisAJoke() = "NO Its not a Joke"

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            toast("Settings Action")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
