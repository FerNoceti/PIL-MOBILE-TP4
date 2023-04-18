package com.pil.tp_04.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.pil.tp_04.databinding.ActivityMainBinding
import com.pil.tp_04.mvvm.model.CountModel
import com.pil.tp_04.mvvm.viewmodel.CountViewModel
import com.pil.tp_04.mvvm.viewmodel.CountViewModel.CounterData
import com.pil.tp_04.mvvm.viewmodel.CountViewModel.CounterState

class CountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val countViewModel: CountViewModel = CountViewModel(CountModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.increment.setOnClickListener { countViewModel.incrementValue(binding.inputCount.text.toString()) }
        binding.decrement.setOnClickListener { countViewModel.decrementValue(binding.inputCount.text.toString()) }
        binding.reset.setOnClickListener { countViewModel.resetValue() }
        countViewModel.initUI()
        countViewModel.data.observe({ lifecycle }, ::updateUI)
    }
    private fun updateUI(it: CounterData) {
        when (it.state) {
            CounterState.INITIAL -> {
                binding.counter.text = "0"
            }
            CounterState.INCREMENT -> {
                binding.counter.text = it.value.toString()
            }
            CounterState.DECREMENT -> {
                binding.counter.text = it.value.toString()
            }
        }
    }
}
