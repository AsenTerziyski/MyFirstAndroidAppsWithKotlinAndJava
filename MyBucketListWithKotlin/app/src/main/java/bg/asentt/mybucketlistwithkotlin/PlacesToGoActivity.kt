package bg.asentt.mybucketlistwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class PlacesToGoActivity : AppCompatActivity() {

    lateinit var recyclerViewPlacesToGo: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_to_go)

        val palacesToGo = setUpList()
        recyclerViewPlacesToGo = findViewById<RecyclerView>(R.id.recycler_view_places_to_go)
        val bucketListAdapter = BucketListEntryAdapter(palacesToGo)
        recyclerViewPlacesToGo.adapter = bucketListAdapter

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