package com.example.myroomsatu.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myroomsatu.R
import com.example.myroomsatu.room.Siswa
import com.example.myroomsatu.view.route.DestinasiHome
import com.example.myroomsatu.viewmodel.HomeViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    // Pastikan HomeViewModel dan PenyediaViewModel sudah dibuat di file lain
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = stringResource(id = DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp) // Saya ganti dimensionResource agar aman
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.tambah_siswa) // Pastikan string ini ada
                )
            }
        },
    ) { innerPadding ->

        // Mengambil state dari ViewModel
        val uiStateSiswa by viewModel.homeUiState.collectAsState()

        BodyHome(
            itemSiswa = uiStateSiswa.listSiswa,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

// --- 3. BODY HALAMAN (LOGIKA LIST KOSONG VS ISI) ---
@Composable
fun BodyHome(
    itemSiswa: List<Siswa>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemSiswa.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item), // Pastikan string "Tidak ada data..." ada di xml
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListSiswa(
                itemSiswa = itemSiswa,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

// --- 4. LIST (LAZY COLUMN) ---
@Composable
fun ListSiswa(
    itemSiswa: List<Siswa>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = itemSiswa, key = { it.id }) { person ->
            DataSiswa(
                siswa = person,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// --- 5. ITEM KARTU SISWA (DATASISWA) ---
@Composable
fun DataSiswa(
    siswa: Siswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp), // Saya sederhanakan paddingnya
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = siswa.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = siswa.telpon,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = siswa.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

// --- 6. KOMPONEN TAMBAHAN (SiswaTopAppBar) ---
// Saya buatkan ini karena dipanggil di HomeScreen tapi tidak ada di gambar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
            }
        }
    )
}