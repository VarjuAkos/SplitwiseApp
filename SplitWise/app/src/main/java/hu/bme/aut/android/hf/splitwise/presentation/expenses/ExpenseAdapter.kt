package hu.bme.aut.android.hf.splitwise.presentation.expenses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.hf.splitwise.data.entities.ExpenseEntity
import hu.bme.aut.android.hf.splitwise.databinding.ExpenseItemBinding
import hu.bme.aut.android.hf.splitwise.domain.model.Event
import hu.bme.aut.android.hf.splitwise.domain.model.Expense

class ExpenseAdapter(
    val onDelete: (Expense, Int) ->Unit
): RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {
    var items = mutableListOf<Expense>()
        private set

    inner class ExpenseViewHolder(private val binding: ExpenseItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(ex: Expense){
            binding.apply {
                paidByLabel.text= ex.payedby
                expenseNameLabel.text = ex.name
                expenseValueLabel.text = ex.amount.toString()

                deleteImageview.setOnClickListener {
                    onDelete(ex,items.indexOf(ex))
                }
            }
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ExpenseViewHolder (
        ExpenseItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItem(ex: Expense?){
        if (ex != null) {
            items.add(ex)
        }
        notifyDataSetChanged()
    }

    fun removeItemAtPosition(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }
}