
public class Celula<T> {

    //Atributos
    private T elemento;
    private Celula<T> esquerda;
    private Celula<T> direita;

    //Metodos Especificos
    //Metodos Especiais
    public Celula(T elemento) {
        this.elemento = elemento;
        this.esquerda = null;
        this.direita = null;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public Celula<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Celula<T> esquerda) {
        this.esquerda = esquerda;
    }

    public Celula<T> getDireita() {
        return direita;
    }

    public void setDireita(Celula<T> direita) {
        this.direita = direita;
    }
}
