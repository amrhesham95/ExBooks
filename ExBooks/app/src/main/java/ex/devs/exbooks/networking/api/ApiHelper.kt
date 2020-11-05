package ex.devs.exbooks.networking.api

import ex.devs.exbooks.model.Notifications.MyResponse
import ex.devs.exbooks.networking.model.Sender
import retrofit2.Response

interface ApiHelper {

    suspend fun sendNotification(message: Sender) : Response<MyResponse>
}