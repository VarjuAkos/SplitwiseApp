package hu.bme.aut.android.hf.splitwise.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Expense(//az a Participant akinek a kiadasa Expense
    var payedby: String, //kiadas neve
    var name: String,
    var amount: Float
) :
    Parcelable
