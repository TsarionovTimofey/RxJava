package tsarionovtimofey.tests.login.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tsarionovtimofey.tests.common.ViewModelFactory
import tsarionovtimofey.tests.login.R
import tsarionovtimofey.tests.login.di.DaggerLoginComponent
import tsarionovtimofey.tests.login.di.LoginComponentDependenciesProvider
import tsarionovtimofey.tests.login.ui.LoginView.Event
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<LoginViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deps = (context?.applicationContext as LoginComponentDependenciesProvider)
            .getDependencies()
        val component = DaggerLoginComponent.builder()
            .loginComponentDependencies(deps)
            .build()
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<EditText>(R.id.et_login).doAfterTextChanged {
            viewModel.onEvent(Event.OnLoginInputTextChanged(it.toString()))
        }
        view.findViewById<EditText>(R.id.et_password).doAfterTextChanged {
            viewModel.onEvent(Event.OnPasswordInputTextChanged(it.toString()))
        }
        val button = view.findViewById<Button>(R.id.btn_action)
        button.setOnClickListener {
            viewModel.onEvent(Event.OnLoginClick)
        }
        val animation = TranslateAnimation(0f, 200f, 0f, 0f)
        animation.duration = 2000
        view.findViewById<LinearLayout>(R.id.container).startAnimation(animation)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                button.isEnabled = it.isValid
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}