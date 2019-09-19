package com.example.datematch

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.example.datematch.adapters.CardStackAdapter
import com.example.datematch.models.Users
import com.example.datematch.utils.UserDiffCallback
import com.yuyakaido.android.cardstackview.*
import java.util.*

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
        manager.setTranslationInterval(12.0f)
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
        users.add(Users(name = "Milan Kunis", city = "Japan,Kyoto",
            url = "https://m.media-amazon.com/images/M/MV5BODQyNTQyNzY4MV5BMl5BanBnXkFtZTcwODg5MDA3MQ@@._V1_UY317_CR25,0,214,317_AL_.jpg",
            hobbies = "Cinema,<font color=\"#bdbdbd\">Photo,</font>Tattoo,<font color=\"#bdbdbd\">Bicycle,</font>Cooking"))
        users.add(Users(name = "Amanda Seyfried", city = "Canada, Montreal",
            url = "https://m.media-amazon.com/images/M/MV5BMjUyODkwODUyMF5BMl5BanBnXkFtZTcwMzU3MjYxMw@@._V1_UY317_CR33,0,214,317_AL_.jpg",
            hobbies = "Singing,<font color=\"#bdbdbd\">Reading,</font>Tattoo,<font color=\"#bdbdbd\">Swimming,</font>Cooking"))
        users.add(Users(name = "Missy Peregrym", city = "Australia, Adelaide",
            url = "https://m.media-amazon.com/images/M/MV5BMTQyNzExNzQ1N15BMl5BanBnXkFtZTcwNjk0NDAyMw@@._V1_UY317_CR17,0,214,317_AL_.jpg",
            hobbies = "Cinema,<font color=\"#bdbdbd\">Photo,</font>Tattoo,<font color=\"#bdbdbd\">Bicycle,</font>Cooking"))
        users.add(Users(name = "Rachel McAdams", city = "USA, New Jersey",
            url = "https://m.media-amazon.com/images/M/MV5BMTY5ODcxMDU4NV5BMl5BanBnXkFtZTcwMjAzNjQyNQ@@._V1_UY209_CR2,0,140,209_AL_.jpg",
            hobbies = "Singing,<font color=\"#bdbdbd\">Reading,</font>Tattoo,<font color=\"#bdbdbd\">Swimming,</font>Cooking"))
        users.add(Users(name = "Shantel VanSanten", city = "Ukraine, Dnipro",
            url = "https://m.media-amazon.com/images/M/MV5BMzk4NjFhYWItODg5OC00MmM1LTljMzItNDBjOWYzMTdiNjVjXkEyXkFqcGdeQXVyMTQxMTcxNzc@._V1_UX140_CR0,0,140,209_AL_.jpg",
            hobbies = "Cinema,<font color=\"#bdbdbd\">Photo,</font>Tattoo,<font color=\"#bdbdbd\">Bicycle,</font>Cooking"))
        users.add(Users(name = "Natalie Portman", city = "USA, New York",
            url = "https://m.media-amazon.com/images/M/MV5BMTQ3ODE3Mjg1NV5BMl5BanBnXkFtZTcwNzA4ODcxNA@@._V1_UY209_CR8,0,140,209_AL_.jpg",
            hobbies = "Singing,<font color=\"#bdbdbd\">Reading,</font>Tattoo,<font color=\"#bdbdbd\">Swimming,</font>Cooking"))
        users.add(Users(name = "Sophia Bush", city = "India, Bangladesh",
            url = "https://m.media-amazon.com/images/M/MV5BODI2MzM0MTkzM15BMl5BanBnXkFtZTgwMjQxMzgwNTM@._V1_UY209_CR10,0,140,209_AL_.jpg",
            hobbies = "Cinema,<font color=\"#bdbdbd\">Photo,</font>Tattoo,<font color=\"#bdbdbd\">Bicycle,</font>Cooking"))
        users.add(Users(name = "Jessica Lowndes", city = "France, Saint Ettiene",
            url = "https://m.media-amazon.com/images/M/MV5BOTY2M2I1OTgtYWI5Zi00Mjc1LWI5MDQtNmYzMWM4Y2ExMTNkXkEyXkFqcGdeQXVyMjQwMDg0Ng@@._V1_UX140_CR0,0,140,209_AL_.jpg",
            hobbies = "Singing,<font color=\"#bdbdbd\">Reading,</font>Tattoo,<font color=\"#bdbdbd\">Swimming,</font>Cooking"))
        users.add(Users(name = "Michelle Monaghan", city = "London, WestHam",
            url = "https://m.media-amazon.com/images/M/MV5BNzEwYTdjNjYtZDk4NC00YzFmLTlkZTQtNTQyZTg3YmIxYWIxL2ltYWdlXkEyXkFqcGdeQXVyNzg5MzIyOA@@._V1_UY209_CR13,0,140,209_AL_.jpg",
            hobbies = "Cinema,<font color=\"#bdbdbd\">Photo,</font>Tattoo,<font color=\"#bdbdbd\">Bicycle,</font>Cooking"))
        users.add(Users(name = "Nelly Furtado", city = "China, Hongkong",
            url = "https://m.media-amazon.com/images/M/MV5BMTQ5OTMxMTMyOF5BMl5BanBnXkFtZTcwMzExMjQ4Mg@@._V1_UX140_CR0,0,140,209_AL_.jpg",
            hobbies = "Singing,<font color=\"#bdbdbd\">Reading,</font>Tattoo,<font color=\"#bdbdbd\">Swimming,</font>Cooking"))
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
//        val textView = view.findViewById<TextView>(R.id.item_name)
//        Log.e(TAG, "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardDisappeared(view: View, position: Int) {
//        val textView = view.findViewById<TextView>(R.id.item_name)
//        Log.e(TAG, "onCardDisappeared: ($position) ${textView.text}")
    }

}
