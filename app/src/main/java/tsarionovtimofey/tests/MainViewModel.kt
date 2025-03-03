package tsarionovtimofey.tests

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tsarionovtimofey.tests.navigation.Navigation
import tsarionovtimofey.tests.domain.usecase.IsUserSignedInUseCase
import tsarionovtimofey.tests.login.ui.LoginFragment
import javax.inject.Inject

class MainViewModel @Inject constructor(
    isUserSignedInUseCase: IsUserSignedInUseCase,
    private val navigation: Navigation
) : ViewModel() {
    private val _state = MutableLiveData(isUserSignedInUseCase.execute())
    val state: LiveData<Boolean>
        get() = _state

    init {
        val nextFragment = if (isUserSignedInUseCase.execute()) {
            Fragment() // Todo stub
        } else {
            LoginFragment.newInstance()
        }
        navigation.navigateTo(nextFragment)
    }
}