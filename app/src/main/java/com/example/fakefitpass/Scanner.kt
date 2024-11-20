package com.example.fakefitpass

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.BarcodeView
import com.google.zxing.ResultPoint

class Scanner : AppCompatActivity() {

    private lateinit var barcodeView: BarcodeView
    private val REQUEST_CAMERA_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scanner)

        barcodeView = findViewById(R.id.zxing_barcode_scanner)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Check for camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            initializeBarcodeView()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                initializeBarcodeView()
            } else {
                // Permission denied, handle appropriately
            }
        }
    }

    private fun initializeBarcodeView() {
        barcodeView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                if (result.text != null) {
                    val inputText = intent.getStringExtra("EXTRA_TEXT")
                    val obieqti = intent.getStringExtra("OBIEQTI")
                    val intent = Intent(this@Scanner, NexttActivity::class.java)
                    intent.putExtra("SCAN_RESULT", result.text)
                    intent.putExtra("EXTRA_TEXT", inputText)
                    intent.putExtra("OBIEQTI", obieqti)
                    //აქ გაატანე რა ინფორმაციაც გინდა რომ ახალ აქთივითიში შეცვალოს
                    startActivity(intent)
                    // Stop scanning
                    barcodeView.pause()
                }
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {
                // Handle possible result points if needed
            }
        })

        // Optional: Configure camera settings if needed
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }
}
