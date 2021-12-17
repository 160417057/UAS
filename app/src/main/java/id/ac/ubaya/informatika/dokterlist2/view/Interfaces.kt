package id.ac.ubaya.informatika.dokterlist2.view

import android.view.View
import android.widget.CompoundButton
import id.ac.ubaya.informatika.dokterlist2.model.Dokter

interface DokterCheckedChangeListener{
    fun onDokterCheckedChange(cb:CompoundButton, isChecked:Boolean, obj: Dokter)
}

interface DokterEditClickListener{
    fun onDokterEditClick(v: View)
}

interface RadioClickListener {
    fun onRadioClick(v: View, obj: Dokter)
}

interface DokterSaveChangesListener {
    fun onDokterSaveChanges(v: View, obj: Dokter)
}

interface DokterDetailClickListener{
    fun onDokterDetailClick(v: View)
}

