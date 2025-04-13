package com.example.saudeocaraapp.di

import androidx.room.Room
import com.example.saudeocaraapp.database.AppDatabase
import com.example.saudeocaraapp.network.RetrofitInstance
import com.example.saudeocaraapp.viewmodel.PacienteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module



val appModule = module {

    // Retrofit
    single { RetrofitInstance.getApiService() }

    // Room database
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "saude_ocara.db"
        ).build()
    }

    single { get<AppDatabase>().PacienteDAO() }


    viewModel {
        PacienteViewModel(
            pacienteDAO = get(),
            apiService = get()
        )
    }
}