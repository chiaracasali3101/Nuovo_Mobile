package android.adapters

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unibo.android.domain.models.Film

class MovieAdapter(
    private val movieLines: MutableList<Film>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    // ViewHolder per gestire i componenti grafici della singola riga
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // inflate del layout standard per gli elementi della lista
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_list_item_1, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        // associo i dati del film alla TextView
        val currentMovie = movieLines[position]
        holder.titleText.text = currentMovie.titolo
    }

    override fun getItemCount(): Int {
        return movieLines.size
    }

    // per aggiornare i dati della RecyclerView
    fun refreshData(newMovies: List<Film>) {
        movieLines.clear()
        movieLines.addAll(newMovies)
        notifyDataSetChanged()
    }
}