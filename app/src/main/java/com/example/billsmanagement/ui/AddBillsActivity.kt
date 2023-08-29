package com.example.billsmanagement.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.billsmanagement.Model.Bill
import com.example.billsmanagement.R
import com.example.billsmanagement.ViewModel.BillzViewModel

class AddBillsActivity : AppCompatActivity() {
    lateinit var binding: AddBillsActivity
    val billViewModel: BillzViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =AddBillsActivity.inflate
        setContentView(binding.root)

        setupFreqSpinner()
        setDueDateSpinner()

        binding.btnSave.setOnClickListener {
            val selectedFrequency = binding.tvFreequency.selectedItem.toString()
            val billName = binding.tvName.text.toString()
            val billAmount = binding.Amount.text.toString().toDouble()
            val selectedDueDate: String = when (selectedFrequency) {
                "Annual" -> {
                    val datePicker = binding.setDueDateSpinner()
                    "${datePicker.year}-${datePicker.month + 1}-${datePicker.dayOfMonth}"
                }
                else -> binding.spinner2.selectedItem.toString()
            }

            val bill = Bill(
                billId = UUID.randomUUID().toString(),
                name = billName,
                amount = billAmount,
                frequency = selectedFrequency,
                dueDate = selectedDueDate,
                userId = "USER_ID"
            )
            billViewModel.saveBill(bill)
            finish()
            navigateToSummaryFragment()
        }
    }
    fun setupFreqSpinner(){
        val adapter = ArrayAdapter.createFromResource(this, R.array.Frequency,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.etFrequency.adapter = adapter
    }

    private fun setDueDateSpinner() {
        binding.tvFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedFrequency = binding.frequency.selectedItem.toString()

                val dueDateAdapter = when (binding.tvFrequency.selectedItem.toString()) {
                    "Monthly" -> {
                        val daysInMonth = 1..31
                        ArrayAdapter(
                            this@AddBillsActivity,
                            android.R.layout.simple_spinner_item,
                            daysInMonth.toList()
                        )
                    }

                    "Quarterly" -> {
                        val daysInQuarter = 1..90
                        ArrayAdapter(
                            this@AddBillsActivity,
                            android.R.layout.simple_spinner_item,
                            daysInQuarter.toList()
                        )
                    }

                    "Annual" -> {
                        val daysInYear = 1..365
                        ArrayAdapter(
                            this@AddBillsActivity,
                            android.R.layout.simple_spinner_item,
                            daysInYear.toList()
                        )
                    }

                    else -> {
                        ArrayAdapter(
                            this@AddBillsActivity,
                            android.R.layout.simple_spinner_item,
                            arrayOf(1, 2, 3, 4, 5, 6, 7)
                        )

                    }
                }
                dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner2.adapter = dueDateAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }
    }

    fun navigateToSummaryFragment(){
        val fragment = SummaryFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(androidx.fragment.R.id.fragment_container_view_tag,fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}