package id.perdanaku.app.ui.open_qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import id.perdanaku.app.databinding.FragmentOpenQrBinding


class OpenQRFragment : Fragment() {

    private var _binding: FragmentOpenQrBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(binding.root.context, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                binding.root.context,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // Launch
    fun onButtonClick(view: View?) {
        barcodeLauncher.launch(ScanOptions())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val openQRViewModel =
            ViewModelProvider(this).get(OpenQRViewModel::class.java)

        _binding = FragmentOpenQrBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textOpenQr
        openQRViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val button = binding.buttonidOpenQr;
        button.setOnClickListener { view ->
            Toast.makeText(binding.root.context, "Membuka...", Toast.LENGTH_SHORT).show()
            this.onButtonClick(null)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}