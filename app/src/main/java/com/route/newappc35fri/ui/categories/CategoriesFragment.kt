package com.route.newappc35fri.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.route.newappc35fri.R
import com.route.newappc35fri.databinding.FragmentCategoriesBinding
import com.route.newappc35fri.databinding.FragmentSettingsBinding

class CategoriesFragment : Fragment() {

    lateinit var  fragmentCategoriesBinding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCategoriesBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_categories,container,false)
        return fragmentCategoriesBinding.root

    }

    val categories = listOf(
        Category(
            "sports", R.drawable.sports,
            R.string.sports, R.color.red
        ),
        Category(
            "technology", R.drawable.politics,
            R.string.technology, R.color.blue
        ),
        Category(
            "health", R.drawable.health,
            R.string.health, R.color.pink
        ),
        Category(
            "business", R.drawable.bussines,
            R.string.business, R.color.brown_orange
        ),
        Category(
            "general", R.drawable.environment,
            R.string.general, R.color.baby_blue
        ),
        Category(
            "science", R.drawable.science,
            R.string.science, R.color.yellow
        ),
    )


    val adapter = CategoriesAdapter(categories)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView();
    }

    private fun initRecyclerView() {

       fragmentCategoriesBinding.recyclerView.adapter = adapter;
        adapter.onItemClickListener = object : CategoriesAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int, item: Category) {
                onCategoryClickListener?.onCategoryClick(category = item)
            }
        }

    }

    var onCategoryClickListener: OnCategoryClickListener? = null;

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category);
    }
}