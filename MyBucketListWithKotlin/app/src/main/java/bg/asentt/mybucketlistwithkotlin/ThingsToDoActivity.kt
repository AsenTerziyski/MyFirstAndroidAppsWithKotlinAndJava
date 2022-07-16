package bg.asentt.mybucketlistwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class ThingsToDoActivity : AppCompatActivity() {
    lateinit var recyclerViewThingsToDo:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_things_to_do)

        val palacesToGo = setUpList()
        recyclerViewThingsToDo = findViewById<RecyclerView>(R.id.card_view_things_to_do)
        val bucketListAdapter = BucketListEntryAdapter(palacesToGo)
        recyclerViewThingsToDo.adapter = bucketListAdapter

    }

    private fun setUpList(): Array<BucketListEntry> {
        return arrayOf<BucketListEntry>(
            BucketListEntry(
                "Heading One",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur nulla massa, bibendum ac ligula quis, gravida auctor risus. In convallis nunc magna.",
                R.drawable.kilimanjaro, 2f
            ),
            BucketListEntry(
                "Heading Two",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur nulla massa, bibendum ac ligula quis, gravida auctor risus. In convallis nunc magna.",
                R.drawable.amazon, 3f
            ),
            BucketListEntry(
                "Heading Two",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur nulla massa, bibendum ac ligula quis, gravida auctor risus. In convallis nunc magna.",
                R.drawable.scubadive, 4f
            )

        )
    }
}