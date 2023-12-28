package hu.bme.aut.android.hf.splitwise.data.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import hu.bme.aut.android.hf.splitwise.domain.model.Participant

@Entity(tableName = "expenses")
data class ExpenseEntity (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "amount") var amount: Float,
    @ColumnInfo(name = "owner") var participant: String
)
