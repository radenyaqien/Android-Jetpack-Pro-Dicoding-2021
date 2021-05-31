package id.radenyaqien.jetpackdicoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.jetpackdicoding.databinding.ActivityMainBinding
import id.radenyaqien.jetpackdicoding.ui.MoviesFragment
import id.radenyaqien.jetpackdicoding.ui.PopularMoviesFragment
import id.radenyaqien.jetpackdicoding.ui.TvShowsFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        MoviesFragment()

                    }
                    1 -> {
                        TvShowsFragment()
                    }
                    else -> {
                        PopularMoviesFragment()
                    }
                }
            }

        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Movies"
                1 -> "TV Shows"
                else -> "Popular Movies"
            }

        }.attach()
    }
}