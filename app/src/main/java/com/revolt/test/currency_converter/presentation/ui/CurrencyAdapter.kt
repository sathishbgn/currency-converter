package com.revolt.test.currency_converter.presentation.ui

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.revolt.test.currency_converter.R
import com.revolt.test.currency_converter.presentation.displaymodel.CurrencyDisplay
import kotlinx.android.synthetic.main.currency_item.view.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class CurrencyAdapter(private val currencyDisplay: MutableList<CurrencyDisplay>,
                      private val activity: Activity) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private var listener: UpdateCurrencyList? = null
    private lateinit var viewHolder: CurrencyViewHolder

    fun setListener(currencyListener: UpdateCurrencyList) {
        listener = currencyListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        viewHolder = CurrencyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return currencyDisplay.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.onBind(display = currencyDisplay[position], position = position)
    }

    inner class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var watcher: CurrencyTextChangedListener? = null

        fun onBind(display: CurrencyDisplay, position: Int) {
            watcher = CurrencyTextChangedListener(currencyDisplay, display, listener)
            itemView.tvCurrency.text = display.countryCode
            itemView.edCurrency.setText(formatCurrency(display.rate))
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

            itemView.edCurrency.setOnClickListener{
                itemView.edCurrency.addTextChangedListener(watcher)
            }
        }

        fun removeWatcher() {
            itemView.edCurrency.removeTextChangedListener(watcher)
        }

        private fun formatCurrency(rate: Double): String {
            val pattern = "###.####"
            val locale = Locale.getDefault()
            val decimalFormat = NumberFormat.getNumberInstance(locale) as DecimalFormat
            decimalFormat.applyLocalizedPattern(pattern)
            return decimalFormat.format(rate)
        }
    }

    fun removeWatcher() {
        viewHolder.removeWatcher()
    }

    interface UpdateCurrencyList {
        fun onValueChange()
    }
}

class CurrencyTextChangedListener(private val currencyDisplay: MutableList<CurrencyDisplay>,
                                  private val display: CurrencyDisplay,
                                  private val listener: CurrencyAdapter.UpdateCurrencyList?) : TextWatcher {

    private var timer: Timer? = null

    companion object {
        private const val delay = 600L
    }

    override fun afterTextChanged(s: Editable?) {
        timer = Timer()
        timer?.schedule(
            object : TimerTask() {
                override fun run() {
                    var editAmount = s.toString()
                    if (editAmount.isEmpty()){
                        editAmount = "0"
                    }

                    //convert euro based on the editText
                    for (currency in currencyDisplay) {
                        if (currency.equals(display)) {
                            currency.rate = editAmount.toDouble()
                        } else {
                            currency.rate = editAmount.toDouble() * currency.eurAgainstLocalCurrency

                        }
                    }
                    listener?.onValueChange()
                }

            },
            delay
        )
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        timer?.cancel()
    }

}