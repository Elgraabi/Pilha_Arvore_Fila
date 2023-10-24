
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Gerenciamento {

    //Atributos
    private LinkedList<Stack<Palavra>> listaStack = new LinkedList<Stack<Palavra>>();
    private SalvarArquivo salvar = new SalvarArquivo();
    private LinkedList<Palavra> lista = new LinkedList<Palavra>();
    private LinkedList<Palavra> repetidos = new LinkedList<Palavra>();
    private Queue<Palavra> fila = new LinkedList<Palavra>();
    private Stack<Palavra> pilha = new Stack<Palavra>();
    private Arvore<Palavra> arvore = new Arvore<Palavra>();

    //Metodos Especificos
    public LinkedList<Palavra> organizaLista() {
        LinkedList<Palavra> lista2 = new LinkedList<Palavra>();
        boolean inseriu = false;
        Palavra atual;
        for (int i = 0; i < this.lista.size(); i++) {
            atual = this.lista.get(i);
            for (int u = 0; u < lista2.size(); u++) {
                Palavra palList2 = lista2.get(u);
                inseriu = false;
                if (atual.getTamanho() < palList2.getTamanho()) {
                    lista2.add(u, atual);
                    inseriu = true;
                    break;
                }
            }
            if (!(inseriu)) {
                lista2.addLast(atual);
            }
        }
        return lista2;
    }

    public void incluirPalavra() {
        String palavra = JOptionPane.showInputDialog("Digite uma palavra");
        Palavra novaPalavra = new Palavra(palavra);
        lista.add(novaPalavra);
    }

    public void deletarPalavra() {
        String palavraADel = JOptionPane.showInputDialog("Digite a palavra a remover: ");
        Palavra atual = new Palavra(palavraADel);
        this.lista.removeLastOccurrence(atual);
    }

    public Queue<Palavra> inserirEmFila() {
        LinkedList<Palavra> lista2 = this.organizaLista();
        Queue<Palavra> fila2 = new LinkedList<Palavra>();
        Palavra atual;
        for (int t = 0; t < lista2.size(); t++) {
            atual = lista2.get(t);
            fila2.add(atual);
        }
        this.fila = fila2;
        return this.fila;
    }

    public void salvarLista() {
        salvar.grava(lista);
    }

    public void lerArquivo() {
        LinkedList<Palavra> nov = (LinkedList<Palavra>) salvar.ler();
        this.lista = nov;
    }

    public LinkedList<Stack<Palavra>> pilhaDePalavrasDeInicialIgual() {
        Stack<Palavra> stackAtual = new Stack<Palavra>();
        boolean inseriu = false;
        Palavra palList;
        Palavra palStackAtual;
        for (int u = 0; u < this.lista.size(); u++) {
            palList = this.lista.get(u);
            String primeiraLetraLista = palList.getPalavra();
            for (int i = 0; i < this.listaStack.size(); i++) {
                stackAtual = this.listaStack.get(i);
                palStackAtual = stackAtual.peek();
                String primeiraLetraStack = palStackAtual.getPalavra();
                inseriu = false;
                if (primeiraLetraLista.charAt(0) == primeiraLetraStack.charAt(0)) {
                    stackAtual.push(palList);
                    inseriu = true;
                    break;
                }
            }
            if (!(inseriu)) {
                Stack<Palavra> novaStack = new Stack<Palavra>();
                novaStack.push(palList);
                this.listaStack.add(novaStack);
            }
        }
        return this.listaStack;
    }

    public Arvore<Palavra> inseriEmArvore() {
        for (int i = 0; i < this.lista.size(); i++) {
            Object repetido = arvore.adicionar(this.lista.get(i));
            if (repetido != null) {

                this.repetidos.add((Palavra) repetido);
            }
        }
        return this.arvore;
    }

    public String imprimirArvore() {
        return "Em ordem: " + this.arvore.emOrdem(this.arvore.getRaiz()) + "\n"
                + "Pre ordem: " + this.arvore.preOrdem(this.arvore.getRaiz()) + "\n"
                + "Palavras nao inseridas: " + this.repetidos;
    }

    public void indiceInvertido() throws FileNotFoundException, IOException {
        SalvarArquivo patch = new SalvarArquivo();
        String texto = patch.lerPatch();
        LinkedList<String> listaDepalavras = new LinkedList<String>();
        String palavraAtual = "";
        for (int i = 0; i < texto.length(); i++) { //Criando lista de palavras 
            String atual = "" + texto.charAt(i);
            if (!(atual.equals(" ")) & !(atual.equals(",")) & !(atual.equals("."))) {
                palavraAtual = palavraAtual + "" + atual;
            } else {
                if (!(palavraAtual.equals(" ")) & !(palavraAtual.equals(",")) & !(palavraAtual.equals(".")) & !(palavraAtual.equals(""))) {
                    listaDepalavras.add(palavraAtual);
                    palavraAtual = "";
                }
            }
        }
        listaDepalavras.add(palavraAtual); // faltou adicionar no laço
        System.out.println("" + listaDepalavras.toString());
        LinkedList<Stack<String>> listaDePilhaDePalavras = new LinkedList<Stack<String>>();
        Stack<String> pilhaAtual = new Stack<String>();
        String palavraDaPilha = "";
        String palavra = "";
        boolean achou = false;
        for (int j = 0; j < listaDepalavras.size(); j++) {
            palavra = listaDepalavras.get(j);
            if (listaDePilhaDePalavras.isEmpty()) {
                Stack<String> novaPilha = new Stack<String>();
                novaPilha.add(palavra);
                listaDePilhaDePalavras.add(novaPilha);
            } else {
                for (int k = 0; k < listaDePilhaDePalavras.size(); k++) {
                    pilhaAtual = listaDePilhaDePalavras.get(k);
                    palavraDaPilha = pilhaAtual.peek();
                    achou = false;
                    if (palavra.equalsIgnoreCase(palavraDaPilha)) {
                        achou = true;
                    }
                    if (achou) {
                        pilhaAtual.add(palavra);
                        break;
                    } 
                }
                if (!(achou)) {
                    Stack<String> novaPilha = new Stack<String>();
                    novaPilha.add(palavra);
                    listaDePilhaDePalavras.add(novaPilha);
                }
            }
        }

        for (int l = 0; l < listaDePilhaDePalavras.size(); l++) {
            Stack atual = listaDePilhaDePalavras.get(l);
            System.out.println("Nome - " + atual.peek() + ", Ocorrencia - " + atual.size());
        }

        System.out.println("" + listaDePilhaDePalavras.toString());
    }

    //Metodos Especiais
    @Override
    public String toString() {
        return "" + this.lista;
    }

    public LinkedList<Stack<Palavra>> getListaStack() {
        return listaStack;
    }

    public void setListaStack(LinkedList<Stack<Palavra>> listaStack) {
        this.listaStack = listaStack;
    }

    public LinkedList<Palavra> getLista() {
        return lista;
    }

    public void setLista(LinkedList<Palavra> lista) {
        this.lista = lista;
    }

    public Queue<Palavra> getFila() {
        return fila;
    }

    public void setFila(Queue<Palavra> fila) {
        this.fila = fila;
    }

    public Stack<Palavra> getPilha() {
        return pilha;
    }

    public void setPilha(Stack<Palavra> pilha) {
        this.pilha = pilha;
    }
}
