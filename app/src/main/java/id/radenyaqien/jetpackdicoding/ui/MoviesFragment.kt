package id.radenyaqien.jetpackdicoding.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.jetpackdicoding.DetailActivity
import id.radenyaqien.jetpackdicoding.MainViewModel
import id.radenyaqien.jetpackdicoding.adapter.MoviesAdapter
import id.radenyaqien.jetpackdicoding.databinding.FragmentMoviesBinding
import id.radenyaqien.jetpackdicoding.models.Movies
import id.radenyaqien.jetpackdicoding.utils.EspressoIdlingResource

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding

    private val mAdapter by lazy { MoviesAdapter() }
    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL
                )
            )
        }
        EspressoIdlingResource.increment()
        binding.progress.visibility = View.VISIBLE

        viewModel.getMovies().observe(viewLifecycleOwner, { list ->
            binding.progress.visibility = View.GONE
            mAdapter.addItems(list)
            EspressoIdlingResource.decrement()
        })


        mAdapter.listener = { _: View, item: Movies, _: Int ->
            Intent(requireContext(), DetailActivity::class.java).also {
                it.putExtra("EXTRA_MOVIES", item)
                startActivity(it)
            }
        }
    }


}