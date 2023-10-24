
import java.io.Serializable;

public class Palavra implements Comparable,  Serializable{

    //Atributos
    private String palavra = "";
    private int tamanho;
    //Metodos Especificos

    public boolean equalsTamanho(Object palavra) {
        return this.tamanho < ((Palavra) palavra).getTamanho();
    }

    @Override
    public int compareTo(Object palavra) {
        if (this.tamanho < ((Palavra) palavra).getTamanho()) {
            return -1;
        }
        if (this.tamanho > ((Palavra) palavra).getTamanho()) {
            return 1;
        }
        return 0;
    }
    //Metodos Especiais

    @Override
    public boolean equals(Object palavra) {
        return this.palavra.equalsIgnoreCase(((Palavra) palavra).getPalavra());
    }

    @Override
    public String toString() {
        return "Palavra= " + palavra + ", tamanho=" + tamanho;
    }

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.tamanho = palavra.length();
    }

    public Palavra() {
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
