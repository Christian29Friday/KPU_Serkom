package com.example.serkom.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.serkom.ui.insert.FormEntryViewModel
import com.example.serkom.ui.main.DaftarDataPemilihViewModel

// Kelas ViewModelFactory yang merupakan ekstensi dari ViewModelProvider.NewInstanceFactory
class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {

    // Companion object untuk implementasi singleton pattern.
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        // Fungsi untuk mendapatkan instance singleton dari ViewModelFactory
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    // Override fungsi create untuk mengembalikan instance dari ViewModel tertentu.
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Mengecek dan mengembalikan instance ViewModel sesuai dengan kelasnya.
        when {
            modelClass.isAssignableFrom(DaftarDataPemilihViewModel::class.java) -> {
                return DaftarDataPemilihViewModel(mApplication) as T
            }
            modelClass.isAssignableFrom(FormEntryViewModel::class.java) -> {
                return FormEntryViewModel(mApplication) as T
            }
            else -> {
                // Melempar exception jika kelas ViewModel tidak dikenal
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}
