package uz.gita.newsappfinaly.domain.di

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappfinaly.R


@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    // Method #1
    @Provides
    fun providesGso(app: Application): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(app.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    // Method #2
    @Provides
    fun providesGoogleSignInClients(
        gso: GoogleSignInOptions,
        app: Application
    ): GoogleSignInClient {
        return GoogleSignIn.getClient(app, gso)
    }
}