package id.ac.ubaya.informatika.dokterlist2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.dokterlist2.R
import id.ac.ubaya.informatika.dokterlist2.databinding.FragmentDokterListItemBinding
import id.ac.ubaya.informatika.dokterlist2.viewmodel.DetailDokterViewModel


class DokterListItem : Fragment() {
    private lateinit var viewModel:DetailDokterViewModel
    private lateinit var dataBinding: FragmentDokterListItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dokter_list_item, container, false)

        dataBinding = DataBindingUtil.inflate<FragmentDokterListItemBinding>(inflater
            ,R.layout.fragment_dokter_list_item, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailDokterViewModel::class.java)
        val uuid = DokterListItemArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.dokterLD.observe(viewLifecycleOwner, Observer{
            dataBinding.dokter = it

        })
    }
}