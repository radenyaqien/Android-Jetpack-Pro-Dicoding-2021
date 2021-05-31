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
import id.radenyaqien.jetpackdicoding.adapter.TvAdapter
import id.radenyaqien.jetpackdicoding.databinding.FragmentTvShowsBinding
import id.radenyaqien.jetpackdicoding.models.TvShows
import id.radenyaqien.jetpackdicoding.utils.EspressoIdlingResource


@AndroidEntryPoint
class TvShowsFragment : Fragment() {

    private val mAdapter by lazy { TvAdapter() }
    private lateinit var binding: FragmentTvShowsBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTv.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL
                )
            )
        }
        binding.progress.visibility = View.VISIBLE
        EspressoIdlingResource.increment()
        viewModel.getTvShows().observe(viewLifecycleOwner, {
            binding.progress.visibility = View.GONE
            mAdapter.addItems(it)
            EspressoIdlingResource.decrement()
        })
        mAdapter.listener = { _: View, item: TvShows, _: Int ->
            Intent(requireContext(), DetailActivity::class.java).also {
                it.putExtra("EXTRA_TVSHOWS", item)
                startActivity(it)
            }
        }
    }

}