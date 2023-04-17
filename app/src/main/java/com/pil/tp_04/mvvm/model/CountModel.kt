package com.pil.tp_04.mvvm.model

import com.pil.tp_04.mvvm.contract.CountContract

class CountModel : CountContract.Model {

    override var counter: Int = ZERO_INT
        private set

    override fun increment(inputValue: Int) {
        counter += inputValue
    }

    override fun decrement(inputValue: Int) {
        counter -= inputValue
    }

    override fun reset() {
        counter = ZERO_INT
    }

    companion object {
        private const val ZERO_INT = 0
    }
}
