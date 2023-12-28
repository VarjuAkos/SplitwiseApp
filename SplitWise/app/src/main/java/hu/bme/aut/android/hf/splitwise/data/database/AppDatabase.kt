package hu.bme.aut.android.hf.splitwise.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.android.hf.splitwise.data.daos.EventDao
import hu.bme.aut.android.hf.splitwise.data.daos.ExpenseDao
import hu.bme.aut.android.hf.splitwise.data.daos.ParticipantDao
import hu.bme.aut.android.hf.splitwise.data.entities.EventEntity
import hu.bme.aut.android.hf.splitwise.data.entities.ExpenseEntity
import hu.bme.aut.android.hf.splitwise.data.entities.ParticipantEntity
import hu.bme.aut.android.hf.splitwise.data.typeconverter.Converters
import hu.bme.aut.android.hf.splitwise.domain.model.Event
import hu.bme.aut.android.hf.splitwise.domain.model.Expense
import hu.bme.aut.android.hf.splitwise.domain.model.Participant

@Database(entities = [EventEntity::class, ParticipantEntity::class, ExpenseEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
    abstract fun participantDao(): ParticipantDao
    abstract fun expenseDao(): ExpenseDao

    companion object {
        fun getDatabase(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "database"
            ).fallbackToDestructiveMigration().build()
        }
    }
}