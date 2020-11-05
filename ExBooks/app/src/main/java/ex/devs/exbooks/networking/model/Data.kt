package ex.devs.exbooks.networking.model

import java.io.Serializable

data class Data(
        var user: String,
        var body: String,
        var title: String,
        var sent: String
) : Serializable
