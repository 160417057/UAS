package id.ac.ubaya.informatika.dokterlist2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.dokterlist2.R
import id.ac.ubaya.informatika.dokterlist2.databinding.FragmentEditDokterBinding
import id.ac.ubaya.informatika.dokterlist2.model.Dokter
import id.ac.ubaya.informatika.dokterlist2.viewmodel.DetailDokterViewModel
import kotlinx.android.synthetic.main.fragment_create_dokter.*


class EditDokterFragment : Fragment(), RadioClickListener , DokterSaveChangesListener {
    private lateinit var viewModel:DetailDokterViewModel
    private lateinit var dataBinding:FragmentEditDokterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditDokterBinding>(inflater
            ,R.layout.fragment_edit_dokter, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailDokterViewModel::class.java)
        val uuid = EditDokterFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        dataBinding.radiolistener = this
        dataBinding.listener = this

        /*btnCreateDokter.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(txtDokterName.text.toString(), txtJabatanDokter.text.toString(),
                txtPraktekDokter.text.toString(), txtDokterPhone.toString(),
                radio.tag.toString().toInt(), uuid)

            Toast.makeText(view.context, "Dokter Update", Toast.LENGTH_SHORT).show()
        }*/

        observerViewModel()

    }
    fun observerViewModel(){
        viewModel.dokterLD.observe(viewLifecycleOwner, Observer{
            dataBinding.dokter = it
            /*txtDokterName.setText(it.nama)
            txtJabatanDokter.setText(it.jabatan)
            txtPraktekDokter.setText(it.praktek)
            txtDokterPhone.setText(it.phone)

            when (it.priority) {
                3 -> radioHigh.isChecked = true
                2 -> radioMedium.isChecked = true
                else -> radioLow.isChecked = true
            }*/
        })
    }

    override fun onRadioClick(v: View, obj: Dokter) {
        obj.priority = v.tag.toString().toInt()
    }

    override fun onDokterSaveChanges(v: View, obj: Dokter) {

        viewModel.update(obj.nama, obj.jabatan, obj.praktek, obj.phone, obj.priority, obj.uuid)

        Toast.makeText(v.context, "Dokter Update", Toast.LENGTH_SHORT).show()

    }
}