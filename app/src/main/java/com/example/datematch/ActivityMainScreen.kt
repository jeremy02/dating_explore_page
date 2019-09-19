package com.example.datematch

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.datematch.models.Users
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import java.util.ArrayList

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ActivityMainScreen : AppCompatActivity() , CardStackListener {

    private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set the theme of statuc bar icons to dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        };

        setContentView(R.layout.activity_main_screen)
        supportActionBar?.hide()

        // Delayed display of UI elements
        supportActionBar?.hide()

        // set the status bar to white
        val window = getWindow()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(ContextCompat.getColor(this,android.R.color.white))
        }
    }

    private fun createUsers(): List<Users> {
        val users = ArrayList<Users>()
        users.add(Users(name = "Kathryn Diaz", city = "Kyoto", url = "https://source.unsplash.com/NYyCqdBOKwc/600x800"))
        users.add(Users(name = "Ramon Iglesias", city = "Kyoto", url = "https://source.unsplash.com/Xq1ntWruZQI/600x800"))
        users.add(Users(name = "Missy Peregrym", city = "Kyoto", url = "https://source.unsplash.com/buF62ewDLcQ/600x800"))
        users.add(Users(name = "Rachel McAdams", city = "New York", url = "https://source.unsplash.com/THozNzxEP3g/600x800"))
        users.add(Users(name = "Shantel VanSanten", city = "New York", url = "https://source.unsplash.com/USrZRcRS2Lw/600x800"))
        users.add(Users(name = "Natalie Portman", city = "New York", url = "https://source.unsplash.com/PeFk7fzxTdk/600x800"))
        users.add(Users(name = "Sophia Bush", city = "Paris", url = "https://source.unsplash.com/LrMWHKqilUw/600x800"))
        users.add(Users(name = "Jessica Lowndes", city = "Paris", url = "https://source.unsplash.com/HN-5Z6AmxrM/600x800"))
        users.add(Users(name = "Michelle Monaghan", city = "London", url = "https://source.unsplash.com/CdVAUADdqEc/600x800"))
        users.add(Users(name = "Nelly Furtado", city = "China", url = "https://source.unsplash.com/AWh9C-QjhE4/600x800"))
        return users
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardSwiped(direction: Direction?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardCanceled() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardAppeared(view: View?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCardRewound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
