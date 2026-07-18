package com.unibo.android.corsolp2526

import android.app.Application
import com.unibo.android.corsolp2526.data.repository.RepositoryProviderImpl
import com.unibo.android.domain.di.UseCasesProvider
import com.unibo.android.nuovo_prog_mobile.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CustomApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            UseCasesProvider.initialize(
                repository = RepositoryProviderImpl.getMovieRepository(
                    context = this@CustomApplication,
                    apiKey = BuildConfig.TMDB_API_KEY
                )
            )
        }
    }
}