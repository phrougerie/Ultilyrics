package fr.phrougerie.ultilyrics.data.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.phrougerie.ultilyrics.BR
import fr.phrougerie.ultilyrics.data.model.tracks.Track
import fr.phrougerie.ultilyrics.databinding.ListTextTrackViewBinding

class TracksAdapter(private var tracksSet : List<Track>) : RecyclerView.Adapter<TracksAdapter.TracksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListTextTrackViewBinding.inflate(inflater)
        return TracksViewHolder(binding)

    }

    override fun getItemCount() = tracksSet.size

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) = holder.bind(getTrack(position))
    inner class TracksViewHolder(val binding : ListTextTrackViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(track : Track){
            binding.setVariable(BR.track,track)
            binding.executePendingBindings()
        }
    }

    fun getTrack ( position : Int) : Track {
        return tracksSet[position]
    }

    fun  setData(newData : ArrayList<Track>  ){
        this.tracksSet = newData
        this.notifyDataSetChanged()
    }


}


