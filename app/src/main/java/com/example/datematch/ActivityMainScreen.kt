package com.example.datematch

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.example.datematch.adapters.CardStackAdapter
import com.example.datematch.models.Users
import com.example.datematch.utils.UserDiffCallback
import com.yuyakaido.android.cardstackview.*
import java.util.ArrayList

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ActivityMainScreen : AppCompatActivity() , CardStackListener {

    private val TAG = ActivityMainScreen::class.java.getSimpleName()

    private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val usersAdapter by lazy { CardStackAdapter(createUsers()) }

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

        // set up the CardStackAdapter
        setupCardStackView()

        // set the status bar to white
        val window = getWindow()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(ContextCompat.getColor(this,android.R.color.white))
        }
    }

    private fun setupCardStackView() {
        manager.setStackFrom(StackFrom.Bottom)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(-25.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(false)
        manager.setSwipeableMethod(SwipeableMethod.Manual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = usersAdapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private fun createUsers(): List<Users> {
        val users = ArrayList<Users>()
        users.add(Users(name = "Kathryn Diaz", city = "Kyoto", url = "https://source.unsplash.com/NYyCqdBOKwc/600x800"))
        users.add(Users(name = "Amanda Seyfried", city = "Kyoto", url = "https://m.media-amazon.com/images/M/MV5BMjUyODkwODUyMF5BMl5BanBnXkFtZTcwMzU3MjYxMw@@._V1_UY317_CR33,0,214,317_AL_.jpg"))
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

    private fun paginate() {
        val old = usersAdapter.getUsers()
        val new = old.plus(createUsers())
        val callback = UserDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        usersAdapter.setUsers(new)
        result.dispatchUpdatesTo(usersAdapter)
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.e(TAG, "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.e(TAG, "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (manager.topPosition == usersAdapter.itemCount - 5) {
            paginate()
        }
    }

    override fun onCardRewound() {
        Log.e(TAG, "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.e(TAG, "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.e(TAG, "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        Log.e(TAG, "onCardDisappeared: ($position) ${textView.text}")
    }

}
