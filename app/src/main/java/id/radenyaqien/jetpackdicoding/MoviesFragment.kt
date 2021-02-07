package id.radenyaqien.jetpackdicoding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.radenyaqien.jetpackdicoding.adapter.MoviesAdapter
import id.radenyaqien.jetpackdicoding.databinding.FragmentMoviesBinding
import id.radenyaqien.jetpackdicoding.models.Movies

private const val ARG_PARAM1 = "param1"

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesBinding
    private var param1: List<Movies>? = null
    private val mAdapter by lazy { MoviesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelableArrayList(ARG_PARAM1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.rvMovie.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            addItemDecoration(
                    DividerItemDecoration(requireContext(),
                            LinearLayoutManager.HORIZONTAL)
            )
        }
        param1?.let { mAdapter.addItems(it) }

        mAdapter.listener = { _: View, item: Movies, _: Int ->
            Intent(requireContext(), DetailActivity::class.java).also {
                it.putExtra("EXTRA_MOVIES", item)
                startActivity(it)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<Movies>) =
                MoviesFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_PARAM1, param1)

                    }
                }
    }
}