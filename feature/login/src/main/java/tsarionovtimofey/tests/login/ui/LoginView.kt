package tsarionovtimofey.tests.login.ui

interface LoginView {
    data class State(
        val userName: String,
        val password: String
    )

    data class Model(
        val isValid: Boolean
    )

    sealed class Event {
        object OnLoginClick : Event()
        class OnLoginInputTextChanged(val value: String) : Event()
        class OnPasswordInputTextChanged(val value: String) : Event()
    }
}