package tsarionovtimofey.tests.login.ui

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import tsarionovtimofey.tests.domain.model.LoginParams
import tsarionovtimofey.tests.domain.usecase.LoginUserUseCaseRx
import tsarionovtimofey.tests.login.di.LoginNavigation
import tsarionovtimofey.tests.login.domain.IsPasswordValidUseCase
import tsarionovtimofey.tests.login.domain.IsUserNameValidUseCase
import tsarionovtimofey.tests.login.ui.LoginView.Event
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUserUseCaseRx: LoginUserUseCaseRx,
    private val isUserNameValidUseCase: IsUserNameValidUseCase,
    private val isPasswordValidUseCase: IsPasswordValidUseCase,
    private val navigation: LoginNavigation
) : ViewModel() {
    private val state = MutableStateFlow(LoginView.State("", ""))
    val uiState = state.map {
        LoginView.Model(
            isValid = isStateValid(it)
        )
    }

    fun onEvent(event: Event) {
        when (event) {
            Event.OnLoginClick -> processLoginClick()
            is Event.OnLoginInputTextChanged -> state.update { it.copy(userName = event.value) }
            is Event.OnPasswordInputTextChanged -> state.update { it.copy(password = event.value) }
        }
    }

    private fun processLoginClick() {
        val state = state.value
        if (!isStateValid(state)) return
        val params = LoginParams(
            userName = state.userName,
            password = state.password
        )
        loginUserUseCaseRx.execute(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { navigation.next() },
                {}
            )
            .let { compositeDisposable.add(it) }
    }

    private fun isStateValid(state: LoginView.State): Boolean = state.run {
        isUserNameValidUseCase.execute(userName) && isPasswordValidUseCase.execute(password)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    private val compositeDisposable = CompositeDisposable()
}