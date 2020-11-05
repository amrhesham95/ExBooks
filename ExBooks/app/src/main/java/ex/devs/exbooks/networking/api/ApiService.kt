package ex.devs.exbooks.networking.api

import ex.devs.exbooks.model.Notifications.MyResponse
import ex.devs.exbooks.networking.model.Sender
import ex.devs.exbooks.utils.HEADER_TOKEN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type:application/json", "Authorization:key=$HEADER_TOKEN")
    @POST("fcm/send")
    fun sendNotification(@Body body: Sender): Response<MyResponse>
}