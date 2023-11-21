package com.example.bluetoothmoduletest

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import com.example.bluetoothmoduletest.databinding.FragmentFirstBinding
import com.example.bluetoothmoduletest.databinding.FragmentMainBinding
import com.example.bluetoothmoduletest.databinding.FragmentSecondBinding
import com.example.bt_def.BluetoothConstants
import com.example.bt_def.bluetooth.BluetoothController
import org.json.JSONException
import org.json.JSONObject

class SecondFragment : Fragment() {

    private lateinit var bluetoothController: BluetoothController
    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            initBtAdapter()
            val pref = activity?.getSharedPreferences(BluetoothConstants.PREFERENCES, Context.MODE_PRIVATE)
            bluetoothController = BluetoothController(btAdapter)
            val mac = pref?.getString(BluetoothConstants.MAC, "")
            bBack.setOnClickListener {
                findNavController().navigate(R.id.action_secondFragment_to_mainFragment)
                bluetoothController.closeConnection()
            }
            bTruck4.setOnClickListener {
                findNavController().navigate(R.id.action_secondFragment_to_truckFragment3)
                bluetoothController.closeConnection()
            }
            bTruck5.setOnClickListener {
                findNavController().navigate(R.id.action_secondFragment_to_truckFragment4)
                bluetoothController.closeConnection()
            }
            bTruck6.setOnClickListener {
                findNavController().navigate(R.id.action_secondFragment_to_truckFragment5)
                bluetoothController.closeConnection()
            }
        }
    }
    private fun initBtAdapter() {
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = bManager.adapter
    }

}