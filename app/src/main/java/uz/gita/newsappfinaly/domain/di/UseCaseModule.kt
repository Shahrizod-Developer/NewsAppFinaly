package uz.gita.newsappfinaly.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.newsappfinaly.domain.usecase.*
import uz.gita.newsappfinaly.domain.usecase.impl.*


@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindSplashScreenUseCase(impl: SplashScreenUseCaseImpl): SplashScreenUseCase

    @Binds
    fun bindCountryScreenUseCase(impl: CountryScreenUseCaseImpl): CountryScreenUseCase

    @Binds
    fun bindChooseTopicScreenUseCase(impl: ChooseTopicScreenUseCaseImpl): ChooseTopicScreenUseCase

    @Binds
    fun bindIntroScreenUseCase(impl: IntroScreenUseCaseImpl): IntroScreenUseCase

    @Binds
    fun bindLoginScreenUseCase(impl: LoginScreenUseCaseImpl): LoginScreenUseCase

    @Binds
    fun bindRegisterScreenUseCase(impl: RegisterScreenUseCaseImpl): RegisterScreenUseCase
}