package id.ac.ubaya.informatika.dokterlist2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dokter(
    @ColumnInfo(name = "nama")
    val nama:String,
    @ColumnInfo(name = "jabatan")
    val jabatan:String,
    @ColumnInfo(name = "praktek")
    val praktek:String,
    @ColumnInfo(name = "phone")
    val phone:String,
    //@ColumnInfo(name = "photoUrl")
    //val photoUrl:String?,
    @ColumnInfo(name = "priority")
    var priority : Int

) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}