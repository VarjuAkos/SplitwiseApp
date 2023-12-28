package hu.bme.aut.android.hf.splitwise.presentation.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.hf.splitwise.R
import hu.bme.aut.android.hf.splitwise.data.database.AppDatabase
import hu.bme.aut.android.hf.splitwise.data.entities.EventEntity
import hu.bme.aut.android.hf.splitwise.databinding.FragmentEventsBinding
import hu.bme.aut.android.hf.splitwise.domain.model.Event
import hu.bme.aut.android.hf.splitwise.domain.model.Expense
import hu.bme.aut.android.hf.splitwise.domain.model.Participant
import kotlin.concurrent.thread

class EventsFragment: EventListener, Fragment(R.layout.fragment_events) {

    private lateinit var binding: FragmentEventsBinding

    private lateinit var adapter: EventsAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        database = AppDatabase.getDatabase(requireContext().applicationContext)
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createNewEvenetFab.setOnClickListener {
            findNavController().navigate(R.id.action_fromEventsFragment_to_NewEventFragment)
        }


    }

    private fun initRecyclerView() {
        thread {
            var eventEntities = database.eventDao().getAllEvents()
            /*
            for (event in eventEntities)
                database.eventDao().deleteEvent(event)
            */
            requireActivity().runOnUiThread{
                val recyclerView = binding.existingEventsRecyclerView
                adapter = EventsAdapter(eventEntitiesToEvents(eventEntities),this)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
        }

    }
    private fun eventEntitiesToEvents(eventEntities :  List<EventEntity>):MutableList<Event>{
        var events = mutableListOf<Event>()
        for(i in 0 until eventEntities.size){
            var event = Event(
                name = eventEntities[i].name,
                date = eventEntities[i].date,
                participants = eventEntities[i].participants.toMutableList(),
                transactions = eventEntities[i].transactions.toMutableList(),
                budget = eventEntities[i].budget,
                average = eventEntities[i].average
            )
            events.add(event)
        }
        return events
    }

    override fun onClick(event: Event){
        val bundle = Bundle()
        bundle.putParcelable("newEvent",event)
        findNavController().navigate(R.id.action_eventsFragment_to_expenseFragment,bundle)
    }

}


interface EventListener {
    fun onClick(eventEntity: Event)
}