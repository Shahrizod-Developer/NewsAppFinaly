package uz.gita.newsappfinaly.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappfinaly.presentation.navigation.Handler
import uz.gita.newsappfinaly.presentation.navigation.NavigationDispatcher
import uz.gita.newsappfinaly.presentation.navigation.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindsNavigator(impl: NavigationDispatcher): Navigator

    @Binds
    fun bindsHandler(impl: NavigationDispatcher): Handler

}