package bg.asentt.mybucketlistwithkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private lateinit var cardPlacesToGo: CardView
    private lateinit var cardThingsToDo: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        setUpClickListeners()

    }

    private fun setUpClickListeners() {
        cardPlacesToGo.setOnClickListener {
            val intent = Intent(this, PlacesToGoActivity::class.java)
            startActivity(intent)
        }

        cardThingsToDo.setOnClickListener {
            val intent = Intent(this, ThingsToDoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun findViews() {
        cardThingsToDo = findViewById<CardView>(R.id.card_view_things_to_do)
        cardPlacesToGo = findViewById<CardView>(R.id.card_view_places_to_go)
    }
}