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
import kotlin.coroutines.CoroutineContext

class ListDokterViewModel(application: Application):AndroidViewModel(application), CoroutineScope{
    val dokterLD = MutableLiveData<List<Dokter>>()
    private var job = Job()

    fun refresh(){
        launch {
            val db = buildDB(getApplication())
            dokterLD.value = db.dokterDao().selectAllDokter()
        }
    }

    fun clearTask(dokter: Dokter){
        launch {
            val db = buildDB(getApplication())
            db.dokterDao().deleteDokter(dokter)
            dokterLD.value = db.dokterDao().selectAllDokter()
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job +Dispatchers.Main
}