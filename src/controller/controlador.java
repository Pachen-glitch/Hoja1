package src.controller;
import src.model.RadioGroup;

public class controlador(){
    public Radio= new RadioGroup()
}

public cambiarEstacion(int button){
    if (validar()){
    int x=button
    if (x==1){
        cambiarAM()
    }
    else{
        cambiarFM()
    }
}
}

public apagar(){
    if (getEstado()==false){
        System.out.println("Ya esta apagada")
    }
    else{
        setEstado(false);
    }
}

public encender(){
    if (getEstado()==true){
        System.out.println("Ya esta encendida")    
    }
    else{
        setEstado(true);
    }
    
}

private boolean validar(){
    if (getEstado()==false){
        System.out.println("La radio esta apagada")
        return false
    }
    else{
        return true
    }
}

public adelantarEstacion(){
    if (validar()){
         avanzarEstacion();
    }
}      

public guardarFavoritos(int pos){
    setFavoritos(int pos)
}

public cargarFavoritos(int post){
    float estacion=getFavoritos(int pos)
    if (estacion==int(estacion)){
        cambiarAM()
    }
    else{
        cambiarFM()
    } 
}



    
