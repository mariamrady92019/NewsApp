package com.route.newappc35fri.bindingAdapters

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.route.newappc35fri.Constants
import com.route.newappc35fri.ui.news.NewsViewModel

@BindingAdapter("app:imageUrl")
fun setImageByUrl(imageView: ImageView, urlToImage: String) {
    Glide.with(imageView)
        .load(urlToImage)
        .into(imageView);

}

@BindingAdapter("app:imageResource")
fun setImageResource(imageView: ImageView , id : Int){
    imageView.setImageResource(id)
}

@RequiresApi(Build.VERSION_CODES.M)
@BindingAdapter("app:changeBackColor")
fun changeBackgroundColor(cardView: CardView, color:Int){
  cardView.setCardBackgroundColor(
        cardView.context.getColor(color)
    )
}

@BindingAdapter("app:search")
fun searchBYKeyword(editText: EditText, viewModel: NewsViewModel) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
            s: CharSequence, start: Int,
            count: Int, after: Int
        ) {}

        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {
            if (s.length != 0)
            {

               viewModel.searchByKeywordAndSource(s.toString(),Constants.source)

            }
        }
    })
}