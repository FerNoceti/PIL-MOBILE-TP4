package com.pil.tp_04.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pil.tp_04.mvvm.contract.CountContract

class CountViewModel(private val model: CountContract.Model) : ViewModel(), CountContract.ViewModel {

    private val mutableLiveData: MutableLiveData<CounterData> = MutableLiveData()
    override fun getValue(): LiveData<CounterData> {
        return mutableLiveData
    }

    val data: LiveData<CounterData> = mutableLiveData

    fun initUI() {
        mutableLiveData.postValue(CounterData(CounterState.INITIAL))
    }

    override fun resetValue() {
        model.reset()
        mutableLiveData.value = CounterData(CounterState.INITIAL)
    }

    override fun incrementValue(input: String) {
        model.increment(input.toInt() ?: 0)
        mutableLiveData.value = CounterData(CounterState.INCREMENT, model.counter)
    }

    override fun decrementValue(input: String) {
        model.decrement(input.toInt() ?: 0)
        mutableLiveData.value = CounterData(CounterState.DECREMENT, model.counter)
    }

    data class CounterData(
        val state: CounterState = CounterState.INITIAL,
        val value: Int = 0,
    )

    enum class CounterState {
        INITIAL,
        INCREMENT,
        DECREMENT,
    }
}
