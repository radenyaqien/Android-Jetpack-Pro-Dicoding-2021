package id.radenyaqien.jetpackdicoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import id.radenyaqien.jetpackdicoding.databinding.ActivityDetailBinding
import id.radenyaqien.jetpackdicoding.models.Movies
import id.radenyaqien.jetpackdicoding.models.TvShows

class DetailActivity : AppCompatActivity() {
    private var movies: Movies? = null
    private var tvShows: TvShows? = null
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        if (intent.hasExtra("EXTRA_MOVIES")) {
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
        } else if (intent.hasExtra("EXTRA_TVSHOWS")) {
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


    }
}