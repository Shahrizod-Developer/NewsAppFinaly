package uz.gita.newsappfinaly.presentation.ui.screen.fragment.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.R
import uz.gita.newsappfinaly.databinding.ScreenIntroBinding
import uz.gita.newsappfinaly.presentation.adapter.IntroAdapter
import uz.gita.newsappfinaly.presentation.ui.viewmodel.IntroScreenViewModel
import uz.gita.newsappfinaly.presentation.ui.viewmodel.impl.IntroScreenViewModelImpl

@AndroidEntryPoint
class IntroScreen : Fragment(R.layout.screen_intro) {

    private val binding: ScreenIntroBinding by viewBinding(ScreenIntroBinding::bind)
    private val viewModel: IntroScreenViewModel by viewModels<IntroScreenViewModelImpl>()
    private val adapter: IntroAdapter by lazy { IntroAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.introDataList.collectLatest {
                adapter.submitList(it)
            }
        }
        binding.viewPager.adapter = adapter

        binding.skip.setOnClickListener {
            viewModel.onClickSkip()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentItemFlow.collectLatest {
                binding.viewPager.currentItem = it
            }
        }
        binding.indicator.setViewPager2(binding.viewPager)

        binding.next.setOnClickListener {
            viewModel.onClickNext(binding.viewPager.currentItem)
        }

    }
}