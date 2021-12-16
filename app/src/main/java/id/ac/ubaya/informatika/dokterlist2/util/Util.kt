package id.ac.ubaya.informatika.dokterlist2.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ubaya.informatika.dokterlist2.model.DokterDatabase

val DB_NAME = "dokterdb"

fun buildDB(context: Context):DokterDatabase{
    val db = Room.databaseBuilder(context,
            DokterDatabase::class.java, "dokterdb")
        .addMigrations(MIGRATION_1_2)
        .build()
    return  db
}

val MIGRATION_1_2 = object :Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE dokter ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }

}