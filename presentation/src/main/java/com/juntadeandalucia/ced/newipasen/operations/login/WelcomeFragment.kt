package com.juntadeandalucia.ced.newipasen.operations.login

import androidx.navigation.fragment.navArgs
import com.juntadeandalucia.ced.newipasen.R
import com.juntadeandalucia.ced.newipasen.base.BaseFragment
import kotlinx.android.synthetic.main.welcome_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeFragment : BaseFragment<WelcomeViewState, WelcomeViewTransition>() {
    override val viewModel by viewModel<WelcomeViewModel>()
    private val args by navArgs<WelcomeFragmentArgs>()
    override fun getLayout(): Int {
        return R.layout.welcome_fragment
    }

    override fun initViews() {
        initArgs(args.message)
    }

    private fun initArgs(message: String) {
        viewModel.init(message)
    }

    override fun manageState(state: WelcomeViewState) {
        when (state) {
            is WelcomeViewState.Welcome -> tvWelcome.text = state.welcomeMessage
        }
    }

    override fun manageTransition(transition: WelcomeViewTransition) {
    }

    override fun initListeners() {
    }
}