package ir.ha.meproject.domain

import ir.ha.meproject.data.model.User
import ir.ha.meproject.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserUseCase {

    suspend fun getAllUsers() : Flow<List<User>>

}



class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserUseCase {

    override suspend fun getAllUsers() = userRepository.getAllUsers()


}