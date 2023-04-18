package com.pil.tp_04.mvvm.contract

import androidx.lifecycle.LiveData
import com.pil.tp_04.mvvm.viewmodel.CountViewModel

interface CountContract {
    interface Model {
        val counter: Int
        fun increment(inputValue: Int)
        fun decrement(inputValue: Int)
        fun reset()
    }

    interface ViewModel {
        fun getValue(): LiveData<CountViewModel.CounterData>
        fun resetValue()
        fun incrementValue(inputValue: String)
        fun decrementValue(inputValue: String)
    }
}
