package java.axon.test.sumsung.com.randomuserstesttask

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri : String) {
    Glide.with(this).load(uri).into(this)
}