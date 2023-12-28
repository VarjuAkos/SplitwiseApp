package hu.bme.aut.android.hf.splitwise.presentation.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.hf.splitwise.data.entities.EventEntity
import hu.bme.aut.android.hf.splitwise.databinding.EventItemBinding
import hu.bme.aut.android.hf.splitwise.domain.model.Event

class EventsAdapter(
    var items: MutableList<Event>,
    val listener: EventListener
): RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventViewHolder (
        EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = items[position]
        holder.binding.titleLabel.text=event.name
        holder.binding.dateLabel.text=event.date
        holder.binding.root.setOnClickListener{
            listener.onClick(event)
        }

    }

    override fun getItemCount(): Int = items.size

    inner class EventViewHolder(val binding: EventItemBinding): RecyclerView.ViewHolder(binding.root)




}