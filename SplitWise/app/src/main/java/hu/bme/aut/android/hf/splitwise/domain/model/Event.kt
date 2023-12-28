package hu.bme.aut.android.hf.splitwise.domain.model

import android.os.Parcelable
import android.util.Log
import hu.bme.aut.android.hf.splitwise.data.entities.EventEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    var name: String,
    var date: String,
    var participants: MutableList<Participant> = mutableListOf(),
    var transactions: MutableList<Transaction> = mutableListOf(),
    var budget: Float = 0F,
    var average: Float = 0F
) : Parcelable {

    fun update() {
        budget = 0F
        for (i in participants.indices) {
            for (j in 0 until participants[i].expenses.size) {
                if (!(participants[i].expenses.isEmpty()))
                    budget = (budget + participants[i].expenses[j].amount)
            }
        }
        average = budget / participants.size
        for (i in participants.indices) {
            participants[i].updateP(average)
        }
    }

    fun szamol() {
        for (i in participants.indices) { //ciklus végig megy az összes részvevőn
            if (participants[i].diff < 0) {    //tartoznak neki
                for (j in participants.indices) {
                    if (participants[i] !== participants[j]) {   //ha nem ugyan az a i mint a j
                        if (participants[j].diff > 0 && participants[i].balance!= average) { //tarzozik
                            var keresztmetszet: Float //belemegy ha j-nek van mit kifizetnie

                            val ij =
                                participants[i].diff.compareTo(-participants[j].diff) //j.k-->-j.k igy az is pozitív

                            if (ij == 0) {
                                keresztmetszet = participants[j].diff
                                val t0 = Transaction(
                                    participants[j],
                                    participants[i], keresztmetszet
                                )

                                transactions.add(t0)
                                participants[i].balance = participants[i].balance - keresztmetszet
                                participants[j].balance = participants[j].balance + keresztmetszet
                                participants[i].diff = 0F
                                participants[j].diff = 0F

                            }
                            if (ij > 0) {

                                keresztmetszet = -participants[i].diff
                                val t1 = Transaction(
                                    participants[j],
                                    participants[i], keresztmetszet
                                )
                                participants[j].diff = participants[j].diff - keresztmetszet
                                participants[j].diff+keresztmetszet;     //átállítja a j különbségét
                                participants[i].diff = 0F //beallitja

                                transactions.add(t1)
                                participants[i].balance = participants[i].balance - keresztmetszet
                                participants[j].balance = participants[j].balance + keresztmetszet
                            }
                            if (ij < 0) {
                                keresztmetszet = participants[j].diff
                                val t2 = Transaction(
                                    participants[j],
                                    participants[i], keresztmetszet
                                )
                                //participants[j].diff+keresztmetszet;     //átállítja a j különbségét
                                participants[i].diff = participants[i].diff + keresztmetszet //j.k negatív , keresztmetszet pozitív + kell
                                participants[j].diff = 0F
                                transactions.add(t2)
                                participants[i].balance = participants[i].balance - keresztmetszet
                                participants[j].balance = participants[j].balance + keresztmetszet
                            }
                            //keresztmetszet;     //tfh megvan a keresztmetszet
                        }
                    }
                }
            }
        }
    }
    fun findP(name: String): Participant? {
        for (participant in participants) {
            if (participant.name == name) return participant
        }
        return null
    }
    fun addExpense(participant: Participant ,expense: Expense){
        participant.expenses.add(expense)
        //Log.e("event.addExpense",participant.expenses.toString())
    }

}
