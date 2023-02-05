package com.example.mychucknoris.di

import com.example.mychucknoris.MainViewModel
import com.example.mychucknoris.Repository
import com.example.mychucknoris.chuckapi.ChuckApi
import com.example.mychucknoris.datasources.DataLocalSource
import com.example.mychucknoris.datasources.DataRemoteSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    val di = module {
        single {
            ChuckApi.getClient()
        }
        single {
            DataLocalSource()
        }
        single {
            DataRemoteSource(get())
        }
        single {
            Repository(get(), get())
        }
        viewModel {
            MainViewModel(get())
        }
    }
