package com.geektech.android2dz21.ui.fragments.signin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.geektech.android2dz21.R
import com.geektech.android2dz21.databinding.FragmentSignInBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private var auth: FirebaseAuth = Firebase.auth

    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() = with(binding) {
        btnCod.setOnClickListener {
            if (etCall.text.isEmpty() ){
                if (etCall.text.isEmpty()) {
                    etCall.error = "asd"
                }
            } else {
                startPhoneNumberVerification(etCall.text.toString())
            }
            btnCod2.isVisible = true
        }
        btnCod2.setOnClickListener {
            if (etCall.text.isEmpty() || etPassword.text.isEmpty()) {
                if (etCall.text.isEmpty()) {
                    etCall.error = "asd"
                    if (etPassword.text.isEmpty()) {
                        etPassword.error = "asd"
                    }
                }
            } else {
                verifyPhoneNumberWithCode(storedVerificationId.toString(),
                    etPassword.text.toString())

            }
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("olol", "njkfds")
                    findNavController().navigate(R.id.noteFragment)
                } else {
                    Log.w("bjkf", "njfk", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(requireContext(), "jkvdf", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
    }


    private fun verifyPhoneNumberWithCode(verificationId: String, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val option = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(68L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(option)
    }


    private var callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.e("jivosh", "hfv")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            storedVerificationId = verificationId
            resendToken = token
        }
    }
}