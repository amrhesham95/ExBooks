package ex.devs.exbooks.networking.api

import ex.devs.exbooks.model.Notifications.MyResponse
import ex.devs.exbooks.networking.model.Sender
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService): ApiHelper {

    override suspend fun sendNotification(message: Sender): Response<MyResponse> = apiService.sendNotification(message)
}