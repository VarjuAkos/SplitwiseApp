package hu.bme.aut.android.hf.splitwise.presentation.new_event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.hf.splitwise.R
import hu.bme.aut.android.hf.splitwise.data.database.AppDatabase
import hu.bme.aut.android.hf.splitwise.data.entities.EventEntity
import hu.bme.aut.android.hf.splitwise.databinding.FragmentNewEventBinding
import hu.bme.aut.android.hf.splitwise.domain.model.Event
import hu.bme.aut.android.hf.splitwise.domain.model.Participant
import kotlin.concurrent.thread

class NewEventFragment : Fragment(R.layout.fragment_new_event) {

    private lateinit var binding: FragmentNewEventBinding
    private lateinit var adapter: ParticipantsAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ParticipantsAdapter(this::onDelete)
        database = AppDatabase.getDatabase(requireContext().applicationContext)



        binding.apply {

            participantsRecyclerView.adapter = adapter

            addButton.setOnClickListener {
                newParticipantInput.text.toString().takeIf { it.isNotEmpty() }?.let {
                    adapter.addItem(it)

                }
            }

            createNewButton.setOnClickListener {
                if (validateInputs()) {
                    val event = createEvent()
                    Log.e("asd",event.toString())
                    /*
                    val bundle = Bundle().apply {
                        putParcelable("newEvent",createEvent())
                    }*/

                    val bundle = bundleOf(Pair("newEvent",event))
                    bundle.putParcelable("newEvent",event)
                    findNavController().navigate(R.id.action_newEventFragment_to_expenseFragment,bundle)
                }
            }


        }

    }

    fun EventToEventEntity(event: Event) : EventEntity{
        return EventEntity(
            name = event.name,
            date = event.date,
            average = event.average,
            budget = event.budget,
            participants = event.participants,
            transactions = event.transactions!!
        )
    }

    private fun validateInputs(): Boolean {
        if (binding.nameInput.text.toString().isEmpty()) {
            binding.nameInputLayout.error = "Cannot be empty"
            return false
        }

        if (binding.dateInput.text.toString().isEmpty()) {
            binding.dateInputLayout.error = "Cannot be empty"
            return false
        }

        if (adapter.itemCount == 0) {
            Toast.makeText(requireContext(), "Add at least one participant", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun onDelete(name: String, atPosition: Int) {
        adapter.removeItemAtPosition(atPosition)
    }

    private fun createEvent():Event {
        var event = Event(
            name = binding.nameInput.text.toString(),
            date = binding.dateInput.text.toString(),
            participants = mutableListOf(),
            transactions = mutableListOf()
        )

        adapter.items.forEach {
            event.participants.add(
                Participant(
                    name = it,
                    expenses = mutableListOf(),
                    balance = 0F,
                    eventAverage = 0F,
                    diff = 0F
                )
            )
        }
        Log.e("msg",event.toString())
        return event

    }

}