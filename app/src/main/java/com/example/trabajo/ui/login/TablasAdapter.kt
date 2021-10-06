package com.example.trabajo.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo.data.model.Tabla
import com.example.trabajo.databinding.TablaItemsBinding
import java.util.*

class TablasAdapter(private var allItems: List<Tabla>) :
    RecyclerView.Adapter<TablasAdapter.ViewHolder>(), Filterable {
    var filteredList: MutableList<Tabla>

    init {
        filteredList = allItems.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TablaItemsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.binding(item)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    inner class ViewHolder(private val binding: TablaItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(table: Tabla) = with(binding) {
            txtCodigoSAP.text = table.codigoSAP
            txtTicket.text = table.ticket
        }
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            filteredList.clear()
            if (constraint.isEmpty()) {
                filteredList.addAll(allItems)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in allItems.indices) {
                    if (allItems[item].codigoSAP.lowercase(Locale.getDefault())
                            .contains(filterPattern)
                    ) {
                        filteredList.add(allItems[item])
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            val mFilteredList = filterResults.values

            filteredList = if(mFilteredList == null ){
                mutableListOf()
            }else {
                mFilteredList as MutableList<Tabla>
            }
            notifyDataSetChanged()
        }
    }
}