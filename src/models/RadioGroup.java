public class RadioGroup{

    public RadioGroup extends Radio(){
        private boolean Estado;
        private int Estacion;//1 Am, 0 Fm
        private float EstacionFm=87.9;//107.9
        private int EstacionAm=530;//1610
        private Arraylist Favoritos= new ArrayList<>(12);
    }
    public void prenderRadio(){
        setEstado(true);
    }
    public void apagarRadio(){
        setEstado(false);
    }

    public void avanzarEstacion(){
        if (getEstacion()==1){
            if (getEstacionAm()==1610){
                setEstacionAm(520)
            }
            setEstacionAm(getEstacionAm()+10)
        }
        else{
            
            if (getEstacionFm()==107.9){
                setEstacionFm(87.7)
            }
            setEstacionFm(getEstacionFm()+0.2)
            
        }
    }

    public cambiarAM(){
        setEstacion(1);
    }
    public cambiarFM(){
        setEstacion(0);
    }




    // Getter y Setter de estado
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Getter y Setter de estacion
    public int getEstacion() {
        return estacion;
    }

    public void setEstacion(int estacion) {
        this.estacion = estacion;
    }

    // Getter y Setter de estacion FM
    public float getEstacionFm() {
        return estacionFm;
    }

    public void setEstacionFm(float estacionFm) {
        this.estacionFm = estacionFm;
    }

    // Getter y Setter de estacion AM
    public int getEstacionAm() {
        return estacionAm;
    }

    public void setEstacionAm(int estacionAm) {
        this.estacionAm = estacionAm;
    }

    // Getter y Setter de favoritos

    
    public float getFavoritos(int pos) {
        return favoritos[pos];
    }

    public void setFavoritos(int pos) {
        if(getEstacion()==1){
            favoritos[pos]=getEstacionAm();
        }
        else{
            favoritos[pos]=getEstacionFm();
        }
    }
    }    

