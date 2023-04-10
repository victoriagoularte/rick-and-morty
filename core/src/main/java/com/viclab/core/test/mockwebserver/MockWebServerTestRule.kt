package com.viclab.core.test.mockwebserver

import com.viclab.core.BuildConfig
import com.viclab.core.network.di.NetworkModule
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MockWebServerTestRule : TestWatcher() {

    val server = MockWebServer()
    private val baseUrl = server.url("/").toString()

    override fun starting(description: Description) {
        super.starting(description)
    }

    override fun finished(description: Description) {
        server.shutdown()
    }

    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
}