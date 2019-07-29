package com.revolt.test.currency_converter.presentation.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.revolt.test.currency_converter.R
import com.revolt.test.currency_converter.presentation.displaymodel.CurrencyDisplay
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrencyAdapter(private val currencyDisplay: MutableList<CurrencyDisplay>,
                      private val activity: Activity) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currencyDisplay.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.onBind(display = currencyDisplay[position], position = position)
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(display: CurrencyDisplay, position: Int) {
            itemView.tvCurrency.text = display.countryCode
            itemView.edCurrency.setText(display.rate.toString())
            itemView.currencyItem.setOnClickListener{
                currencyDisplay.removeAt(position).also {
                    currencyDisplay.add(0, display)
                }
                notifyItemMoved(position, 0)
                notifyDataSetChanged()
                itemView.edCurrency.requestFocus().also {
                    activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
                }
            }
        }

    }
}
