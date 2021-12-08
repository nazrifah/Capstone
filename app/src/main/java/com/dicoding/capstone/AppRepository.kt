package com.dicoding.capstone

import com.dicoding.capstone.adapter.DataObat

class AppRepository private constructor(private val remoteData: RemoteData) : DataSource {
    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(remoteData: RemoteData): AppRepository = instance ?: synchronized(this) {
            AppRepository(remoteData).apply {
                instance = this
            }
        }
    }
    override fun getObat(): ArrayList<DataObat> {
        val obatResponses = remoteData.getAllObat()
        val obatList = ArrayList<DataObat>()
        for (response in obatResponses) {
            val obat = DataObat(
                response.id,
                response.nama,
                response.harga,
                response.deskripsi,
                response.indikasi,
                response.komposisi,
                response.dosis,
                response.aturan,
                response.efek,
                response.foto
            )
            obatList.add(obat)
        }
        return obatList
    }
    override fun getDetailObat(id: Int): DataObat {
        val detailResponses = remoteData.getDetailObat()
        lateinit var detailO: DataObat
        for (response in detailResponses) {
            if (response.id == id) {
                detailO = DataObat(
                response.id,
                response.nama,
                response.harga,
                response.deskripsi,
                response.indikasi,
                response.komposisi,
                response.dosis,
                response.aturan,
                response.efek,
                response.foto)
            }
        }
        return detailO
    }
}