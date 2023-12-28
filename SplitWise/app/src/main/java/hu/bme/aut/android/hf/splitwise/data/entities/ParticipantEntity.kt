package hu.bme.aut.android.hf.splitwise.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import hu.bme.aut.android.hf.splitwise.domain.model.Event
import hu.bme.aut.android.hf.splitwise.domain.model.Expense
import hu.bme.aut.android.hf.splitwise.domain.model.Participant

@Entity(tableName = "participants")
data class ParticipantEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "expenses") var expense: List<Expense>,
    @ColumnInfo(name = "balance") var balance: Float

)