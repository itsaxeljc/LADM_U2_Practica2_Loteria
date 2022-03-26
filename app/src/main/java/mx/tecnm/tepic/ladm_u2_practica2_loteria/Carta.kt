package mx.tecnm.tepic.ladm_u2_practica2_loteria

import android.media.MediaPlayer

class Carta (song:Int,image:Int) {
    val img = image
    val song = song

    @JvmName("getImg1")
    fun getImg() : Int{
        return img
    }

    @JvmName("getSong1")
    fun getSong() : Int{
        return song
    }

}