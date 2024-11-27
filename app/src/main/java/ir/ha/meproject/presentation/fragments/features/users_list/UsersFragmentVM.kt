package ir.ha.meproject.presentation.fragments.features.users_list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ha.meproject.common.base.BaseViewModel
import ir.ha.meproject.data.model.UserEntity
import ir.ha.meproject.data.repository.CoroutineDispatchers
import ir.ha.meproject.domain.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class UsersFragmentVM @Inject constructor(
    private val userUseCase: UserUseCase,
    private val coroutineDispatchers : CoroutineDispatchers
) : BaseViewModel() {

    private val _users = MutableSharedFlow<List<UserEntity>>()
    val users = _users.asSharedFlow()

    fun getAllUsers(){
        viewModelScope.launch(coroutineDispatchers.ioDispatchers()) {
            userUseCase.getAllUsers().collect{ allUsers ->
                _users.emit(allUsers)
            }
        }
    }

}