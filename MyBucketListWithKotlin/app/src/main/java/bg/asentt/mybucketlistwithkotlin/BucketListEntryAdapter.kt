package bg.asentt.mybucketlistwithkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BucketListEntryAdapter(bucketListEntry: Array<BucketListEntry>) :
    RecyclerView.Adapter<BucketListEntryAdapter.BucketListEntryViewHolder>() {

    var bucketEntries = emptyArray<BucketListEntry>()

    init {
        bucketEntries = bucketListEntry
    }

    class BucketListEntryViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        private var image: ImageView? = null
        private var descrText: TextView? = null
        private var ratingBar: RatingBar? = null
        private var headerText: TextView? = null

        init {
            image = view.findViewById(R.id.image_view_item_picture)
            descrText = view.findViewById(R.id.text_view_description)
            ratingBar = view.findViewById(R.id.rating_bar)
            headerText = view.findViewById(R.id.text_view_heading)
        }

        fun bind(entry: BucketListEntry, position: Int) {
            val headerString = "$position ." + entry.heading
            image?.setImageResource(entry.image)
            descrText?.text = entry.description
            ratingBar?.rating = entry.rating
            headerText?.text = headerString
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BucketListEntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bucket_list_entry, parent, false)
        return BucketListEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: BucketListEntryViewHolder, position: Int) {
        holder.bind(bucketEntries[position], position)
    }

    override fun getItemCount(): Int {
        return bucketEntries.size
    }
}