package ir.ha.meproject.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.ha.meproject.common.file.AssetHelper
import ir.ha.meproject.data.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import java.lang.reflect.Type;


interface UserRepository {
    suspend fun getAllUsers() : Flow<List<UserEntity>>
}



class UserRepositoryImpl @Inject constructor(
    private val context: Context
) : UserRepository {

    override suspend fun getAllUsers(): Flow<List<UserEntity>> = flow {
        val json = AssetHelper.readJsonFromAssets(context,"Users.json")
        val userListType: Type = object : TypeToken<List<UserEntity?>?>() {}.type
        val users = Gson().fromJson<List<UserEntity>>(json,userListType)
        emit(users)
    }
}