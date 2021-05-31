package id.radenyaqien.jetpackdicoding.utils

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {

        Picasso.get()
            .load(url)
            .fit()
            .into(this)
    }
}

@BindingAdapter(value = ["setImageUrlNofit"])
fun ImageView.bindImageUrlNoFit(url: String?) {
    if (url != null && url.isNotBlank()) {

        Picasso.get()
            .load(url)
            .into(this)
    }
}

fun ContentResolver.getFileName(fileUri: Uri): String {
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}