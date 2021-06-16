package id.radenyaqien.jetpackdicoding

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.jetpackdicoding.databinding.ActivityDetailTvShowsBinding
import id.radenyaqien.jetpackdicoding.models.PopularTv
import id.radenyaqien.jetpackdicoding.room.TvEntity
import id.radenyaqien.jetpackdicoding.ui.viewmodels.FavDetailViewModel
import id.radenyaqien.jetpackdicoding.utils.Dummy

@AndroidEntryPoint
class DetailTvShowsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TVSHOWS = "EXTRA_TVSHOWS"
    }

    private lateinit var binding: ActivityDetailTvShowsBinding
    private var model: PopularTv? = null
    private var entity: TvEntity? = null
    private var id: Int? = null
    private val mapper by lazy { TvMapping() }
    private var isFavorite: Boolean = false
    private val viewModel: FavDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tv_shows)
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = getString(R.string.detailtvshow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.mainToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24)
        binding.mainToolbar.setNavigationOnClickListener { finish() }
        model = intent.getParcelableExtra(EXTRA_TVSHOWS)
        observer()
        id = model?.id
        model?.posterPath.let {
            Picasso
                .get()
                .load(Dummy.image + it)
                .into(binding.imgThumbnail)
        }
        model?.firstAirDate.let { binding.txtYear.text = it }
        model?.name?.let { binding.txtName.text = it }
        model?.genreIds.let { binding.txtGenre.text = it?.get(0).toString() }
        model?.overview.let { binding.txtOveview.text = it }

        model?.let { it ->
            viewModel.getTvById(it.id).observe(this) { tv ->
                handleUserDetailFromDb(tv)
            }
        }
        binding.fabButton.setOnClickListener {
            setFavorite()
        }
    }

    private fun observer() {
        viewModel.resultDeletetv.observe(this) {
            if (it) {
                Toast.makeText(
                    this,
                    "berhasil di hapus dari user Favorite",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.resultInsertTv.observe(this, {
            if (it) {
                id?.let { user ->
                    Toast.makeText(
                        this,
                        "berhasil di tambahkan ke Favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.getTvById(user).observe(this) { user1 ->
                        handleUserDetailFromDb(user1)
                    }
                }
            }

        })


    }

    private fun handleUserDetailFromDb(tvEntity: TvEntity?) {
        if (tvEntity == null) {
            isFavorite = false
            val icon = R.drawable.ic_baseline_favorite_border_24
            binding.fabButton.setImageResource(icon)
        } else {
            entity = tvEntity
            isFavorite = true
            val icon = R.drawable.ic_baseline_favorite_24
            binding.fabButton.setImageResource(icon)
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            entity?.let {
                viewModel.deletetv(it)
            }
        } else {
            entity = model?.let { mapper.from(it) }

            entity?.let { it1 ->
                viewModel.inserttv(
                    it1
                )
            }

        }
    }
}