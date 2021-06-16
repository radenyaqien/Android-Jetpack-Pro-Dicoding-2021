package id.radenyaqien.jetpackdicoding

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.jetpackdicoding.databinding.ActivityDetailMoviesBinding
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.room.MoviesEntity
import id.radenyaqien.jetpackdicoding.ui.viewmodels.FavDetailViewModel
import id.radenyaqien.jetpackdicoding.utils.Dummy

@AndroidEntryPoint
class DetailMoviesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "EXTRA_MOVIES"
    }

    private lateinit var binding: ActivityDetailMoviesBinding
    private var model: PopularMovies? = null
    private var entity: MoviesEntity? = null
    private var id: Int? = null
    private val mapper by lazy { MoviesMapping() }
    private var isFavorite: Boolean = false
    private val viewModel: FavDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movies)
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = getString(R.string.detailmovies)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.mainToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24)
        binding.mainToolbar.setNavigationOnClickListener { finish() }
        model = intent.getParcelableExtra(EXTRA_MOVIES)
        observer()
        id = model?.id
        model?.posterPath.let {
            Picasso
                .get()
                .load(Dummy.image + it)
                .into(binding.imgThumbnail)
        }
        model?.releaseDate.let { binding.txtYear.text = it }
        model?.title?.let { binding.txtName.text = it }
        model?.genreIds.let { binding.txtGenre.text = it?.get(0).toString() }
        model?.overview.let { binding.txtOveview.text = it }

        model?.let { it ->
            viewModel.getMoviesById(it.id).observe(this) { tv ->
                handleUserDetailFromDb(tv)
            }
        }
        binding.fabButton.setOnClickListener {
            setFavorite()
        }
    }

    private fun observer() {
        viewModel.resultdeleteMovies.observe(this) {
            if (it) {
                Toast.makeText(
                    this,
                    "berhasil di hapus dari user Favorite",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.resultInsertmoviesDb.observe(this, {
            if (it) {
                id?.let { user ->
                    Toast.makeText(
                        this,
                        "berhasil di tambahkan ke Favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.getMoviesById(user).observe(this) { user1 ->
                        handleUserDetailFromDb(user1)
                    }
                }
            }

        })


    }

    private fun handleUserDetailFromDb(Entity: MoviesEntity?) {
        if (Entity == null) {
            isFavorite = false
            val icon = R.drawable.ic_baseline_favorite_border_24
            binding.fabButton.setImageResource(icon)
        } else {
            entity = Entity
            isFavorite = true
            val icon = R.drawable.ic_baseline_favorite_24
            binding.fabButton.setImageResource(icon)
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            entity?.let {
                viewModel.deleteMovies(it)
            }
        } else {
            entity = model?.let { mapper.from(it) }

            entity?.let { it1 ->
                viewModel.insertmovies(
                    it1
                )
            }

        }
    }
}