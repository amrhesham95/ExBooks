package ex.devs.exbooks.networking.model

import java.io.Serializable

data class Sender(
        val data: Data,
        val to: String
): Serializable