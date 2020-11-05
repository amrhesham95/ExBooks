package ex.devs.exbooks.networking.repository

import ex.devs.exbooks.networking.model.Sender
import ex.devs.exbooks.networking.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun sendNoification(message : Sender)  = apiHelper.sendNotification(message)
}