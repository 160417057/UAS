package id.ac.ubaya.informatika.dokterlist2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.dokterlist2.model.Dokter
import id.ac.ubaya.informatika.dokterlist2.model.DokterDatabase
import id.ac.ubaya.informatika.dokterlist2.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class DetailDokterViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val dokterLD = MutableLiveData<Dokter>()

    fun  fetch(uuid:Int){
        launch {
            val db = buildDB(getApplication())
            dokterLD.value = db.dokterDao().selectDokter(uuid)
        }
    }

    fun update(nama:String, jabatan:String, praktek:String, phone:String, priority:Int, uuid:Int){
        launch {
            val  db = buildDB(getApplication())
            db.dokterDao().update(nama, jabatan, praktek, phone, priority, uuid)
        }
    }

    fun  addDokter(dokter: Dokter){
        launch {
            val db = buildDB(getApplication())
            db.dokterDao().insertAll(dokter)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}