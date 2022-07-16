package bg.asentt.mybucketlistwithkotlin

import androidx.annotation.DrawableRes

data class BucketListEntry(
    var heading: String, var description: String,
    @DrawableRes var image: Int, var rating: Float
) {

}