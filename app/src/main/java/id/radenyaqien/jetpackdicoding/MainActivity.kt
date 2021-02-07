package id.radenyaqien.jetpackdicoding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import id.radenyaqien.jetpackdicoding.databinding.ActivityMainBinding
import id.radenyaqien.jetpackdicoding.models.Movies
import id.radenyaqien.jetpackdicoding.models.TvShows

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val movies = viewModel.getMovies()
        val tv = viewModel.getTvShows()
        setupViewPager(movies, tv)
    }

    private fun setupViewPager(movies: ArrayList<Movies>, tv: ArrayList<TvShows>) {
        binding.viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 2
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        MoviesFragment.newInstance(movies)
                    }
                    else -> {
                        TvShowsFragment.newInstance(tv)
                    }
                }
            }

        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Movies"
                else -> "TV Shows"
            }

        }.attach()
    }
}