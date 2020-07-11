package fr.phrougerie.ultilyrics.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.phrougerie.ultilyrics.R
import fr.phrougerie.ultilyrics.data.Adapters.TracksAdapter
import fr.phrougerie.ultilyrics.data.Listeners.OnItemClickListener
import fr.phrougerie.ultilyrics.data.Listeners.addOnItemClickListener
import fr.phrougerie.ultilyrics.data.model.tracks.Track
import fr.phrougerie.ultilyrics.data.viewModel.FavouriteFragmentViewModel
import kotlinx.android.synthetic.main.fragment_search.view.*


class FavouriteFragment : Fragment() {
    private  lateinit var tracksAdapter : TracksAdapter
    private lateinit var  viewManager: RecyclerView.LayoutManager
    private lateinit var  tracksVM : FavouriteFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tracksVM = ViewModelProvider(this).get(FavouriteFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_favourite,container, false)
        tracksVM.trackData.observe(this, Observer {
            tracksAdapter = TracksAdapter(it)
            view.tracks_recycler_view.apply {
                layoutManager = viewManager
                adapter = tracksAdapter
            }
            view.tracks_recycler_view.addOnItemClickListener(object :
                OnItemClickListener {
                override fun onItemClicked(position: Int, view: View) {
                    val track = (tracksAdapter).getTrack(position)
                    val action = FavouriteFragmentDirections.actionFavouriteFragmentToLyricsFragment(track,true)
                    Navigation.findNavController(view).navigate(action)
                }
            })
        })

        viewManager = GridLayoutManager(context,1)

        return view
    }

}
