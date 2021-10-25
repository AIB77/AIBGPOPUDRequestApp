package com.example.aib_gpopud_requestapp

import com.google.gson.annotations.SerializedName


class Users {

    var data: List<UserDetails>? = null

    class UserDetails {

        @SerializedName("name")
        var name: String? = null


        @SerializedName("location")
        var location: String? = null

        @SerializedName("pk")
        var id: Int? = null

        constructor(name: String?, location: String?, id: Int) {
            this.name = name
            this.location = location
            this.id=id
        }
    }
}