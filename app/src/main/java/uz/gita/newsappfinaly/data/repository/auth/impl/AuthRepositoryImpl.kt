package uz.gita.newsappfinaly.data.repository.auth.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappfinaly.data.local.shp.MySharedPreference
import uz.gita.newsappfinaly.data.model.AuthData
import uz.gita.newsappfinaly.data.repository.auth.AuthRepository
import uz.gita.newsappfinaly.utils.MessageData
import uz.gita.newsappfinaly.utils.ResultData
import uz.gita.newsappfinaly.utils.hasConnection
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var firebaseUser: FirebaseUser
    private val shp = MySharedPreference.getInstance()
    private var message = ""
    private var a = false

    override fun register(authData: AuthData): Flow<ResultData<String>> =
        callbackFlow<ResultData<String>> {
            if (hasConnection()) {
                if (notEmpty(authData)) {
                    firebaseAuth.createUserWithEmailAndPassword(authData.email, authData.password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                shp.uid = task.result.user?.uid.toString()
                                a = true
                                shp.email = authData.email
                                shp.password = authData.password
                                firebaseUser = firebaseAuth.currentUser!!
                                trySend(ResultData.success("Sign up successfully"))
                            } else {
                                trySend(ResultData.message(MessageData.messageText(
                                    task.exception?.message.toString())))
                            }
                        }
                } else {
                    trySend(ResultData.message(MessageData.messageText("Please fill in all fields !!!")))
                }
                awaitClose()
            } else {
                trySend(ResultData.message(MessageData.messageText(
                    "No internet connection")))
            }
        }.flowOn(Dispatchers.IO)

    override fun login(authData: AuthData): Flow<ResultData<String>> =
        callbackFlow<ResultData<String>> {
            if (hasConnection()) {
                if (notEmpty(authData)) {
                    firebaseAuth.signInWithEmailAndPassword(authData.email, authData.password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                if (a) {
                                    shp.isFirst = false
                                }
                                trySend(ResultData.success("Sign in successfully"))
                            } else {
                                trySend(ResultData.message(MessageData.messageText("Sign in failed")))
                            }
                        }
                } else {
                    val a = ArrayList<String>()
                    a.add(authData.email)
                    a.add(authData.password)
                    a.forEach { input ->
                        if (input.isEmpty()) {
                            trySend(ResultData.message(MessageData.messageText("Please fill in all fields !!!")))
                        }
                    }
                }
                awaitClose()
            } else {
                trySend(ResultData.message(MessageData.messageText(
                    "No internet connection")))
            }
        }.flowOn(Dispatchers.IO)

    override fun resetPasswordWithEmail(email: String): Flow<ResultData<String>> =
        callbackFlow<ResultData<String>> {

            if (hasConnection()) {
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        message = it.result.toString()
                        trySend(ResultData.success("Send code your email"))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)

    override fun logout(authData: AuthData): Flow<ResultData<String>> =
        callbackFlow {
            if (hasConnection()) {
                try {
                    firebaseAuth.signOut()
                    shp.uid = ""
                    shp.email = ""
                    shp.password = ""
                    shp.fullName = ""
                    shp.image = ""
                    shp.isFirst = true
                    trySend(ResultData.success("Account deleted"))
                } catch (e: Exception) {
                    trySend(ResultData.message(MessageData.messageText(e.message.toString())))
                }
            }
        }.flowOn(Dispatchers.IO)

    override fun getSms(): Flow<ResultData<String>> = flow {
        emit(ResultData.success(message))
    }

    private fun notEmpty(authData: AuthData): Boolean =
        authData.email.isNotEmpty() && authData.password.isNotEmpty()

}