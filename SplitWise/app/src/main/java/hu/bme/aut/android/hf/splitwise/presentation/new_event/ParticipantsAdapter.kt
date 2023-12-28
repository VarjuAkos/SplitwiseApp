package hu.bme.aut.android.hf.splitwise.presentation.new_event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.hf.splitwise.databinding.ParticipantsItemBinding

class ParticipantsAdapter(
    val onDelete: (String, Int) -> Unit
): RecyclerView.Adapter<ParticipantsAdapter.ParticipantsViewHolder>() {

    var items = mutableListOf<String>()
        private set

    inner class ParticipantsViewHolder(private val binding: ParticipantsItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String) {
            binding.apply {
                nameLabel.text = name

                deleteImageview.setOnClickListener { onDelete(name, items.indexOf(name)) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ParticipantsViewHolder (
        ParticipantsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ParticipantsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItem(item: String) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun removeItemAtPosition(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }
}