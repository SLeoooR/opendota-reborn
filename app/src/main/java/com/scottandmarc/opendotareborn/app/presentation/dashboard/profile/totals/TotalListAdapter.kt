package com.scottandmarc.opendotareborn.app.presentation.dashboard.profile.totals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.scottandmarc.opendotareborn.R
import com.scottandmarc.opendotareborn.app.domain.entities.Total
import com.scottandmarc.opendotareborn.databinding.TotalListItemBinding

class TotalListAdapter(
    private val totals: List<Total>,
) : RecyclerView.Adapter<TotalListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: TotalListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val totalListItemBinding = TotalListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(totalListItemBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (this.totals.isNotEmpty()) {
            val total: Total = totals[position]

            viewHolder.binding.tvTotalField.text = total.field.replace('_', ' ').uppercase()
            viewHolder.binding.tvTotalSum.text = "%,d".format(total.sum.toInt())
        }
    }

    override fun getItemCount(): Int = totals.size
}