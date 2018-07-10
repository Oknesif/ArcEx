package com.github.data.entities

import android.arch.persistence.room.Embedded

class AddressData(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        @Embedded val geo: GeoData
)