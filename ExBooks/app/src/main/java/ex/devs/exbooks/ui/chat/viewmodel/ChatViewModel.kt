package ex.devs.exbooks.ui.chat.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ex.devs.exbooks.networking.repository.MainRepository
import kotlinx.coroutines.launch

class ChatViewModel @ViewModelInject constructor(
        private val mainRepository: MainRepository
): ViewModel() {

    fun sendMessage(){
        viewModelScope.launch {

        }
    }
}