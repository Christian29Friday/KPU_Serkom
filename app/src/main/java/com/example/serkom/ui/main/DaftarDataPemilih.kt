package com.example.serkom.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serkom.databinding.ActivityDaftarDataPemilihBinding
import com.google.android.material.snackbar.Snackbar
import com.example.serkom.helper.ViewModelFactory

// Kelas Activity untuk menampilkan daftar data pemilih.
class DaftarDataPemilih : AppCompatActivity() {

    private var _daftarDataPemilihBinding: ActivityDaftarDataPemilihBinding? = null
    private val binding get() = _daftarDataPemilihBinding

    private lateinit var adapter: DataPemilihAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _daftarDataPemilihBinding = ActivityDaftarDataPemilihBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Set up action bar dengan judul dan tombol kembali.
        supportActionBar?.title = "Daftar Data Pemilih"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mendapatkan ViewModel dari factory.
        val daftarDataPemilihViewModel = obtainViewModel(this@DaftarDataPemilih)

        // Mengobservasi LiveData dari ViewModel untuk mendapatkan data pemilih.
        daftarDataPemilihViewModel.getAllDataPemilih().observe(this) { datapemilihList ->
            if (datapemilihList != null && datapemilihList.isNotEmpty()) {
                adapter.setListDataPemilih(datapemilihList)
            } else {
                adapter.setListDataPemilih(emptyList()) // Menetapkan daftar kosong untuk menghapus data sebelumnya.
                showNoDataSnackbar()
            }
        }

        adapter = DataPemilihAdapter()

        // Mengatur RecyclerView dengan LinearLayoutManager dan Adapter.
        binding?.rvDatapemilih?.layoutManager = LinearLayoutManager(this)
        binding?.rvDatapemilih?.setHasFixedSize(true)
        binding?.rvDatapemilih?.adapter = adapter

    }

    // Menampilkan Snackbar jika tidak ada data.
    private fun showNoDataSnackbar() {
        val snackbar = Snackbar.make(
            binding?.root!!, // Root view dari layout
            "Tidak ada data saat ini",
            Snackbar.LENGTH_LONG
        )
        snackbar.show()
    }

    // Membuat ViewModel dengan factory yang disediakan.
    private fun obtainViewModel(activity: AppCompatActivity): DaftarDataPemilihViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DaftarDataPemilihViewModel::class.java)
    }

    // Membersihkan binding ketika Activity dihancurkan.
    override fun onDestroy() {
        super.onDestroy()
        _daftarDataPemilihBinding = null
    }

    // Menangani tombol kembali di action bar.
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
