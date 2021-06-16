package id.radenyaqien.jetpackdicoding.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.radenyaqien.jetpackdicoding.R
import id.radenyaqien.jetpackdicoding.adapter.FavoriteTvAdapter
import id.radenyaqien.jetpackdicoding.databinding.FavoriteTvFragmentBinding
import id.radenyaqien.jetpackdicoding.ui.viewmodels.FavoriteViewModel

@AndroidEntryPoint
class FavoriteTvFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels()
    private val mAdapter by lazy { FavoriteTvAdapter() }
    private lateinit var binding: FavoriteTvFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_tv_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progress.visibility = View.VISIBLE
        viewModel.getTvFav().observe(viewLifecycleOwner) {
            if (it != null) {
                mAdapter.submitList(it)
                binding.progress.visibility = View.GONE
            }

        }
        binding.rvTv.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }


}