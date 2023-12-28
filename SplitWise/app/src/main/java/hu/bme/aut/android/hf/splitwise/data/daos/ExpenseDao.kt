package hu.bme.aut.android.hf.splitwise.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import hu.bme.aut.android.hf.splitwise.data.entities.ExpenseEntity

@Dao
interface ExpenseDao {
    @Insert
    fun insertExpense(expense: ExpenseEntity)

    @Update
    fun updateExpense(expense: ExpenseEntity)

    @Delete
    fun deleteExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expenses ")
    fun getExpensesForParticipant(): List<ExpenseEntity>
}