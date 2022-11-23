package uz.gita.newsappfinaly.presentation.ui.screen.fragment.intro

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappfinaly.R
import uz.gita.newsappfinaly.databinding.ScreenSplashBinding
import uz.gita.newsappfinaly.presentation.ui.viewmodel.SplashScreenViewModel
import uz.gita.newsappfinaly.presentation.ui.viewmodel.impl.SplashScreenViewModelImpl

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val viewModel: SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openScreen()
    }
}