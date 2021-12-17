package id.ac.ubaya.informatika.dokterlist2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.dokterlist2.R
import id.ac.ubaya.informatika.dokterlist2.databinding.DokterItemLayoutBinding
import id.ac.ubaya.informatika.dokterlist2.model.Dokter
import kotlinx.android.synthetic.main.dokter_item_layout.view.*

class DokterListAdapter(val  dokterlist:ArrayList<Dokter>, val adapterOnClick:(Any) -> Unit):RecyclerView.Adapter<DokterListAdapter.DokterListViewHolder>(),
    DokterCheckedChangeListener, DokterEditClickListener, DokterDetailClickListener {
    class DokterListViewHolder(var view:DokterItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    fun updateDokterList(newDokterList:List<Dokter>){
        dokterlist.clear()
        dokterlist.addAll(newDokterList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DokterListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.dokter_item_layout,parent,false)

        val view = DataBindingUtil.inflate<DokterItemLayoutBinding>(inflater,
        R.layout.dokter_item_layout, parent, false)
        return DokterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DokterListViewHolder, position: Int) {
        holder.view.dokter = dokterlist[position]
        holder.view.listener = this
        holder.view.editlistener = this
    }

    override fun getItemCount(): Int {
        return dokterlist.size
    }

    override fun onDokterCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Dokter) {
        if(isChecked){
            adapterOnClick(obj)
        }
    }

    override fun onDokterEditClick(v: View) {
        val action = DokterListFragmentDirections.actionEditDokterFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onDokterDetailClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = DokterListFragmentDirections.actionDetailDokterFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}