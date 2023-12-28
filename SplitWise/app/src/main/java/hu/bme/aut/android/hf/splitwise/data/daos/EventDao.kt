package hu.bme.aut.android.hf.splitwise.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hu.bme.aut.android.hf.splitwise.data.entities.EventEntity
import hu.bme.aut.android.hf.splitwise.data.entities.ExpenseEntity

@Dao
interface EventDao {
    @Insert
    fun insertEvent(event: EventEntity) : Long

    @Query("SELECT * FROM event")
    fun getAllEvents(): List<EventEntity>

    @Update
    fun updateEvent(event: EventEntity)

    @Delete
    fun deleteEvent(event: EventEntity)
/*
    @Query("SELECT * FROM event WHERE name = :name")
    fun getEventByName(name: String): EventEntity
*/
    /*
    @Query("SELECT * FROM expenses WHERE name = :name")
    fun getExpenesesByEventName(name: String?): List<ExpenseEntity>
    */
}