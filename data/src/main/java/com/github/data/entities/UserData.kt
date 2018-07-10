package com.github.data.entities

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class UserData(
        @PrimaryKey val id: Int,
        val name: String,
        val email: String,
        val phone: String,
        val website: String,
        @Embedded val address: AddressData
)