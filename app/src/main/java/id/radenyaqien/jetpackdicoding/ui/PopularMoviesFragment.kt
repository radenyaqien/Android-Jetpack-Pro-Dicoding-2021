package id.radenyaqien.jetpackdicoding.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.jetpackdicoding.DetailMoviesActivity
import id.radenyaqien.jetpackdicoding.adapter.MoviesAdapter
import id.radenyaqien.jetpackdicoding.databinding.FragmentPopularMoviesBinding
import id.radenyaqien.jetpackdicoding.models.PopularMovies
import id.radenyaqien.jetpackdicoding.ui.viewmodels.MoviesViewModel


@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val mAdapter by lazy { MoviesAdapter() }
    private val viewmodel: MoviesViewModel by viewModels()
    private lateinit var binding: FragmentPopularMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progress.visibility = View.VISIBLE
        viewmodel.getmoviesApi()

        setupRV()
        viewmodel.response.observe(viewLifecycleOwner) {
            binding.progress.visibility = View.GONE
            mAdapter.addItems(it)

        }
    }

    private fun setupRV() {
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
            setHasFixedSize(true)
        }

        mAdapter.listener = { _: View, item: PopularMovies, _: Int ->
            startActivity(
                Intent(requireContext(), DetailMoviesActivity::class.java)
                    .putExtra(DetailMoviesActivity.EXTRA_MOVIES, item)
            )

        }

    }
}