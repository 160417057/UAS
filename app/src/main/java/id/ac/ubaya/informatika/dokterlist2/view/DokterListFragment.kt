package id.ac.ubaya.informatika.dokterlist2.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.dokterlist2.R
import id.ac.ubaya.informatika.dokterlist2.model.Dokter
import id.ac.ubaya.informatika.dokterlist2.viewmodel.ListDokterViewModel
import kotlinx.android.synthetic.main.fragment_create_dokter.*
import kotlinx.android.synthetic.main.fragment_dokter_list.*


class DokterListFragment : Fragment() {
    private lateinit var viewModel:ListDokterViewModel
    private  var dokterListAdapter:DokterListAdapter = DokterListAdapter(arrayListOf(),
        {item -> doClick(item)})

    fun doClick(item:Any){
        viewModel.clearTask(item as Dokter)

        //val action = DokterListFragmentDirections.actionDetailDokterFragment((item as Dokter).uuid)
        //findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dokter_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListDokterViewModel::class.java)
        viewModel.refresh()

        recDokterList.layoutManager = LinearLayoutManager(context)
        recDokterList.adapter = dokterListAdapter

        fabAdd.setOnClickListener {
            val  action = DokterListFragmentDirections.actionCreateDokter()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.dokterLD.observe(viewLifecycleOwner, Observer {
            dokterListAdapter.updateDokterList(it)

            with(txtEmpty){
                visibility = if(it.isEmpty()) View.VISIBLE
                else View.GONE
            }

        })
    }
}