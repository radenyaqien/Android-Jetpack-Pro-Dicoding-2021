package id.radenyaqien.jetpackdicoding.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.jetpackdicoding.adapter.FavoriteMoviesAdapter
import id.radenyaqien.jetpackdicoding.databinding.FragmentFavMoviesBinding
import id.radenyaqien.jetpackdicoding.ui.viewmodels.FavoriteViewModel

@AndroidEntryPoint
class FavMoviesFragment : Fragment() {
    private val mAdapter by lazy { FavoriteMoviesAdapter() }
    private val viewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentFavMoviesBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.progress?.visibility = View.VISIBLE
        viewModel.getMoviesFav().observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
            binding?.progress?.visibility = View.GONE
        }

        binding?.rvMovies.apply {
            this?.setHasFixedSize(true)
            this?.adapter = mAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}