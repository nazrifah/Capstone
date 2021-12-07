package com.dicoding.capstone.utils

import android.content.Context
import com.dicoding.capstone.adapter.DataObat
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper (private val context: Context) {
    private fun parsingFileToString (fileName : String) : String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        }
        catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun loadObat() : List<DataObat> {
        val listObat = ArrayList<DataObat>()

        try {
            val resposeObject = JSONObject(parsingFileToString("ObatList.json").toString())
            val listArray = resposeObject.getJSONArray("obat")

            for (i in 0 until listArray.length()) {
                val obat = listArray.getJSONObject(i)

                val id = obat.getInt("id")
                val nama = obat.getString("nama")
                val harga = obat.getString("harga")
                val deskripsi = obat.getString("deskripsi")
                val indikasi = obat.getString("indikasi")
                val komposisi = obat.getString("komposisi")
                val dosis = obat.getString("dosis")
                val aturan = obat.getString("aturan")
                val efek = obat.getString("efek")
                val foto = obat.getString("foto")

                val obatResponse = DataObat(id, nama, harga, deskripsi,indikasi, komposisi, dosis,aturan,efek,foto)
                listObat.add(obatResponse)
            }
        }
        catch (e : JSONException) {
            e.printStackTrace()
        }
        return listObat
    }
}