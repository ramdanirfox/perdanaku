package id.perdanaku.app.ui.open_qr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OpenQRViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Buka QR Fragment"
    }
    val text: LiveData<String> = _text
}