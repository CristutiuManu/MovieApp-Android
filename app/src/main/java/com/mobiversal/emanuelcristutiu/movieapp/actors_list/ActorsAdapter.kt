package com.mobiversal.emanuelcristutiu.movieapp.actors_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiversal.emanuelcristutiu.movieapp.R
import com.mobiversal.emanuelcristutiu.movieapp.actor.FavoriteActor
import com.mobiversal.emanuelcristutiu.movieapp.utils.ImageLoader
import kotlinx.android.synthetic.main.actors_list_item.view.*

class ActorsAdapter(private val actorsList: List<FavoriteActor>) : RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.actors_list_item, parent, false))
    }
    override fun getItemCount(): Int {
        return actorsList?.size ?: 0
    }
    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        val actor: FavoriteActor? = actorsList?.get(position)
        actor?.let {
            holder.bind(actor)
        }
    }
    inner class ActorsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(actor: FavoriteActor) {

            val imgUrl = actor.imgUrl
            if (!imgUrl.isNullOrEmpty()) {
                ImageLoader.loadImage("https://image.tmdb.org/t/p/original" +actor.imgUrl, itemView.iv_actor, itemView.context)
            }
            itemView.tv_actor_name.text = actor.name
            itemView.cb_selected.setOnCheckedChangeListener(null)
            itemView.cb_selected.isChecked = actor.isSelected
            itemView.cb_selected.setOnCheckedChangeListener {
                    buttonView, isChecked -> actor.isSelected = isChecked
            }
        }
    }
}