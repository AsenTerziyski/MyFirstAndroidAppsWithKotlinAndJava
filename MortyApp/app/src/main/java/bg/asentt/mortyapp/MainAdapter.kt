package bg.asentt.mortyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bg.asentt.mortyapp.network.Character
import coil.load
import coil.transform.CircleCropTransformation

class MainAdapter(private val charactersList: List<Character>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(character: Character) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val image = itemView.findViewById<ImageView>(R.id.image)
            name.text = character.name
            image.load(character.image) {
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }
}