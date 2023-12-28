package hu.bme.aut.android.hf.splitwise.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Transaction(
    var paidBy: Participant,
    var paidTo: Participant,
    var amount: Float
) : Parcelable {

    override fun toString(): String {
        return "paidBy=${paidBy.name}, paidTo=${paidTo.name}, amount=$amount)"
    }
}