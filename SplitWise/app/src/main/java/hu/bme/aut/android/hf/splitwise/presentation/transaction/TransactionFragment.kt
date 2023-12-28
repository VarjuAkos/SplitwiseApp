package hu.bme.aut.android.hf.splitwise.presentation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.hf.splitwise.R
import hu.bme.aut.android.hf.splitwise.data.database.AppDatabase
import hu.bme.aut.android.hf.splitwise.data.entities.EventEntity
import hu.bme.aut.android.hf.splitwise.databinding.ExpenseItemBinding
import hu.bme.aut.android.hf.splitwise.databinding.FragmentTransactionBinding
import hu.bme.aut.android.hf.splitwise.domain.model.Event
import hu.bme.aut.android.hf.splitwise.domain.model.Transaction
import kotlin.concurrent.thread

class TransactionFragment : Fragment(R.layout.fragment_transaction){

    private lateinit var binding: FragmentTransactionBinding
    private lateinit var adapter: TransactionAdapter
    lateinit var event : Event
    private lateinit var database : AppDatabase
    private var cnt:Long = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater,container,false)
        event = arguments?.getParcelable<Event>("event")!!
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TransactionAdapter()
        database= AppDatabase.getDatabase(requireContext())
        binding.apply {

            transactionRecyclerView.adapter = adapter
            adapter.addItems(event.transactions)
            saveEvent.setOnClickListener{

                saveEvent()
                cnt++
                findNavController().navigate(R.id.action_transactionFragment_to_eventsFragment)
            }


        }
    }
    private fun onDelete(transaction: Transaction, atPosition: Int) {
        adapter.removeItemAtPosition(atPosition)
    }
    fun saveEvent(){
        val eventEntity =EventEntity(
            id=cnt,
            name = event.name,
            date = event.date,
            average = event.average,
            budget = event.budget,
            participants = event.participants.toList(),
            transactions = event.transactions.toList()
        )
        thread {
            database.eventDao().insertEvent(eventEntity)
        }

    }

}