package hu.bme.aut.android.hf.splitwise.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hu.bme.aut.android.hf.splitwise.data.entities.ParticipantEntity

@Dao
interface ParticipantDao {
    @Insert
    fun insertParticipant(participant: ParticipantEntity)

    @Update
    fun updateParticipant(participant: ParticipantEntity)

    @Delete
    fun deleteParticipant(participant: ParticipantEntity)

    @Query("SELECT * FROM participants")
    fun getParticipantsForEvent(): List<ParticipantEntity>
}