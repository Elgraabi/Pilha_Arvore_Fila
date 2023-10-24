
import java.io.Serializable;

public class Arvore<T extends Comparable> implements Serializable {

    //Atributos
    private Celula<T> raiz;

    //Metodos Especificos
    public T adicionar(T elemento) {
        Celula<T> novoEle = new Celula<T>(elemento);
        if (this.raiz == null) {
            this.raiz = novoEle;
        } else {
            Celula<T> celAtual = this.raiz;
            while (true) {
                if (!((Palavra) novoEle.getElemento()).getPalavra().equalsIgnoreCase(((Palavra) celAtual.getElemento()).getPalavra())) {
                    if (novoEle.getElemento().compareTo(celAtual.getElemento()) == -1) {
                        if (celAtual.getEsquerda() != null) {
                            celAtual = celAtual.getEsquerda();
                        } else {
                            celAtual.setEsquerda(novoEle);
                            break;
                        }
                    } else if (novoEle.getElemento().compareTo(celAtual.getElemento()) == 1) {
                        if (celAtual.getDireita() != null) {
                            celAtual = celAtual.getDireita();
                        } else {
                            celAtual.setDireita(novoEle);
                            break;
                        }
                    } else {
                        return null;
                    }
                } else {
                    return novoEle.getElemento();
                }
            }
        }
        return null;
    }

    public String emOrdem(Celula<T> atual) {
        if (atual != null) {
            return this.emOrdem(atual.getEsquerda()) + atual.getElemento() + ", " + this.emOrdem(atual.getDireita());
        } else {
            return "";
        }
    }

    public String preOrdem(Celula<T> atual) {
        if (atual != null) {
            return atual.getElemento() + ", " + this.preOrdem(atual.getEsquerda()) + this.preOrdem(atual.getDireita());
        } else {
            return "";
        }
    }

    public String posOrdem(Celula<T> atual) {
        if (atual != null) {
            return this.posOrdem(atual.getEsquerda()) + this.posOrdem(atual.getDireita()) + atual.getElemento() + ", ";
        } else {
            return "";
        }
    }

    public boolean remove(T elemento) {
        Celula<T> atual = this.raiz;
        Celula<T> paiAtual = null;
        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                break;
            } else if (elemento.compareTo(atual.getElemento()) == -1) {
                paiAtual = atual;
                atual = atual.getEsquerda();
            } else {
                paiAtual = atual;
                atual = atual.getDireita();
            }
        }
        if (atual != null) {
            if (atual.getDireita() != null && atual.getEsquerda() != null) { //tem direita e esquerda
                Celula<T> substituto = atual.getDireita();
                Celula<T> paiSubstituto = atual;
                while (substituto.getEsquerda() != null) {
                    paiSubstituto = substituto;
                    substituto = substituto.getEsquerda();
                }
                if (paiAtual != null) { //Mexer no galho
                    if (substituto.getDireita() != null) {
                        Celula<T> direitaSub = substituto.getDireita();
                        if (substituto.getElemento().compareTo(paiSubstituto.getElemento()) == -1) {
                            paiSubstituto.setEsquerda(direitaSub);
                        } else {
                            paiSubstituto.setDireita(direitaSub);
                        }
                        substituto.setEsquerda(atual.getEsquerda());
                        substituto.setDireita(atual.getDireita());
                        if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1) {
                            paiAtual.setEsquerda(substituto);
                        } else {
                            paiAtual.setDireita(substituto);
                        }
                    } else {
                        substituto.setEsquerda(paiSubstituto.getEsquerda());
                        if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1) {
                            paiAtual.setEsquerda(substituto);
                        } else {
                            paiAtual.setDireita(substituto);
                        }
                    }
                } else { //Mexer na raiz
                    if (paiSubstituto != atual) {
                        if (substituto.getDireita() != null) {
                            Celula<T> direitaSub = substituto.getDireita();
                            paiSubstituto.setEsquerda(direitaSub);
                            substituto.setEsquerda(atual.getEsquerda());
                            substituto.setDireita(atual.getDireita());
                            this.raiz = substituto;
                        } else {
                            substituto.setEsquerda(atual.getEsquerda());
                            substituto.setDireita(atual.getDireita());
                            this.raiz = substituto;
                        }
                    } else {
                        substituto.setEsquerda(atual.getEsquerda());
                        this.raiz = substituto;
                    }
                }
            } else if (atual.getDireita() != null && atual.getEsquerda() == null) { //Tem só a direita 
                Celula<T> substituto = atual.getDireita();
                Celula<T> paiSubstituto = atual;
                while (substituto.getEsquerda() != null) {
                    paiSubstituto = substituto;
                    substituto = substituto.getEsquerda();
                }
                if (paiAtual != null) { //Mexer no galho
                    if (substituto.getDireita() != null) {
                        Celula<T> direitaSub = substituto.getDireita();
                        paiSubstituto.setEsquerda(direitaSub);
                        if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1) {
                            paiAtual.setEsquerda(substituto);
                        } else {
                            paiAtual.setDireita(substituto);
                        }
                    } else {
                        if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1) {
                            paiAtual.setEsquerda(substituto);
                        } else {
                            paiAtual.setDireita(substituto);
                        }
                        if (substituto.getElemento().compareTo(paiSubstituto.getElemento()) == -1) {
                            paiSubstituto.setEsquerda(null);
                        } else {
                            paiSubstituto.setDireita(null);
                        }
                    }
                } else { //Mexer na raiz
                    this.raiz = substituto;
                }
            } else if (atual.getEsquerda() != null && atual.getDireita() == null) { //Tem só a esquerda
                Celula<T> substituto = atual.getEsquerda();
                Celula<T> paiSubstituto = atual;
                while (substituto.getDireita() != null) {
                    paiSubstituto = substituto;
                    substituto = substituto.getDireita();
                }
                if (paiAtual != null) { //Mexer no galho
                    if (substituto.getEsquerda() != null) {
                        Celula<T> direitaSub = substituto.getEsquerda();
                        paiSubstituto.setDireita(direitaSub);
                        if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1) {
                            paiAtual.setEsquerda(substituto);
                        } else {
                            paiAtual.setDireita(substituto);
                        }
                    } else {
                        if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1) {
                            paiAtual.setEsquerda(substituto);
                        } else {
                            paiAtual.setDireita(substituto);
                        }
                        if (substituto.getElemento().compareTo(paiSubstituto.getElemento()) == -1) {
                            paiSubstituto.setEsquerda(null);
                        } else {
                            paiSubstituto.setDireita(null);
                        }
                    }
                } else { //Mexer na raiz
                    this.raiz = substituto;
                }
            } else if (atual.getEsquerda() == null && atual.getDireita() == null) { //não tem
                if (paiAtual != null) {
                    if (atual.getElemento().compareTo(paiAtual.getElemento()) == -1) {
                        paiAtual.setEsquerda(null);
                    } else {
                        paiAtual.setDireita(null);
                    }
                } else {
                    this.raiz = null;
                }
            }
        }
        return false;
    }

    //Metodos Especiais
    public Arvore() {
        this.raiz = null;
    }

    public Celula<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Celula<T> raiz) {
        this.raiz = raiz;
    }
}
