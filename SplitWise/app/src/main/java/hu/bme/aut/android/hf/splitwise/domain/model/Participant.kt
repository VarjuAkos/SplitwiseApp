package hu.bme.aut.android.hf.splitwise.domain.model

import android.os.Parcelable
import android.util.Log
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import kotlin.String

@Parcelize
data class Participant(
    var name: String,
    var expenses: MutableList<Expense> = mutableListOf(),
    var balance: Float = 0F,
    var eventAverage: Float,
    var diff:Float
) :
   Parcelable {

    fun updateP(f:Float) {
        eventAverage = f
        balance = 0F
        for (i in expenses.indices) {
            balance += expenses[i].amount
        }
        diff=eventAverage-balance
    }

}
