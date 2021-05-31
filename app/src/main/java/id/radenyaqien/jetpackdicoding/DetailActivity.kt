package id.radenyaqien.jetpackdicoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import id.radenyaqien.jetpackdicoding.databinding.ActivityDetailBinding
import id.radenyaqien.jetpackdicoding.models.Movies
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.models.TvShows
import id.radenyaqien.jetpackdicoding.utils.Dummy

class DetailActivity : AppCompatActivity() {
    private var movies: Movies? = null
    private var tvShows: TvShows? = null
    private var popular: PopularMovies? = null
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        when {
            intent.hasExtra("EXTRA_MOVIES") -> {
                movies = intent.getParcelableExtra("EXTRA_MOVIES")

                movies?.image?.let {
                    Picasso
                        .get()
                        .load(it)
                        .into(binding.imgThumbnail)
                }
                movies?.Year.let { binding.txtYear.text = it }
                movies?.name?.let { binding.txtName.text = it }
                movies?.genre.let { binding.txtGenre.text = it }
                movies?.overview.let { binding.txtOveview.text = it }
            }
            intent.hasExtra("EXTRA_TVSHOWS") -> {
                tvShows = intent.getParcelableExtra("EXTRA_TVSHOWS")

                tvShows?.image?.let {
                    Picasso
                        .get()
                        .load(it)
                        .into(binding.imgThumbnail)
                }
                tvShows?.Year.let { binding.txtYear.text = it }
                tvShows?.name?.let { binding.txtName.text = it }
                tvShows?.genre.let { binding.txtGenre.text = it }
                tvShows?.overview.let { binding.txtOveview.text = it }
            }
            intent.hasExtra("EXTRA_POPULAR") -> {
                popular = intent.getParcelableExtra("EXTRA_POPULAR")


                Picasso
                    .get()
                    .load(Dummy.image + popular?.posterPath)
                    .into(binding.imgThumbnail)
                binding.txtYear.text = popular?.firstAirDate
                binding.txtName.text = popular?.originalName
                binding.txtGenre.text = popular?.originCountry?.get(0) ?: "-"
                binding.txtOveview.text = popular?.overview
                binding.layoutGenre.text = "Original Country"

            }
        }


    }
}