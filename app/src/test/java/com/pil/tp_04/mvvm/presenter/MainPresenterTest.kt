package com.pil.tp_04.mvvm.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pil.tp_04.mvvm.contract.CountContract
import com.pil.tp_04.mvvm.model.CountModel
import com.pil.tp_04.mvvm.viewmodel.CountViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainPresenterTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CountContract.ViewModel

    @Before
    fun setUp() {
        viewModel = CountViewModel(CountModel())
    }

    @Test
    fun `initial state`() {
        assert(viewModel.getValue().value == null)
    }

    @Test
    fun `on press reset after initial state`() {
        viewModel.resetValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.INITIAL)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun `on press reset after increment state`() {
        viewModel.incrementValue(FIVE)

        viewModel.resetValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.INITIAL)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun `on press reset after decrement state`() {
        viewModel.decrementValue(FIVE)

        viewModel.resetValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.INITIAL)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun `on press increment after initial state`() {
        viewModel.incrementValue(FIVE)

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.INCREMENT)
        assert(viewModel.getValue().value?.value == FIVE)
    }

    @Test
    fun `on press increment after increment state`() {
        viewModel.incrementValue(FIVE)
        viewModel.incrementValue(FIVE)

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.INCREMENT)
        assert(viewModel.getValue().value?.value == TEN)
    }

    @Test
    fun `on press increment after decrement state`() {
        viewModel.decrementValue(FIVE)
        viewModel.incrementValue(FIVE)

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.INCREMENT)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun `on press decrement after initial state`() {
        viewModel.decrementValue(FIVE)

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.DECREMENT)
        assert(viewModel.getValue().value?.value == MINUS_FIVE)
    }

    @Test
    fun `on press decrement after increment state`() {
        viewModel.incrementValue(FIVE)
        viewModel.decrementValue(FIVE)

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.DECREMENT)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun `on press decrement after decrement state`() {
        viewModel.decrementValue(FIVE)
        viewModel.decrementValue(FIVE)

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CountViewModel.CounterState.DECREMENT)
        assert(viewModel.getValue().value?.value == MINUS_TEN)
    }

    companion object {
        private const val ZERO = 0
        private const val FIVE = 5
        private const val TEN = 10
        private const val MINUS_FIVE = -5
        private const val MINUS_TEN = -10
    }
}
