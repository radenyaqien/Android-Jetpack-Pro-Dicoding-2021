package id.radenyaqien.jetpackdicoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.jetpackdicoding.databinding.FavoriteActivityBinding
import id.radenyaqien.jetpackdicoding.ui.favorite.FavMoviesFragment
import id.radenyaqien.jetpackdicoding.ui.favorite.FavoriteTvFragment

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: FavoriteActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.favorite_activity)
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = getString(R.string.myfavmovies)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.mainToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24)
        binding.mainToolbar.setNavigationOnClickListener { finish() }
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 2
            override fun createFragment(position: Int): Fragment {
                return when (position) {

                    0 -> {
                        FavoriteTvFragment()
                    }
                    else -> {
                        FavMoviesFragment()
                    }
                }
            }

        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            when (position) {
                0 -> getString(R.string.tvshows)
                else -> getString(R.string.movies)
            }.also { tab.text = it }

        }.attach()
    }
}