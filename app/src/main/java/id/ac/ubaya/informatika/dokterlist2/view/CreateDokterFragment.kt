package id.ac.ubaya.informatika.dokterlist2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ubaya.informatika.dokterlist2.R
import id.ac.ubaya.informatika.dokterlist2.model.Dokter
import id.ac.ubaya.informatika.dokterlist2.viewmodel.DetailDokterViewModel
import kotlinx.android.synthetic.main.fragment_create_dokter.*

class CreateDokterFragment : Fragment() {
    private lateinit var viewModel:DetailDokterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_dokter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailDokterViewModel::class.java)

        btnCreateDokter.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            var dokter = Dokter(txtDokterName.text.toString(),txtJabatanDokter.text.toString()
                ,txtPraktekDokter.text.toString(),txtDokterPhone.text.toString(),radio.tag.toString().toInt())
            viewModel.addDokter(dokter)
            Toast.makeText(it.context,"Dokter Created", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}