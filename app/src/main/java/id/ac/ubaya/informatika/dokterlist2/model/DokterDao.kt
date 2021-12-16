package id.ac.ubaya.informatika.dokterlist2.model

import androidx.room.*

@Dao
interface DokterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertAll(vararg dokter: Dokter)

    @Query("SELECT * FROM dokter ORDER BY priority DESC")
    suspend fun selectAllDokter():List<Dokter>

    @Query("SELECT *FROM dokter WHERE uuid= :id")
    suspend fun selectDokter(id:Int):Dokter

    @Query("UPDATE dokter SET nama= :nama, jabatan=:jabatan, praktek=:praktek, phone=:phone, priority=:priority WHERE uuid=:uuid")
    suspend fun update(nama:String,jabatan:String,praktek:String,phone:String,priority:Int,uuid:Int)

    @Delete
    suspend fun  deleteDokter(dokter: Dokter)
}