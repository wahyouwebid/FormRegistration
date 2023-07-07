package com.ujangwahyu.testamarbank.modules.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ujangwahyu.testamarbank.databinding.ItemAdapterBinding
import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem

class FormEnumAdapter(
    private val selectItem: (EnumItem) -> Unit
) : RecyclerView.Adapter<FormEnumAdapter.ViewHolder>() {

    private var data = ArrayList<EnumItem>()

    fun setData(categoryList: List<EnumItem>) {
        data.clear()
        data.addAll(categoryList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.view) {

            root.setOnClickListener {
                selectItem(data[position])
            }
            tvName.text = data[position].name
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    class ViewHolder(val view: ItemAdapterBinding) : RecyclerView.ViewHolder(view.root)

}