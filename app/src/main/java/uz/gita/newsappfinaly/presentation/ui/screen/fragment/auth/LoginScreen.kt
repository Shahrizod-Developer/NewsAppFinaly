package uz.gita.newsappfinaly.presentation.ui.screen.fragment.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.R
import uz.gita.newsappfinaly.data.local.shp.MySharedPreference
import uz.gita.newsappfinaly.data.model.AuthData
import uz.gita.newsappfinaly.data.repository.auth.impl.SessionManager
import uz.gita.newsappfinaly.databinding.ScreenLoginBinding
import uz.gita.newsappfinaly.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.newsappfinaly.presentation.ui.viewmodel.impl.LoginScreenViewModelImpl
import uz.gita.newsappfinaly.utils.hasConnection
import uz.gita.newsappfinaly.utils.toast
import javax.inject.Inject

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {

    private val binding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)
    private val viewModel: LoginScreenViewModel by viewModels<LoginScreenViewModelImpl>()
    private val live = MutableLiveData<String>()

    @Inject
    lateinit var mGoogleSignInClient: GoogleSignInClient

    @Inject
    lateinit var gso: GoogleSignInOptions

    @Inject
    lateinit var sessionManager: SessionManager

    companion object {
        const val RC_SIGN_IN = 9001
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.message.collectLatest {
                Alerter.create(requireActivity()).setBackgroundColorRes(R.color.red)
                    .setTitle("Login Message").setText(it).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.successMessage.collectLatest {
                Alerter.create(requireActivity()).setBackgroundColorRes(R.color.green)
                    .setTitle("Login Message").setText(it).show()
            }
        }

        binding.google.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loading.collectLatest {
                if (it) {
                    binding.cl.isEnabled = false
                    binding.cl.alpha = 0.7f
                    binding.loading.visibility = View.VISIBLE
                } else {
                    binding.cl.isEnabled = true
                    binding.cl.alpha = 1f
                    binding.loading.visibility = View.GONE
                }
            }
        }

        binding.signUp.setOnClickListener {
            viewModel.onCLickSignUp()
        }

        binding.signIn.setOnClickListener {
            viewModel.onClickSignIn(AuthData(
                binding.emailEt.text.toString().trim(),
                binding.passwordEt.text.toString().trim()),
                requireContext())

        }
    }

    @SuppressLint("ResourceType")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (hasConnection()) {
            if (requestCode == RC_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    sessionManager.setToken(account?.id.toString())
                    viewModel.onClickSignIn(AuthData(account.email.toString(),
                        account.idToken.toString()), requireContext())

                } catch (e: ApiException) {
                    Alerter.create(requireActivity()).setTitle("Login Message")
                        .setBackgroundColorRes(R.color.red)
                        .setText(e.message.toString()).show()
                }
            }
        } else {
            Alerter.create(requireActivity()).setTitle("Login Message")
                .setBackgroundColorRes(R.color.red)
                .setText("No internet connection").show()
        }
    }

}