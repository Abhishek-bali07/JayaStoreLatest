package com.jaya.app.store.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.jaya.app.store.core.domain.repositories.AddProductRepository
import com.jaya.app.store.core.domain.repositories.DashboardRepository
import com.jaya.app.store.core.domain.repositories.IssueProductRepository
import com.jaya.app.store.core.domain.repositories.MobileRepository
import com.jaya.app.store.core.domain.repositories.SplashRepository
import com.jaya.app.store.core.utils.helper.AppStore
import com.jaya.app.store.data.repositories.AddProductRepositoryImpl
import com.jaya.app.store.data.repositories.DashboardRepositoryImpl
import com.jaya.app.store.data.repositories.IssueProductRepositoryImpl
import com.jaya.app.store.data.repositories.MobileRepositoryImpl
import com.jaya.app.store.data.repositories.SplashRepositoryImpl
import com.jaya.app.store.utils.helper_impl.AppStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AppModule {



    companion object {
        private val Context.dataStore by preferencesDataStore("JayaStore")


        @Provides
        @Singleton
        fun provideGlobalCoroutineScope(): CoroutineScope =
            CoroutineScope(context = Dispatchers.Main + SupervisorJob())

        @Singleton
        @Provides
        fun provideDataStore(@ApplicationContext appContext: Context): DataStore<androidx.datastore.preferences.core.Preferences> =
            appContext.dataStore

    }

    @Binds
    fun bindSplashRepository(splashRepository: SplashRepositoryImpl): SplashRepository


    @Binds
    fun bindAppStore(appStoreImpl: AppStoreImpl): AppStore


    @Binds
    fun bindMobileRepository(MobileRepository : MobileRepositoryImpl) : MobileRepository

    @Binds
    fun bindDashboardRepository(DashboardRepository :DashboardRepositoryImpl) : DashboardRepository

    @Binds
    fun bindAddproductRepository(AddProductRepository :AddProductRepositoryImpl):AddProductRepository

    @Binds
    fun bindIssueProductRepository(IssueproductRepository : IssueProductRepositoryImpl) : IssueProductRepository

}