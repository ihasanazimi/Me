package ir.ha.meproject.data.repository

import ir.ha.meproject.data.model.UserEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
    suspend fun getAllUsers() : Flow<List<UserEntity>>
}



class UserRepositoryImpl : UserRepository {

    override suspend fun getAllUsers(): Flow<List<UserEntity>> = flow {

        delay(5000)

        emit(listOf(
            UserEntity("Omid", "Sadr", "30", "USA", "New York"),
            UserEntity("Pejman", "Pajoohi", "25", "Canada", "Toronto"),
            UserEntity("Alireza", "Ganbari", "40", "Iran", "Tehran"),
            UserEntity("Hasan", "Azimi", "35", "Iran", "Tehran"),
            UserEntity("Sobhan", "Hasanvand", "32", "Japan", "Tokyo"),
            UserEntity("Parsia", "Dolati", "45", "France", "Paris"),
            UserEntity("Zahra", "Eslami", "32", "India", "Mumbai")
        ))
    }
}