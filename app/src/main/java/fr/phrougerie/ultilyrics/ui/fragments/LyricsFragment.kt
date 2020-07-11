package fr.phrougerie.ultilyrics.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import fr.phrougerie.ultilyrics.R
import fr.phrougerie.ultilyrics.data.model.tracks.Track
import fr.phrougerie.ultilyrics.data.viewModel.LyricsFragViewModel
import fr.phrougerie.ultilyrics.data.viewModel.LyricsFragViewModelFactory
import fr.phrougerie.ultilyrics.databinding.FragmentLyricsBinding
import fr.phrougerie.ultilyrics.ui.activity.MainActivity

class LyricsFragment : Fragment(){
    val args: LyricsFragmentArgs by navArgs()

    private lateinit var trackVM : LyricsFragViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val track = args.track
        val factory = LyricsFragViewModelFactory(track)
        trackVM = ViewModelProvider(this,factory).get(LyricsFragViewModel::class.java)
        trackVM.trackData.observe(this, Observer {

            if(track.lyrics==""){
                AlertDialog.Builder(activity!!)
                    .setTitle(R.string.message_no_lycrics)
                    .setMessage(R.string.no_lyrics)
                    .setNeutralButton(android.R.string.ok, null)
                    .show()
                val action = LyricsFragmentDirections.actionLyricsFragmentToSearchFragment()
                view?.let { Navigation.findNavController(it).navigate(action) }
            }

        })

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val binding : FragmentLyricsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lyrics, container, false)
        binding.tracksVM = trackVM
        binding.lifecycleOwner = this
        return  binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favourite -> {
                trackVM.saveTrack()
                true
            }
            R.id.delete -> {
                trackVM.removeTrack()
                val action = LyricsFragmentDirections.actionLyricsFragmentToFavouriteFragment()
                view?.let { Navigation.findNavController(it).navigate(action) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_song_menu,menu)

        if(args.isFavourite){
            menu.findItem(R.id.favourite).isVisible = false
        }
        else{
            menu.findItem(R.id.delete).isVisible = false
        }
    }


}