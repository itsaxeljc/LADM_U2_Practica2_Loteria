package mx.tecnm.tepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.delay

class AdminHilo (activity: MainActivity) : Thread() {
    private var ejecutar = true
    private var pausar = false
    var act = activity
    var conta = 0
    private var maso = arrayListOf<Carta>(Carta(R.raw.c1,R.drawable.carta1),Carta(R.raw.c2,R.drawable.carta2),
        Carta(R.raw.c3,R.drawable.carta3),Carta(R.raw.c4,R.drawable.carta4),Carta(R.raw.c5,R.drawable.carta5),
        Carta(R.raw.c6,R.drawable.carta6),Carta(R.raw.c7,R.drawable.carta7),Carta(R.raw.c8,R.drawable.carta8),
        Carta(R.raw.c9,R.drawable.carta9),Carta(R.raw.c10,R.drawable.carta10),Carta(R.raw.c11,R.drawable.carta11),
        Carta(R.raw.c12,R.drawable.carta12),Carta(R.raw.c13,R.drawable.carta13),Carta(R.raw.c14,R.drawable.carta14),
        Carta(R.raw.c15,R.drawable.carta15),Carta(R.raw.c16,R.drawable.carta16),Carta(R.raw.c17,R.drawable.carta17),
        Carta(R.raw.c18,R.drawable.carta18),Carta(R.raw.c19,R.drawable.carta19),Carta(R.raw.c20,R.drawable.carta20),
        Carta(R.raw.c21,R.drawable.carta21),Carta(R.raw.c22,R.drawable.carta22),Carta(R.raw.c23,R.drawable.carta23),
        Carta(R.raw.c24,R.drawable.carta24),Carta(R.raw.c25,R.drawable.carta25),Carta(R.raw.c26,R.drawable.carta26),
        Carta(R.raw.c27,R.drawable.carta27),Carta(R.raw.c28,R.drawable.carta28),Carta(R.raw.c29,R.drawable.carta29),
        Carta(R.raw.c30,R.drawable.carta30),Carta(R.raw.c31,R.drawable.carta31),Carta(R.raw.c32,R.drawable.carta32),
        Carta(R.raw.c33,R.drawable.carta33),Carta(R.raw.c34,R.drawable.carta34),Carta(R.raw.c35,R.drawable.carta35),
        Carta(R.raw.c36,R.drawable.carta36),Carta(R.raw.c37,R.drawable.carta37),Carta(R.raw.c38,R.drawable.carta38),
        Carta(R.raw.c39,R.drawable.carta39),Carta(R.raw.c40,R.drawable.carta40),Carta(R.raw.c41,R.drawable.carta41),
        Carta(R.raw.c42,R.drawable.carta42),Carta(R.raw.c43,R.drawable.carta43),Carta(R.raw.c44,R.drawable.carta44),
        Carta(R.raw.c45,R.drawable.carta45),Carta(R.raw.c46,R.drawable.carta46),Carta(R.raw.c47,R.drawable.carta47),
        Carta(R.raw.c48,R.drawable.carta48),Carta(R.raw.c49,R.drawable.carta49),Carta(R.raw.c50,R.drawable.carta50),
        Carta(R.raw.c51,R.drawable.carta51),Carta(R.raw.c52,R.drawable.carta52),Carta(R.raw.c53,R.drawable.carta53),
        Carta(R.raw.c54,R.drawable.carta54))


    override fun run(){
        super.run()
        while(ejecutar){
            if(!pausar){
                act.runOnUiThread {
                    act.binding.btnIniciar.setText("Reiniciar")
                    act.binding.carta.setImageResource(maso[conta].getImg())

                    act.sonCarta(maso[conta].getSong(),act)

                }
                conta++
                if(conta>53){
                    terminarHilo()
                    pausarHilo()
                    act.runOnUiThread{
                        AlertDialog.Builder(act)
                            .setTitle("El juego a terminado")
                            .setMessage("El juego a terminado")
                            .setNegativeButton("Jugar de nuevo",{d,i->d.cancel()})
                            .show()
                        act.binding.carta.setImageResource(R.drawable.uno)
                        act.binding.button2.setText("¡Buenas!")
                        act.binding.button2.visibility=(View.GONE)
                        act.binding.btnIniciar.setText("Iniciar")
                        act.fin = true
                        act.estado = false
                        terminarHilo()
                    }
                }
            }
            sleep(3500)
        }
    }

    fun terminarHilo(){
        conta = 0
        //ejecutar = false
    }

    fun pausarHilo(){
        pausar = true
    }

    fun despausarHilo(){
        pausar = false
    }

    fun estaPausado() : Boolean{
        return pausar
    }

    fun barajear(){
        maso.shuffle()
    }
}