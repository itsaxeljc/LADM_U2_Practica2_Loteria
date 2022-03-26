package mx.tecnm.tepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.tecnm.tepic.ladm_u2_practica2_loteria.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var estado = false
    var fin = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var hilo = AdminHilo(this)

        binding.btnIniciar.setOnClickListener {
            try {
                if(estado){
                    estado = false
                    hilo.terminarHilo()
                    hilo.pausarHilo()
                    binding.carta.setImageResource(R.drawable.uno)
                    binding.button2.setText("¡Buenas!")
                    binding.button2.visibility=(View.GONE)
                    binding.btnIniciar.setText("Iniciar")
                }else{
                    estado = true
                    hilo.barajear()
                    if(hilo.estaPausado()){
                        hilo.despausarHilo()
                        binding.button2.visibility=(View.VISIBLE)
                    } else if(fin){
                        fin = false
                        hilo.despausarHilo()
                        binding.button2.visibility=(View.VISIBLE)
                    } else hilo.start()
                    binding.btnIniciar.setText("Reiniciar")
                }
            }catch (ex: Exception){
                println(ex.message)
            }
        }

        binding.button2.setOnClickListener {
            if(!estado) {
                AlertDialog.Builder(this)
                    .setTitle("Atención")
                    .setMessage("Inicie el juego para poder pausarlo")
                    .setNegativeButton("Aceptar",{d,i->d.cancel()})
                    .show()
            }
            if(!hilo.estaPausado() && estado){
                hilo.pausarHilo()
                binding.button2.setText("Ver faltantes")
            }else{
                hilo.despausarHilo()
                binding.button2.setText("¡Buenas!")
            }

        }

    }

    fun sonCarta(song:MediaPlayer) = GlobalScope.launch {
        launch {
            song.start()
            song.setOnCompletionListener {
                mp -> mp.release()
            }
            delay(4000)
        }
    }

}