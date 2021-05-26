package com.androiddev97.noteapp2021.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Int=0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "date")
    val date: String,
//    @ColumnInfo(name = "color")
//    var color: Int
) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(id)
//        parcel.writeString(title)
//        parcel.writeString(description)
//        parcel.writeString(date)
//
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Note> {
//        override fun createFromParcel(parcel: Parcel): Note {
//            return Note(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Note?> {
//            return arrayOfNulls(size)
//        }
//    }
}

