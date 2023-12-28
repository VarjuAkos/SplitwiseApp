package hu.bme.aut.android.hf.splitwise.data.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hu.bme.aut.android.hf.splitwise.domain.model.Expense
import hu.bme.aut.android.hf.splitwise.domain.model.Participant
import hu.bme.aut.android.hf.splitwise.domain.model.Transaction

class Converters {
    @TypeConverter
    fun toParticipantList(participantsString: String): List<Participant> {
        val type = object : TypeToken<List<Participant>>() {}.type
        return Gson().fromJson(participantsString, type)
    }
    @TypeConverter
    fun fromParticipantList(participant: List<Participant>): String {
        return Gson().toJson(participant)
    }


    @TypeConverter
    fun fromTransactionList(transactions: List<Transaction>): String {
        return Gson().toJson(transactions)
    }

    @TypeConverter
    fun toTransactionList(transactionsString: String): List<Transaction> {
        val type = object : TypeToken<List<Transaction>>() {}.type
        return Gson().fromJson(transactionsString, type)
    }

    @TypeConverter
    fun fromExpenseList(expense: List<Expense>): String {
        return Gson().toJson(expense)
    }

    @TypeConverter
    fun toExpenseList(expensesString: String): List<Expense> {
        val type = object : TypeToken<List<Expense>>() {}.type
        return Gson().fromJson(expensesString, type)
    }

}
