package quiniela.tesis.com.mundialprueba2018;

public class PartidoVo {

    private String nombre;
    private String Grupo;
    private int foto;

    public PartidoVo(){

    }

    public PartidoVo(String nombre, String grupo, int foto) {
        this.nombre = nombre;
        Grupo = grupo;
        this.foto = foto;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String grupo) {
        Grupo = grupo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
