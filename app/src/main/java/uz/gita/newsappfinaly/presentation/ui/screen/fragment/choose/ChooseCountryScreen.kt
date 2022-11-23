package uz.gita.newsappfinaly.presentation.ui.screen.fragment.choose

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.R
import uz.gita.newsappfinaly.data.model.Query
import uz.gita.newsappfinaly.databinding.ScreenChooseCountryBinding
import uz.gita.newsappfinaly.presentation.adapter.CountryAdapter
import uz.gita.newsappfinaly.presentation.ui.viewmodel.ChooseCountryScreenViewModel
import uz.gita.newsappfinaly.presentation.ui.viewmodel.impl.ChooseCountryScreenViewModelImpl
import uz.gita.newsappfinaly.utils.toast

@AndroidEntryPoint
class ChooseCountryScreen : Fragment(R.layout.screen_choose_country) {

    private val binding: ScreenChooseCountryBinding by viewBinding(ScreenChooseCountryBinding::bind)
    private val viewModel: ChooseCountryScreenViewModel by viewModels<ChooseCountryScreenViewModelImpl>()
    private val adapter: CountryAdapter by lazy { CountryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countryList.collectLatest {
                if (it.isNotEmpty()) {
                    binding.text.visibility = View.GONE
                    adapter.submitList(it)
                } else {
                    binding.text.visibility = View.VISIBLE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collectLatest {
                if (it) {
                    binding.loading.visibility = View.VISIBLE
                } else {
                    binding.loading.visibility = View.GONE
                }
            }
        }

        binding.nextButton.setOnClickListener {
            viewModel.onClickNextButton(Query(adapter.code, "", ""))
        }
        binding.rv.adapter = adapter

        viewModel.isFirst.onEach {
            Log.d("jjj", it.toString())
            if (it) {
                binding.back.visibility = View.INVISIBLE
            } else {
                binding.back.visibility = View.VISIBLE
            }
        }.launchIn(lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.message.collectLatest {
                toast(it)
            }
        }

        adapter.state.observe(viewLifecycleOwner) {
            Log.d("NNN", it.toString())
            if (it) {
                binding.nextButton.alpha = 1f
                binding.nextButton.isClickable = true
            } else {
                binding.nextButton.alpha = 0.5f
                binding.nextButton.isClickable = false
            }
        }

        binding.back.setOnClickListener {
            viewModel.onClickBack()
        }

        binding.searchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().trim() != "") {
                    viewModel.search(p0.toString().trim())
                }
            }
            override fun afterTextChanged(p0: Editable?) {
                viewModel.search(p0.toString().trim())
            }
        })
    }
}