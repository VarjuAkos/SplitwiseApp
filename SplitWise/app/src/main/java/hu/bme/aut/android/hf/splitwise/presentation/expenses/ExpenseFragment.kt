package hu.bme.aut.android.hf.splitwise.presentation.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.hf.splitwise.R
import hu.bme.aut.android.hf.splitwise.databinding.FragmentExpenseBinding
import hu.bme.aut.android.hf.splitwise.domain.model.Event
import hu.bme.aut.android.hf.splitwise.domain.model.Expense
import hu.bme.aut.android.hf.splitwise.domain.model.Participant

class ExpenseFragment : Fragment(R.layout.fragment_expense) {
    private lateinit var binding: FragmentExpenseBinding
    private lateinit var adapter: ExpenseAdapter
    lateinit var event :Event


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpenseBinding.inflate(inflater, container, false)
        event = arguments?.getParcelable<Event>("newEvent")!!
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExpenseAdapter(this::OnDelete)
        binding.apply {
            expenseRecyclerView.adapter = adapter
            addButton.setOnClickListener{
                if (validateInputs()){
                    var ex = Expense(binding.paidByInput.text.toString(),
                        binding.expenseNameInput.text.toString(),
                        binding.expenseValueInput.text.toString().toFloat())
                    adapter.addItem(ex)

                }
                else {
                    var participants =" "
                    for (i in 0 until event.participants.size){
                        participants += event.participants[i].name
                        participants +=" "
                    }
                    Toast.makeText(requireContext(),
                        "Something went wrong, check inputs Participants:$participants",Toast.LENGTH_SHORT).show()
                }
            }
            calculateButton.setOnClickListener{
                for (i in 0 until adapter.items.size){
                    val ex = adapter.items[i]
                    val participant = event.findP(ex.payedby)
                    if (participant != null) {
                        event.addExpense(participant,ex)
                    }
                }
                event.transactions.clear()
                event.update()
                event.szamol()

                val bundle = bundleOf(Pair("event",event))
                bundle.putParcelable("event",event)
                findNavController().navigate(R.id.action_expenseFragment_to_transactionFragment,bundle)
            }
        }


        for (participant in event.participants) {
            for (expenses in participant.expenses)
                adapter.addItem(expenses)
        }
    }



    private fun validateInputs(): Boolean{
        if(binding.paidByInput.text.toString().isEmpty()){
            binding.paidByInput.error = "Cannot be empty"
            return false
        }

        if(findParticipant()==null)
           return false

        if(binding.expenseNameInput.text.toString().isEmpty()){
            binding.expenseNameInput.error = "Cannot be empty"
            return false
        }
        if(binding.expenseValueInput.text.toString().isEmpty()){
            binding.expenseValueInput.error = "Cannot be empty"
            return false
        }
        if(binding.expenseValueInput.text.toString().toFloat() < 0F){
            Toast.makeText(requireContext(), "Expense value must be greater than 0", Toast.LENGTH_SHORT)
                .show()

        }
        return true
    }
    private fun findParticipant(): Participant? {
        for (participant in event?.participants!!){
            if(participant.name==binding.paidByInput.text.toString())
                return participant
        }
        return null
    }
    private fun OnDelete(expense: Expense, i: Int) {
        adapter.removeItemAtPosition(i)
    }

}