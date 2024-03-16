package com.example.project_calculator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.Toast
import com.example.project_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener{
            calculateEquation()
        }
    }

    private fun calculateEquation(){
        val valor1 = binding.editTextValor1.text.toString().trim()
        val valor2 = binding.editTextValor2.text.toString().trim()

        if(valor1.isEmpty() && valor2.isEmpty()){
            Toast.makeText(this, "Ambos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
            return
        }
        else if(valor1.isEmpty())
        {
            Toast.makeText(this, "Campo 1 é obrigatório", Toast.LENGTH_SHORT).show()
            return
        }
        else if(valor2.isEmpty()){
            Toast.makeText(this, "Campo 2 é obrigatório", Toast.LENGTH_SHORT).show()
            return
        }

        val realValue1 = valor1.toFloat()
        val realValue2 = valor2.toFloat()

        val checkedId = binding.radioGroup.checkedRadioButtonId

        val result: Float? = when(checkedId){
            binding.radioSoma.id -> {
                sumValues(realValue1, realValue2)
            }
            binding.radioSub.id -> {
                subValues(realValue1, realValue2)
            }
            binding.radioMultip.id -> {
                multiValues(realValue1, realValue2)
            }
            else ->{
                divValues(realValue1, realValue2)
            }
        }

        if(result == null)
            Toast.makeText(this, "Divisão inválida", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun sumValues(value1: Float, value2: Float): Float {
        return value1 + value2
    }

    private fun subValues(value1: Float, value2: Float): Float{
        return value1 - value2
    }

    private fun multiValues(value1: Float, value2: Float): Float{
        return value1 * value2
    }

    private fun divValues(value1: Float, value2: Float): Float?{
        if(value2 == 0f)
            return null
        return value1 / value2
    }

}