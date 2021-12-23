package com.dicoding.capstone

import androidx.lifecycle.LiveData
import com.dicoding.capstone.adapter.DataObat
import com.dicoding.capstone.source.ObatDataSource
import com.dicoding.capstone.source.entity.ObatEntity
import com.dicoding.capstone.utils.ApiResponse
import com.dicoding.capstone.utils.AppExecutors
import com.dicoding.capstone.utils.NetworkBoundResource
import com.dicoding.capstone.utils.Resource

class AppRepository private constructor(private val remoteData: RemoteData,
                                        private val obatDataSource: ObatDataSource,
                                        private val appExecutors: AppExecutors) : DataSource {
    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(remoteData: RemoteData,obatDataSource: ObatDataSource,appExecutors: AppExecutors): AppRepository = instance ?: synchronized(this) {
            AppRepository(remoteData,obatDataSource,appExecutors).apply {
                instance = this
            }
        }
    }
    override fun getObat(): LiveData<Resource<List<ObatEntity>>> {
        return object :
            NetworkBoundResource<List<ObatEntity>, List<DataObat>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<ObatEntity>> =
                obatDataSource.getObat()

            override fun shouldFetch(data: List<ObatEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<DataObat>>> =
                remoteData.getAllObat()

            override fun saveCallResult(data: List<DataObat>) {
                val obatList = ArrayList<ObatEntity>()
                for (response in data) {
                    val obat = ObatEntity(
                        response.id,
                        response.nama,
                        response.harga,
                        response.deskripsi,
                        response.indikasi,
                        response.komposisi,
                        response.dosis,
                        response.aturan,
                        response.efek,
                        response.foto,
                        response.category
                    )
                    obatList.add(obat)
                }
                obatDataSource.insertObat(obatList)
            }
        }.asLiveData()
    }
    override fun insertObat(obat: List<ObatEntity>) {
        val runnable = {
            if (obatDataSource.getObat().value.isNullOrEmpty()){
                obatDataSource.insertObat(obat)
            }
        }
        appExecutors.diskIO().execute(runnable)
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
                response.foto,
                response.category)
            }
        }
        return detailO
    }
    override fun getCatBat(): LiveData<Resource<List<ObatEntity>>> {
        return object :
            NetworkBoundResource<List<ObatEntity>, List<DataObat>>(appExecutors) {
            public override fun loadFromDB(): LiveData<List<ObatEntity>> =
                obatDataSource.getCatBat()

            override fun shouldFetch(data: List<ObatEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<DataObat>>> =
                remoteData.getAllObat()

            override fun saveCallResult(data: List<DataObat>) {
                val obatList = ArrayList<ObatEntity>()
                for (response in data) {
                    val obat = ObatEntity(
                        response.id,
                        response.nama,
                        response.harga,
                        response.deskripsi,
                        response.indikasi,
                        response.komposisi,
                        response.dosis,
                        response.aturan,
                        response.efek,
                        response.foto,
                        response.category
                    )
                    obatList.add(obat)
                }
                obatDataSource.insertObat(obatList)
            }
        }.asLiveData()
    }
}