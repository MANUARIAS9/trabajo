package com.example.trabajo.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo.data.model.Tabla
import com.example.trabajo.databinding.TablaItemsBinding
import java.util.*

class TablasAdapter(private val allItems: List<Tabla>) :
    RecyclerView.Adapter<TablasAdapter.ViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TablaItemsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = allItems[0]
        holder.binding(item)
    }

    override fun getItemCount(): Int {
        return allItems.size
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
        var filteredList: MutableList<Tabla> = arrayListOf()
        override fun performFiltering(constraint: CharSequence): FilterResults {
            if (constraint.isEmpty()) {
                filteredList.addAll(allItems)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in 0..allItems.size) {
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
            filteredList = filterResults.values as MutableList<Tabla>
            notifyDataSetChanged()
        }
    }
}