package hu.bme.aut.android.hf.splitwise.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.bme.aut.android.hf.splitwise.domain.model.Participant
import hu.bme.aut.android.hf.splitwise.domain.model.Transaction

@Entity(tableName = "event")
data class EventEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name")  var name: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "average") var average: Float,
    @ColumnInfo(name = "budget") var budget: Float,
    @ColumnInfo(name = "participants") var participants: List<Participant>,
    @ColumnInfo(name = "transactions") var transactions: List<Transaction>
)