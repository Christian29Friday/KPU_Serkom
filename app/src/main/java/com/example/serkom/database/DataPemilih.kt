package com.example.serkom.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Entity(tableName = "DataPemilih")
@Parcelize
data class DataPemilih(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "nik")
    var nik: String? = null,

    @ColumnInfo(name = "nama")
    var nama: String? = null,

    @ColumnInfo(name = "nomorhp")
    var nomorhp: String? = null,

    @ColumnInfo(name = "jeniskelamin")
    var jeniskelamin: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "alamat")
    var alamat: String? = null,

    @ColumnInfo(name = "latitude")
    var latitude: Double? = null,

    @ColumnInfo(name = "longitude")
    var longitude: Double? = null,

    @ColumnInfo(name = "gambar")
    var gambar: ByteArray? = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.createByteArray()
    ) {
    }

    companion object : Parceler<DataPemilih> {

        override fun DataPemilih.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(nik)
            parcel.writeString(nama)
            parcel.writeString(nomorhp)
            parcel.writeString(jeniskelamin)
            parcel.writeString(date)
            parcel.writeString(alamat)
            parcel.writeValue(latitude)
            parcel.writeValue(longitude)
            parcel.writeByteArray(gambar)
        }

        override fun create(parcel: Parcel): DataPemilih {
            return DataPemilih(parcel)
        }
    }
}