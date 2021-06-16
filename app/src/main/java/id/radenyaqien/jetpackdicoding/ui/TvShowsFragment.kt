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
import id.radenyaqien.jetpackdicoding.DetailTvShowsActivity
import id.radenyaqien.jetpackdicoding.adapter.popularAdapter
import id.radenyaqien.jetpackdicoding.databinding.FragmentTvShowsBinding
import id.radenyaqien.jetpackdicoding.models.PopularTv
import id.radenyaqien.jetpackdicoding.ui.viewmodels.TvViewModel


@AndroidEntryPoint
class TvShowsFragment : Fragment() {

    private val mAdapter by lazy { popularAdapter() }
    private lateinit var binding: FragmentTvShowsBinding
    private val viewModel: TvViewModel by viewModels()

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

        viewModel.getTv()

        viewModel.response.observe(viewLifecycleOwner, {
            binding.progress.visibility = View.GONE
            mAdapter.addItems(it)

        })
        mAdapter.listener = { _: View, item: PopularTv, _: Int ->
            Intent(requireContext(), DetailTvShowsActivity::class.java).also {
                it.putExtra(DetailTvShowsActivity.EXTRA_TVSHOWS, item)
                startActivity(it)
            }
        }
    }

}