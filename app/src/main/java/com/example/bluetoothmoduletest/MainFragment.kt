package com.example.bluetoothmoduletest

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bluetoothmoduletest.databinding.FragmentMainBinding
import com.example.bt_def.BluetoothConstants
import com.example.bt_def.bluetooth.BluetoothController
import org.json.JSONException
import org.json.JSONObject

class MainFragment : Fragment() {

    private lateinit var bluetoothController: BluetoothController
    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            initBtAdapter()
            val pref = activity?.getSharedPreferences(BluetoothConstants.PREFERENCES, Context.MODE_PRIVATE)
            bluetoothController = BluetoothController(btAdapter)
            val mac = pref?.getString(BluetoothConstants.MAC, "")
            btList.setOnClickListener {
                findNavController().navigate(R.id.listFragment)
            }
            bTruck.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_firstFragment)
            }
            bTractor.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
            }
        }
    }

    private fun initBtAdapter() {
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = bManager.adapter
    }




}