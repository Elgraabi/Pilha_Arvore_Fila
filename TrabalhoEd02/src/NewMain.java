
import java.io.IOException;
import javax.swing.JOptionPane;

public class NewMain {

    public static void main(String[] args) throws IOException {
        Gerenciamento geren = new Gerenciamento();
        boolean menu = true;
        int opcao = 0;
        geren.lerArquivo();
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(
                    "------------- MENU DE OPCAO -------------" + "\n"
                    + "[1] Incluir palavra" + "\n" //fiz
                    + "[2] Deletar palavra" + "\n" //fiz
                    + "[3] Mostrar LISTA de palavras" + "\n" //fiz
                    + "[4] Inserir lista de palavras em FILA" + "\n" //fiz
                    + "[5] Criar PILHAS com palavras que iniciam com a mesma letra" + "\n" //fiz
                    + "[6] Inserir lista de palavras em ARVORE" + "\n" //fiz 
                    + "[7] Salvar lista" + "\n" //fiz
                    + "[8] [Extra] Criar índice invertido" + "\n" //fiz
                    + "[9] Sair")); //fiz
            switch (opcao) {
                case 1:
                    geren.incluirPalavra();
                    break;
                case 2:
                    geren.deletarPalavra();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "" + geren.toString());
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "" + geren.inserirEmFila());
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "" + geren.pilhaDePalavrasDeInicialIgual());
                    break;
                case 6:
                    geren.inseriEmArvore();
                    JOptionPane.showMessageDialog(null, "" + geren.imprimirArvore());
                    break;
                case 7:
                    geren.salvarLista();
                    break;
                case 8:
                    geren.indiceInvertido();
                    break;
                case 9:
                    menu = false;
                    break;
                default:
                    throw new AssertionError();
            }
        } while (menu != false);
    }
}

