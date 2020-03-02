package com.juntadeandalucia.ced.newipasen.operations.Login

import android.view.View
import androidx.navigation.fragment.findNavController
import com.juntadeandalucia.ced.newipasen.R
import com.juntadeandalucia.ced.newipasen.base.BaseFragment
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<LoginViewState, LoginViewTransition>() {

    override val viewModel by viewModel<LoginViewModel>()


    override fun getLayout(): Int = R.layout.login_fragment

    override fun initViews() {
        initListeners()
    }

    override fun manageState(state: LoginViewState) {
        when (state) {
            is LoginViewState.Login -> manageStateLogin(state)
        }
    }

    private fun manageStateLogin(state: LoginViewState.Login) {

        progress.visibility = if (state.loading) View.VISIBLE else View.GONE
    }

    override fun manageTransition(transition: LoginViewTransition) {

        when (transition) {
            is LoginViewTransition.OnWelcome -> findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                    transition.message
                )
            )
        }
    }

    override fun initListeners() {
        btnLogin.setOnClickListener {
            if (etUsername.text.toString().isNotEmpty()
                && etPassword.text.toString().isNotEmpty()
            )
                viewModel.checkLogin(etUsername.text.toString(), etPassword.text.toString())
        }
    }
}