package fr.phrougerie.ultilyrics.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.phrougerie.ultilyrics.data.Listeners.OnItemClickListener
import fr.phrougerie.ultilyrics.data.Listeners.addOnItemClickListener
import fr.phrougerie.ultilyrics.R
import fr.phrougerie.ultilyrics.data.Adapters.TracksAdapter
import fr.phrougerie.ultilyrics.data.viewModel.SearchFragmentViewModel
import kotlinx.android.synthetic.main.fragment_lyrics.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment(){
    private lateinit var  tracksVM : SearchFragmentViewModel
    private  lateinit var tracksAdapter : TracksAdapter
    private lateinit var  viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        val view = inflater.inflate(R.layout.fragment_search,container, false)
        viewManager = GridLayoutManager(activity,1)
        view.tracks_recycler_view.apply {
            layoutManager = viewManager
            adapter = tracksAdapter
        }
        view.tracks_recycler_view.addOnItemClickListener(object :
            OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val track = (tracksAdapter).getTrack(position)
                val action = SearchFragmentDirections.actionSearchFragmentToLyricsFragment(track,false)
                Navigation.findNavController(view).navigate(action)
            }
        })
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.search.setOnClickListener {
            if(artistText.text.toString()=="" && trackText.text.toString()==""){
                AlertDialog.Builder(activity!!)
                    .setTitle(R.string.search_incomplete)
                    .setMessage(R.string.element_missing)
                    .setNeutralButton(android.R.string.ok, null)
                    .show()
            }
            else{
                tracksVM.search(artistText.text.toString(),trackText.text.toString())
            }



        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tracksVM = ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
        if(tracksVM.tracks.value == null){
            tracksVM.tracks.value = ArrayList()
        }
        tracksVM.tracks.value?.let{
            tracksAdapter = TracksAdapter(it)
        }
        tracksVM.tracks.observe(this, Observer {
            tracksAdapter.setData(it)
        })
    }




}