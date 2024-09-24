package ir.ha.meproject.presentation.features.fragments.temp1

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ha.meproject.common.base.BaseViewModel
import ir.ha.meproject.data.model.User
import ir.ha.meproject.domain.UserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileFragmentVM @Inject constructor(
    private val userUseCase: UserUseCase
) :  BaseViewModel() {

    val usersFlow = MutableSharedFlow<List<User>>(replay = 1)


    fun getAllUsers() {
        Log.i(TAG, "getAllUsers: ")
        viewModelScope.launch {
            val result = userUseCase.getAllUsers()
            result.collect{ returnedUsers ->
                usersFlow.emit(returnedUsers)
            }
        }
    }


}