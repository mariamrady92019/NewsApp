package com.route.newappc35fri.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.route.newappc35fri.R
import com.route.newappc35fri.databinding.ItemCategoryLeftSidedBinding
import com.route.newappc35fri.databinding.ItemCategoryRightSidedBinding
import com.route.newappc35fri.ui.news.NewsAdapter

class CategoriesAdapter(val categories: List<Category>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         if(viewType==isLeftSided){
             var leftSidedBinding:ItemCategoryLeftSidedBinding = DataBindingUtil.inflate(
                 LayoutInflater.from(parent.context), R.layout.item_category_left_sided
             ,parent , false
                 )

             return  ViewHolderLeft(leftSidedBinding)
         }else{

          var rightSided : ItemCategoryRightSidedBinding = DataBindingUtil.inflate(
              LayoutInflater.from(parent.context), R.layout.item_category_right_sided,parent, false
          )
           return  ViewHolderRight(rightSided)
         }


    }

    val isRightSided = 10;
    val isLeftSided = 20;


    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) return isLeftSided;
        return isRightSided;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cat = categories[position]
        if(getItemViewType(position)==isLeftSided){
            ( holder as ViewHolderLeft).bind(cat)

        }else{
            ( holder as ViewHolderRight).bind(cat)
        }




        onItemClickListener?.let {
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, cat)
            }
        }

    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(pos: Int, item: Category)
    }

    override fun getItemCount(): Int = categories.size


    class ViewHolderLeft(val view :ItemCategoryLeftSidedBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(model : Category){
            view.model=model
            view.executePendingBindings()
        }
}
    class ViewHolderRight(val view : ItemCategoryRightSidedBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(model : Category){
            view.model=model
            view.executePendingBindings()
        }
    }

    }