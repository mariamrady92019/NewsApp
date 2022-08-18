package com.route.newappc35fri.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.route.newappc35fri.R
import com.route.newappc35fri.databinding.ActivityHomeBinding
import com.route.newappc35fri.model.ArticlesItem
import com.route.newappc35fri.ui.categories.CategoriesFragment
import com.route.newappc35fri.ui.categories.Category
import com.route.newappc35fri.ui.news.NewsFragment
import com.route.newappc35fri.ui.SettingsFragment
import com.route.newappc35fri.ui.news.NewsViewModel

class HomeActivity : AppCompatActivity() {

    val categoriesFragment = CategoriesFragment();

 lateinit  var  binding : ActivityHomeBinding
    lateinit var viewModele:NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

   binding = DataBindingUtil.setContentView(this,R.layout.activity_home)

   viewModele= ViewModelProvider(this).get(NewsViewModel::class.java)
        Log.e("home avtiv",viewModele.log.toString())
    binding.appBarHome.vm = viewModele

        pushFragment(categoriesFragment)


        categoriesFragment.onCategoryClickListener =
            object : CategoriesFragment.OnCategoryClickListener {
                override fun onCategoryClick(category: Category) {
                    pushFragment(NewsFragment.getInstance(category), true);
                }
            }
        binding.appBarHome.drawerIcon.setOnClickListener {
            binding.drawerLayout.open()
        }
       binding.categories.setOnClickListener {
            pushFragment(categoriesFragment)
        }
       binding.settings.setOnClickListener {
            pushFragment(SettingsFragment())
        }

    }



    fun pushFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val fragTransction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
        if (addToBackStack)
            fragTransction.addToBackStack("");
        fragTransction.commit()
        binding.drawerLayout.close()
    }

}