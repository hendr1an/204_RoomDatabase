package com.example.myroomsatu.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myroomsatu.AplikasiSiswa
// Pastikan import ini sesuai dengan lokasi file ViewModel Anda nantinya
// import com.example.myroomsatu.viewmodel.HomeViewModel
// import com.example.myroomsatu.viewmodel.EntryViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(repositoriSiswa = aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EntryViewModel(repositoriSiswa = aplikasiSiswa().container.repositoriSiswa)
        }
    }
}

/**
 * Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari
 * [AplikasiSiswa].
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)