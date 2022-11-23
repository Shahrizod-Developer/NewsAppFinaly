package uz.gita.newsappfinaly.presentation.ui.screen.fragment.choose

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.R
import uz.gita.newsappfinaly.data.model.Query
import uz.gita.newsappfinaly.databinding.ScreenChooseTopicsBinding
import uz.gita.newsappfinaly.presentation.adapter.CategoryAdapter
import uz.gita.newsappfinaly.presentation.ui.viewmodel.ChooseTopicScreenViewModel
import uz.gita.newsappfinaly.presentation.ui.viewmodel.impl.ChooseTopicScreenViewModelImpl
import uz.gita.newsappfinaly.utils.toast

@AndroidEntryPoint
class ChooseTopicScreen : Fragment(R.layout.screen_choose_topics) {

    private val binding: ScreenChooseTopicsBinding by viewBinding(ScreenChooseTopicsBinding::bind)
    private val viewModel: ChooseTopicScreenViewModel by viewModels<ChooseTopicScreenViewModelImpl>()
    private val adapter: CategoryAdapter by lazy { CategoryAdapter() }
    private val args: ChooseTopicScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("YYY", args.query.toString())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.categoryList.collectLatest {
                Log.d("MMMM", it.toString())
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

        binding.rv.adapter = adapter

        binding.back.setOnClickListener {
            viewModel.onClickBack()
        }
        binding.nextButton.setOnClickListener {
            viewModel.onClickNextButton(Query(args.query.country, adapter.code, ""))
        }

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