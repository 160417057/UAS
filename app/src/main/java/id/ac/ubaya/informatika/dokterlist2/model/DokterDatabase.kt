package id.ac.ubaya.informatika.dokterlist2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ubaya.informatika.dokterlist2.util.MIGRATION_1_2

@Database(entities = arrayOf(Dokter::class),version = 1)
abstract class DokterDatabase:RoomDatabase() {
    abstract fun dokterDao():DokterDao

    companion object{
        @Volatile private var instance: DokterDatabase ?= null
        private val LOCK = Any()
        private fun  buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DokterDatabase::class.java,
            "dokterdb"
        )
            .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}