package hu.bme.aut.android.hf.splitwise.presentation.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.hf.splitwise.databinding.TransactionItemBinding
import hu.bme.aut.android.hf.splitwise.domain.model.Transaction

class TransactionAdapter():RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    var items = mutableListOf<Transaction>()
        private set
    inner class TransactionViewHolder(private val binding: TransactionItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(transaction : Transaction){
            binding.paidByLabel.text ="By:" + transaction.paidBy.name
            binding.paidToLabel.text ="To:" + transaction.paidTo.name
            binding.transactionValueLabel.text = transaction.amount.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TransactionViewHolder (
        TransactionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(items[position])
    }
    fun addItems(transactions : MutableList<Transaction>){
        items.addAll(transactions)
    }

    fun removeItemAtPosition(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }
}