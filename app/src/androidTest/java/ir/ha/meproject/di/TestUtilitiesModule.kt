package ir.ha.meproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UtilitiesModule::class]
)
object TestUtilitiesModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context = appContext

}